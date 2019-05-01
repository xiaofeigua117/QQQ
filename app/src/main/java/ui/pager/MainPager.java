package ui.pager;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.demo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import serialport_utils.DataSenser;
import serialport_utils.Send_Controller;
import utils.Constants;
import utils.PreferenceUtils;

import static serialport_utils.DataSenser.data;

/**
 * Created by Lenovo on 2019/1/18.
 */

public class MainPager extends BasePager {
    @BindView(R.id.ll_mainpager)
    LinearLayout llMainpager;
    public RadioButton rbMainOne;
    public RadioButton rbMainTwo;
    public RadioButton rbMainThree;
    public RadioButton rbMainFour;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
               //更细状态

                    int data = (int) msg.obj;
                    boolean aBoolean = PreferenceUtils.getBoolean(mActivity, Constants.LIGHT_TRUE, false);
                    if (aBoolean) {
                        switch (data) {
                            case 0:
//                            rbMainOne.setChecked(true);
                                rbMainOne.setBackgroundResource(R.drawable.lamp_two_pre);
                                break;
                            case 1:
//                            rbMainTwo.setChecked(true);
                                rbMainTwo.setBackgroundResource(R.drawable.lamp_two_pre);
                                rbMainThree.setBackgroundResource(R.drawable.lamp_two);
                                rbMainFour.setBackgroundResource(R.drawable.lamp_two);
                                break;
                            case 2:

                                rbMainTwo.setBackgroundResource(R.drawable.lamp_two);
                                rbMainThree.setBackgroundResource(R.drawable.lamp_two_pre);
                                rbMainFour.setBackgroundResource(R.drawable.lamp_two);
                                break;
                            case 3:
                                rbMainTwo.setBackgroundResource(R.drawable.lamp_two);
                                rbMainThree.setBackgroundResource(R.drawable.lamp_two);
                                rbMainFour.setBackgroundResource(R.drawable.lamp_two_pre);

                                break;

                        }


                        break;
                    } else {
                        rbMainTwo.setBackgroundResource(R.drawable.lamp_two);
                        rbMainThree.setBackgroundResource(R.drawable.lamp_two);
                        rbMainFour.setBackgroundResource(R.drawable.lamp_two);

                    }

                case 2:

                    break;
            };


        }
    };
    public MainPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_main, null);
        RadioGroup rgMain = (RadioGroup) view.findViewById(R.id.rg_mian);
        rbMainOne = (RadioButton) view.findViewById(R.id.rb_main_one);
        rbMainTwo = (RadioButton) view.findViewById(R.id.rb_main_two);
        rbMainThree = (RadioButton) view.findViewById(R.id.rb_main_three);
        rbMainFour = (RadioButton) view.findViewById(R.id.rb_main_four);





        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_main_one:

                        Toast.makeText(mActivity, "关灯", Toast.LENGTH_SHORT).show();
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=24;

                        break;
                    case R.id.rb_main_two:

                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=1;
                        break;
                    case R.id.rb_main_three:


                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=2;

                        break;

                    case R.id.rb_main_four:

                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=3;

                        break;
                    case R.id.rb_main_five:
                        PreferenceUtils.putBoolean(mActivity, Constants.LIGHT_TRUE, false);
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=18;

                        break;
                    case R.id.rb_main_six:
                        PreferenceUtils.putBoolean(mActivity, Constants.LIGHT_TRUE, false);
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=19;

                        break;
                    case R.id.rb_main_seven:
                        PreferenceUtils.putBoolean(mActivity, Constants.LIGHT_TRUE, false);
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=20;

                        break;
                    case R.id.rb_main_eight:
                        PreferenceUtils.putBoolean(mActivity, Constants.LIGHT_TRUE, false);
                        rbMainTwo.setBackgroundResource(R.drawable.lamp_two);
                        rbMainThree.setBackgroundResource(R.drawable.lamp_two);
                        rbMainFour.setBackgroundResource(R.drawable.lamp_two);
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=21;

                        break;

                }
            }
        });
//        new Thread() {
//            @Override
//            public void run() {
//
//                int lightData = PreferenceUtils.getInt(mActivity, Constants.LIGHT_UPDATE, 1);
//
//                Log.e("PreferenceUtils", " "+lightData);
//                Message message = handler.obtainMessage(1, lightData);
//                handler.sendMessage(message);
//                handler.postDelayed(this, 100);
//            }
//        }.start();
        return view;
    }




    public void initData(int i) {
        Log.e("PreferenceUtils", " 我被执行了");
        switch (i) {
            case 1:
                rbMainOne.setChecked(true);
                break;
            case 2:
                rbMainTwo.isChecked();
                rbMainTwo.setChecked(true);
                break;
            case 3:
                rbMainThree.setChecked(true);
                rbMainThree.isChecked();

                break;
            case 4:
                rbMainFour.isChecked();
                rbMainFour.setChecked(true);

                break;
        }
    }
}
