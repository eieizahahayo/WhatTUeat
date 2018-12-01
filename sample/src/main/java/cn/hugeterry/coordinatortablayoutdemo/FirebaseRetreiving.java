package cn.hugeterry.coordinatortablayoutdemo;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseRetreiving {

    String[] test = new String[50];
    private DatabaseReference mDatabase;

    public FirebaseRetreiving(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("places_chiangrak");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("cr12").child("type").toString();
                int size = 0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                        Log.v("sapatawajae", String.valueOf(postSnapshot.child("name").getValue()));
//                    mDatas.add(String.valueOf(postSnapshot.child("name").getValue()));
//                        test[size] = String.valueOf(postSnapshot);
                    test[size] = String.valueOf(postSnapshot.child("name").getValue());
                    size++ ;
//                    Log.v("sapatawajae","onDataChange");
                }
                Log.v("sapatawajae", "retrieve");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("sapatawajae", "yooooo");
            }
        });
    }
}