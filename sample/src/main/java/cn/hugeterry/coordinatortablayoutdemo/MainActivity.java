package cn.hugeterry.coordinatortablayoutdemo;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private CoordinatorTabLayout mCoordinatorTabLayout;
    private int[] mImageArray, mColorArray;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"Nearby restaurants"};
    private ViewPager mViewPager;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private String setArea = "init";
    private String check = "init";
    private String child = "init";
    Context context;
    static final int POLL_INTERVAL = 3000;
    SensorInfo sensorInfo = new SensorInfo();
    SensorManager sensorManager;
    Sensor accelerometerSensor;
    Handler hdr = new Handler();

    private Runnable pollTask = new Runnable() {
        @Override
        public void run() {
            sensorInfo.showDialog(context);
            hdr.postDelayed(pollTask, POLL_INTERVAL);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mImageArray = new int[]{
                R.mipmap.bg_cb};
        mColorArray = new int[]{
                android.R.color.holo_green_light};
        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
        mCoordinatorTabLayout.setTranslucentStatusBar(MainActivity.this)
                .setTitle("What TU eat")
                .setImageArray(mImageArray, mColorArray)
                .setupWithViewPager(mViewPager);
        for (int i = 0; i < 4; i++) {
            Toast.makeText(MainActivity.this, "ลองเขย่าดู", Toast.LENGTH_LONG).show();
        }

        final AlertDialog.Builder viewDialog = new AlertDialog.Builder(context);
        viewDialog.setTitle("ขอต้อนรับสู่ What TU eat");
        viewDialog.setMessage("วิธีการใช้งาน\n" +
                "1. เดินเพื่อระบุตำแหน่ง\n" +
                "2. เขย่าเพื่อสุ่มร้านอาหาร\n\n" +
                " \t*เมื่อระบุตำแหน่งได้แล้วหน้าจอจะแสดงรายชื่อร้านอาหาร\n" );
        viewDialog.show();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if(location.getLatitude() < 14.067407 && location.getLatitude() > 14.065023 && location.getLongitude() < 100.613721 && location.getLongitude() > 100.605179 ){
                    if(check != "places_chiangrak"){
                        setArea("places_chiangrak");
                        check = "places_chiangrak";
                        child = "cr";
                        initFragments();
                        initViewPager();
                        mCoordinatorTabLayout.setTranslucentStatusBar(MainActivity.this)
                                .setTitle("Chiangrak area")
                                .setImageArray(mImageArray, mColorArray)
                                .setupWithViewPager(mViewPager);
                    }
                }else if(location.getLatitude() < 14.070265 && location.getLatitude() > 14.064944 && location.getLongitude() < 100.602783 && location.getLongitude() > 100.597374){
                    if(check != "places_tudome"){
                        setArea("places_tudome");
                        check = "places_tudome";
                        child = "tu";
                        initFragments();
                        initViewPager();
                        mCoordinatorTabLayout.setTranslucentStatusBar(MainActivity.this)
                                .setTitle("TU dome area")
                                .setImageArray(mImageArray, mColorArray)
                                .setupWithViewPager(mViewPager);
                    }
                }else if(location.getLatitude() < 14.071980 && location.getLatitude() > 14.070031 && location.getLongitude() < 100.605422 && location.getLongitude() > 100.601711){
                    if(check != "places_sc"){
                        setArea("places_sc");
                        check = "places_sc";
                        child = "sc";
                        initFragments();
                        initViewPager();
                        mCoordinatorTabLayout.setTranslucentStatusBar(MainActivity.this)
                                .setTitle("SC area")
                                .setImageArray(mImageArray, mColorArray)
                                .setupWithViewPager(mViewPager);
                    }

                }else if(location.getLatitude() < 14.070095 && location.getLatitude() > 14.067502 && location.getLongitude() < 100.610126 && location.getLongitude() > 100.605554){
                    if(check != "places_vanStation"){
                        setArea("places_vanStation");
                        check = "places_vanStation";
                        child = "vs";
                        initFragments();
                        initViewPager();
                        mCoordinatorTabLayout.setTranslucentStatusBar(MainActivity.this)
                                .setTitle("Van station area")
                                .setImageArray(mImageArray, mColorArray)
                                .setupWithViewPager(mViewPager);
                    }
                }else if(location.getLatitude() < 14.074720 && location.getLatitude() > 14.071236 && location.getLongitude() < 100.602760 && location.getLongitude() > 100.598996){
                    if(check != "places_greenCanteen"){
                        setArea("places_greenCanteen");
                        check = "places_greenCanteen";
                        child = "gc";
                        mCoordinatorTabLayout.setTranslucentStatusBar(MainActivity.this)
                                .setTitle("Green canteen area")
                                .setImageArray(mImageArray, mColorArray)
                                .setupWithViewPager(mViewPager);
                        Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG).show();

                        initFragments();
                        initViewPager();
                    }
                }else if(location.getLatitude() < 14.074761 && location.getLatitude() > 14.07211 && location.getLongitude() < 100.610103 && location.getLongitude() > 100.601658){
                    if(check != "places_vitya"){
                        check = "places_vitya";
                        setArea("places_vitya");
                        child = "vy";
                        initFragments();
                        initViewPager();
                        mCoordinatorTabLayout.setTranslucentStatusBar(MainActivity.this)
                                .setTitle("Science dep. area")
                                .setImageArray(mImageArray, mColorArray)
                                .setupWithViewPager(mViewPager);
                        Log.v("tot", "[main] sci");
                        Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG).show();
                    }
                }else if(location.getLatitude() < 14.075099 && location.getLatitude() > 14.072296 && location.getLongitude() < 100.616243 && location.getLongitude() > 100.610589){
                    if(check != "places_hospital"){
                        setArea("places_hospital");
                        check = "places_hospital";
                        child = "ho";
                        initFragments();
                        initViewPager();
                        mCoordinatorTabLayout.setTranslucentStatusBar(MainActivity.this)
                                .setTitle("Hospital area")
                                .setImageArray(mImageArray, mColorArray)
                                .setupWithViewPager(mViewPager);
                    }
                }else if(location.getLatitude() < 14.078559 && location.getLatitude() > 14.076078 && location.getLongitude() < 100.600240 && location.getLongitude() > 100.59408){
                    if(check != "places_tiewson"){
                        setArea("places_tiewson");
                        check = "places_tiewson";
                        child = "ts";
                        initFragments();
                        initViewPager();
                        mCoordinatorTabLayout.setTranslucentStatusBar(MainActivity.this)
                                .setTitle("Tiewson area")
                                .setImageArray(mImageArray, mColorArray)
                                .setupWithViewPager(mViewPager);
                    }
                }else{
                    if(check != "places_nowhere"){
                        setArea("places_nowhere");
                        check = "places_nowhere";
                        child = "text";
                        initFragments();
                        initViewPager();
                        mCoordinatorTabLayout.setTranslucentStatusBar(MainActivity.this)
                                .setTitle("Nowhere")
                                .setImageArray(mImageArray, mColorArray)
                                .setupWithViewPager(mViewPager);
                        Log.v("tot", "[main] nowhere");
                    }
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);
                return;
            }else{
                configureButton();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults){
        switch (requestCode){
            case 10:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_DENIED){
                    configureButton();
                }
                return;
        }
    }

    private void configureButton(){
        locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);

    }

    public void setArea(String input){
        setArea = input;
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        Log.d("tot", "[add fragment] " + setArea);
        for (String title : mTitles) {
            mFragments.add(MainFragment.getInstance(title,setArea));
        }
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
        hdr.postDelayed(pollTask, POLL_INTERVAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();
        if(type == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            sensorInfo.setSensor(x, y, z, check,child);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
