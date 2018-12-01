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
    private String child = "";
    private DatabaseReference mDatabase;

    public void setSensor(float x, float y, float z, String area,String child) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.area = area;
        this.child = child;
    }

    public void showDialog(final Context context) {
        if((Math.abs(x) > shake_threshold) || (Math.abs(y) > shake_threshold) || (Math.abs(z) > shake_threshold)) {

                mDatabase = FirebaseDatabase.getInstance().getReference().child(area);
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final AlertDialog.Builder viewDialog = new AlertDialog.Builder(context);
                        int size = 1;
                        String temp;
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            size++ ;
                        }
                        index = (int)(Math.random() * (size));
                        temp = child + String.valueOf(index);
                        viewDialog.setTitle(String.valueOf(dataSnapshot.child(temp).child("name").getValue()));
                        viewDialog.setMessage("Description : " + String.valueOf(dataSnapshot.child(temp).child("description").getValue()) + "\n" +
                                "Location : " + String.valueOf(dataSnapshot.child(temp).child("location").getValue()) + "\n" +
                                "Type : " + String.valueOf(dataSnapshot.child(temp).child("type").getValue()) + "\n" +
                                "Price : " + String.valueOf(dataSnapshot.child(temp).child("price").getValue()) + "\n" +
                                "Time : " + String.valueOf(dataSnapshot.child(temp).child("time").getValue()));
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
