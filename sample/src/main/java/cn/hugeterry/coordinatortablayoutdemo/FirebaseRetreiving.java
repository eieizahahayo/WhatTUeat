package cn.hugeterry.coordinatortablayoutdemo;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseRetreiving {
    private DatabaseReference mDatabase;
    private List<String> mDatas;
    public final String[] chaingRak = {
            "Shinkansen", "Family mart" , "Seven-eleven" , "Nana steak house" ,"Sweet duck" , "Shinya" ,"Steak holder",
            "ติ่มซำ fusion","อมรไก่ยำแซ่บ","ข้าวมันไก่ห้าสีปป้ามาลี","นายป้อม เล้งแซ่บ" ,
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
    //***********************************************************************************************************************************************************************
    // ผมสามารถดึงข้อมูลมาได้ครับแต่ว่า Lib ui ที่เลือกใช้มีปัญหาครับ
    // การดึงข้อมูลจาก firebase จำเป็นต้องดึงผ่านฟังก์ชัน onDataChange ซึ่งจะถูกเรียก"หลังสุด"เสมอ ไม่ว่าจะอยู่ตำแหน่งใดในคลาสและไม่สนว่าจะอยู่คลาสใด
    // Lib ui ตัวนี้สามารถส่งค่าไปแสดงผลได้แค่ครั้งเดียว และทำเป็นต้อง init ค่าเริ่มต้นให้ไม่เช่นนั้นแอปจะพัง
    // ทำให้ผมจำเป็นต้อง static ข้อมูลไว้ก่อน แต่หลังจากนั้นผมสามารถดึงข้อมูลมาใส่ในตัวแปรได้ ถึงแม้จะ render ใหม่อีกรอบไม่ได้ก็ตาม
    // ผม log ข้อมูลที่ได้มาไว้ใน tag "sapatawajae" นะครับ
    //***********************************************************************************************************************************************************************
    public List FirebaseRetreiving(String input, final String child){
        mDatas = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference().child(input);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if(child != "text"){
                        mDatas.add(String.valueOf(postSnapshot.child(child).child("name").getValue()));
                        Log.v("sapatawajae",String.valueOf(postSnapshot.child("name").getValue()));
                    }
                    else{
                        mDatas.add(String.valueOf(postSnapshot.child(child).getValue()));
                        Log.v("sapatawajae",String.valueOf(postSnapshot.child(child).getValue()));
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("sapatawajae", "yooooo");
            }
        });
        return mDatas;
    }
}
