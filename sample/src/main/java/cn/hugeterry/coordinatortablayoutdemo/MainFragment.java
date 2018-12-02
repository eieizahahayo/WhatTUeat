package cn.hugeterry.coordinatortablayoutdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugeterry(http://hugeterry.cn)
 */
public class MainFragment extends Fragment  {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private static final String ARG_TITLE = "title";
    private static final String areaFrag = "areFrag";
    private String mTitle;
    private final String[] temp = {"Card","Big","Boom"};
    private String area;
    private FirebaseRetreiving firebase;
    private String child;

    public static MainFragment getInstance(String title, String arearea) {
        MainFragment fra = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        bundle.putString(areaFrag,arearea);
        fra.setArguments(bundle);
        return fra;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        Bundle bundle = getArguments();
        mTitle = bundle.getString(ARG_TITLE);
        area = bundle.getString(areaFrag);
        firebase = new FirebaseRetreiving();

        if(area == "places_chiangrak"){
            child = "cr";
            initData(firebase.chaingRak);
            firebase.FirebaseRetreiving("places_chiangrak",child);
        }
        else if(area == "places_vitya"){
            child = "vy";
            initData(firebase.vitya);
            firebase.FirebaseRetreiving("places_vitya",child);
        }
        else if(area == "places_greenCanteen"){
            child = "gc";
            initData(firebase.greenCanteen);
            firebase.FirebaseRetreiving("places_greenCanteen",child);
        }
        else if(area == "places_sc"){
            child = "sc";
            initData(firebase.sc);
            firebase.FirebaseRetreiving("places_sc",child);
        }
        else if(area == "places_tiewson"){
            child = "ts";
            initData(firebase.tiewson);
            firebase.FirebaseRetreiving("places_tiewson",child);
        }
        else if(area == "places_tudome"){
            child = "tu";
            initData(firebase.tuDome);
            firebase.FirebaseRetreiving("places_tudome",child);
        }
        else if(area == "places_vanStation"){
            child = "vs";
            initData(firebase.vanStation);
            firebase.FirebaseRetreiving("places_vanStation",child);
        }
        else if(area == "places_hospital"){
            child = "ho";
            initData(firebase.hospital);
            firebase.FirebaseRetreiving("places_hospital",child);
        }
        else if(area == "init"){
            initData(firebase.init);
        }else{
            child = "text";
            initData(firebase.doeNot);
            firebase.FirebaseRetreiving("places_nowhere",child);
        }
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new RecyclerAdapter(mRecyclerView.getContext(), mDatas));
        return v;
    }

    private void initData(String[] input) {
        mDatas = new ArrayList<>();
        for(String data: input) {
            mDatas.add(data);
        }
    }



}