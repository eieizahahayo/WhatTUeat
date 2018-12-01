package cn.hugeterry.coordinatortablayoutdemo;

import android.content.Context;
import android.support.v7.app.AlertDialog;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class SensorInfo {
    private float x = 0;
    private float y = 0;
    private float z = 0;
    private int shake_threshold = 10;
    private int index;
    private String area = "";
    private DatabaseReference mDatabase;

    public void setSensor(float x, float y, float z, String area) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.area = area;
    }

    public void showDialog(final Context context) {
        if((Math.abs(x) > shake_threshold) || (Math.abs(y) > shake_threshold) || (Math.abs(z) > shake_threshold)) {

                mDatabase = FirebaseDatabase.getInstance().getReference().child("places_chiangrak");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final AlertDialog.Builder viewDialog = new AlertDialog.Builder(context);
                        int size = 1;
                        String child;
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            size++ ;
                        }
                        index = (int)(Math.random() * (size));
                        child = "cr" + String.valueOf(index);
                        viewDialog.setTitle(String.valueOf(dataSnapshot.child(child).child("name").getValue()));
                        viewDialog.setMessage("Description : " + String.valueOf(dataSnapshot.child(child).child("description").getValue()) + "\n" +
                                "Location : " + String.valueOf(dataSnapshot.child(child).child("location").getValue()) + "\n" +
                                "Type : " + String.valueOf(dataSnapshot.child(child).child("type").getValue()) + "\n" +
                                "Price : " + String.valueOf(dataSnapshot.child(child).child("price").getValue()) + "\n" +
                                "Time : " + String.valueOf(dataSnapshot.child(child).child("time").getValue()));
                        viewDialog.show();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        final AlertDialog.Builder viewDialog = new AlertDialog.Builder(context);
                        viewDialog.setTitle("Error");
                        viewDialog.setMessage("Cannot connect to the database");
                    }
                });

        }
    }
}
