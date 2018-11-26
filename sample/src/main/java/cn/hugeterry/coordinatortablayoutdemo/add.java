package cn.hugeterry.coordinatortablayoutdemo;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;

public class add extends AppCompatActivity {
    private CoordinatorTabLayout mCoordinatorTabLayout;
    private int[] mImageArray,mColorArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

//        android.app.ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        CoordinatorTabLayout addPage = (CoordinatorTabLayout) findViewById(R.id.addPage);
//
//        addPage.setTranslucentStatusBar(this)
//            .setTitle("Add").setBackEnable(true);

        mImageArray = new int[]{
                R.mipmap.bg_android};

        mColorArray = new int[]{
                android.R.color.holo_orange_light};

        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.addPage);
        mCoordinatorTabLayout.setTranslucentStatusBar(this)
                .setTitle("TU do")
                .setImageArray(mImageArray, mColorArray);



    }
}
