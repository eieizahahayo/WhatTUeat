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

    private DatabaseReference mDatabase;

    public final String[] chaingRak = {
    "มานีกระเพาะปลา","Dairy store ปังหยาป้าแดง","ข้าวไข่เจียว/ยำ Banyan","Interhouse อาหารคลีน",
    "อาหารตามสั่งใต้อินเตอร์","ครัวมี้","ก๋วยเตี๋ยวเรือยกซด","ใบโมก","ปอาหารตตามสั่งป้าหนู","ข้าวมันไก่ป้ายแดง","กะเพราถาดใต้ก้ำ","Namba","Society",
    "Okoku","ตำแหลก","สุกี้นายพัน","ไก่ย่างห้าดาว","นมสาด","ชาบูลาว","ไข่หวานบ้านซูชิ","ก๋วยเตี๋ยวเรือนายเกรียง","ตำลึง","รสเอก","เมี่บงปลาเผา ป้าบุญเพ็ง",
    "โจ๊ก U-Square","สุขใจหมูกระทะ","ก๋วยเตี๋ยวกระดก","บะหมี่เป็ดหมูกรอบ ข้าวหน้าเป็ด","Shabu Umai","ShabuKu","เกรียงทะเลเผา","นายเกรียงปลาเผา",
    "ย่างเนย","ชาบูบู๋ตึ๋ง","นมหวาน"};
    public final String[] vitya = {"โรงอาหารคณะวิทยาศาสตร์","ร้านกาแฟบร.2","Seven-eleven","ร้านยำยูป้า","Urbie"};
    public final String[] sc = {"โรงอาหาร SC","โรงอาหารคณะบัญชี","โรงอาหารนิติศาสตร์","Dairy Queen","Seven-eleven","Hotto bun"};
    public final String[] greenCanteen = {"โรงอาหารกรีนแคนทีน","Swensen","Salmon king","ไก่ย่างห้าดาว","โรงอาหารโต้รุ่ง","กาแฟหอมกรุ่น","Starbuck","Urbie"};
    public final String[] tiewson = {"โรงอาหารทิวสน","ตลาดนัดอินเตอร์โซน","Top super market","Seven-eleven"};
    public final String[] tuDome = {"ตลาดนัด TU dome" , "ศูนย์อาหาร TU dome" , "KFC" , "MK" , "Yayoi" , "Seven-eleven","S&P"};
    public final String[] vanStation = {"Yakishi yakiniku","โรงอาหารคณะวิศวกรรมศาสตร์","Tomodachi","Sydney steak TU","อ๋องข้าวผัดปู","ร้านนายโอ๋เตี๋ยวต้มยำไข่"};
    public final String[] hospital = {"Pepper lunch","ศูนย์อาหารโรงพยาบาลธรรมศาสตร์","KFC","Starbuck","D'Oro","Black canyon","Seven-eleven","โรงอาหารคณะแพทยศาสตร์"};
    public final String[] init = {"เดินเพื่อ tune GPS หน่อยจ้า"};
    public final String[] doeNot = {"ไม่มีร้านอาหารใกล้เคียง","ลองเดินหาดูสิ"};

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

        if(area == "places_chiangrak"){
            initData(chaingRak);
        }
        else if(area == "places_vitya"){
            initData(vitya);
            Log.v("tot", "sci");
        }
        else if(area == "places_greenCanteen"){
            initData(greenCanteen);
        }
        else if(area == "places_sc"){
            initData(sc);
        }
        else if(area == "places_tiewson"){
            initData(tiewson);
        }
        else if(area == "places_tudome"){
            initData(tuDome);
        }
        else if(area == "places_vanStation"){
            initData(vanStation);
        }
        else if(area == "places_hospital"){
            initData(hospital);
        }
        else if(area == "init"){
            initData(init);
        }else{
            initData(doeNot);
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