package cn.hugeterry.coordinatortablayoutdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public final String[] chaingRak = {"Shinkansen","Family mart","Seven-eleven","Nana steak house","Sweetduck",
    "ปังเว้ยเฮ้ย","Gelato","Shinya","So cool","Stake holder","ร้านของทอดใต้ก้ำ","ติ่มซำ fusion","อมรไก่ยำแซ่บ","ข้าวมันไก่ห้าสีป้ามาลี",
    "Jeffer steak","นายป้อม แล้งแซ่บ","มานีกระเพาะปลา","Dairy store ปังหยาป้าแดง","ข้าวไข่เจียว/ยำ Banyan","Interhouse อาหารคลีน",
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
    public final String[] golf = {"Seven-eleven","โรงอาหารกอล์ฟวิว"};
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
//        Bundle bundle = getArguments();
//        mTitle = bundle.getString(ARG_TITLE);
//        area = bundle.getString(areaFrag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        Bundle bundle = getArguments();
        mTitle = bundle.getString(ARG_TITLE);
        area = bundle.getString(areaFrag);
        Log.v("tot", "[area] " + area);

        if(area == "chiangRak"){
            initData(chaingRak);
        }
        else if(area == "vitya"){
            initData(vitya);
            Log.v("tot", "sci");
        }
        else if(area == "greenCanteen"){
            initData(greenCanteen);
        }
        else if(area == "sc"){
            initData(sc);
        }
        else if(area == "tiewson"){
            initData(tiewson);
        }
        else if(area == "tuDome"){
            initData(tuDome);
        }
        else if(area == "vanStation"){
            initData(vanStation);
        }
        else if(area == "hospital"){
            initData(hospital);
        }else if(area == "golfview"){
            initData(golf);
        }else if(area == "init"){
            initData(init);
        }else{
            initData(doeNot);
            Log.v("tot", "nowhere");
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