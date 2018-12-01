package cn.hugeterry.coordinatortablayoutdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

class SensorInfo {
    private float x = 0;
    private float y = 0;
    private float z = 0;
    private int shake_threshold = 10;
    private int index;
    private boolean showDialog;
    private String area = "";
    MainFragment mFragments = new MainFragment();

    public void setSensor(float x, float y, float z, String area) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.area = area;
    }

    public void showDialog(Context context) {
        if((Math.abs(x) > shake_threshold) || (Math.abs(y) > shake_threshold) || (Math.abs(z) > shake_threshold)) {
            if(!showDialog) {
                showDialog = true;


                final AlertDialog.Builder viewDialog = new AlertDialog.Builder(context);

                if(area == "chiangRak"){
                    index = (int)(Math.random() * (mFragments.chaingRak.length - 1));
                    viewDialog.setTitle("สถานที่ " + area);
                    viewDialog.setMessage("ร้านอาหารที่ได้ " + mFragments.chaingRak[index]);
                }
                else if(area == "vitya"){
                    index = (int)(Math.random() * (mFragments.vitya.length - 1));
                    viewDialog.setTitle("สถานที่ " + area);
                    viewDialog.setMessage("ร้านอาหารที่ได้ " + mFragments.vitya[index]);

                }
                else if(area == "greenCanteen"){
                    index = (int)(Math.random() * (mFragments.greenCanteen.length - 1));
                    viewDialog.setTitle("สถานที่ " + area);
                    viewDialog.setMessage("ร้านอาหารที่ได้ " + mFragments.greenCanteen[index]);

                }
                else if(area == "sc"){
                    index = (int)(Math.random() * (mFragments.sc.length - 1));
                    viewDialog.setTitle("สถานที่ " + area);
                    viewDialog.setMessage("ร้านอาหารที่ได้ " + mFragments.sc[index]);

                }
                else if(area == "tiewson"){
                    index = (int)(Math.random() * (mFragments.tiewson.length - 1));
                    viewDialog.setTitle("สถานที่ " + area);
                    viewDialog.setMessage("ร้านอาหารที่ได้ " + mFragments.tiewson[index]);

                }
                else if(area == "tuDome"){
                    index = (int)(Math.random() * (mFragments.tuDome.length - 1));
                    viewDialog.setTitle("สถานที่ " + area);
                    viewDialog.setMessage("ร้านอาหารที่ได้ " + mFragments.tuDome[index]);

                }
                else if(area == "vanStation"){
                    index = (int)(Math.random() * (mFragments.vanStation.length - 1));
                    viewDialog.setTitle("สถานที่ " + area);
                    viewDialog.setMessage("ร้านอาหารที่ได้ " + mFragments.vanStation[index]);

                }
                else if(area == "hospital"){
                    index = (int)(Math.random() * (mFragments.hospital.length - 1));
                    viewDialog.setTitle("สถานที่ " + area);
                    viewDialog.setMessage("ร้านอาหารที่ได้ " + mFragments.hospital[index]);

                }else if(area == "golfview"){
//                    index = (int)(Math.random() * (mFragments.golf.length - 1));
                    viewDialog.setTitle("สถานที่ " + area);
                    viewDialog.setMessage("ร้านอาหารที่ได้ " + mFragments.chaingRak[index]);

                }else if(area == "init"){
                    viewDialog.setTitle("ขอต้อนรับสู่ What TU eat");
                    viewDialog.setMessage(
                            "1. เดินเพื่อระบุตำแหน่ง\n" +
                            "2. เขย่าเพื่อสุ่มร้านอาหาร");

                }else{
                    index = (int)(Math.random() * (mFragments.doeNot.length - 1));
                    viewDialog.setMessage("ร้านอาหารที่ได้ " + mFragments.doeNot[index]);

                }

                viewDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showDialog = false;
                    }
                });
                viewDialog.show();
            }
        }
    }
}
