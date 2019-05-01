package ui.activity;


import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.demo.R;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import serialport_utils.InputStatus;
import serialport_utils.OrderUtil;
import serialport_utils.OrderUtil02;
import serialport_utils.PLCSerialPortUtil;
import serialport_utils.Send_Controller;
import serialport_utils.TransformUtils;
import threads.ThreadTest;
import ui.adapter.MyFragmentPagerAdapter;
import ui.pager.LightPager;
import ui.pager.MainPager;
import utils.Constants;
import utils.PreferenceUtils;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {

    //几个代表页面的常量
    private static int SENSER_TYPE = 12;
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    @BindView(R.id.vpager)
    ViewPager vpager;
    @BindView(R.id.rb_state)
    RadioButton rbState;
    @BindView(R.id.rb_lamp)
    RadioButton rbLamp;
    @BindView(R.id.rb_curtain)
    RadioButton rbCurtain;
    @BindView(R.id.rb_window)
    RadioButton rbWindow;
    @BindView(R.id.rg_tab_bar)
    RadioGroup rgTabBar;

    private MyFragmentPagerAdapter mAdapter;
    public MainPager mainPager;
    public LightPager lightPager;

    private int TIME = 1000;
    Handler handler = new Handler() ;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (msg.obj != null) {
                        String k1 = ((String) msg.obj).substring(11, 12);
                        String window4 = ((String) msg.obj).substring(6, 7);
                        String k2 = ((String) msg.obj).substring(10, 11);
                        String curtin1 = ((String) msg.obj).substring(9, 10);
                        String curtin2 = ((String) msg.obj).substring(8, 9);
                        String wash9 = ((String) msg.obj).substring(3, 4);
                        //判断窗户状态
                        //判断窗户状态
                        if (InputStatus.Is_opencurtain == 1) {
                            Log.i("打开了", "窗帘");
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=12;
                            InputStatus.Is_opencurtain = 0;
                        }

                        if (InputStatus.Is_closecurtain == 1) {
                            Log.i("关闭了", "窗帘");
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=14;
                            InputStatus.Is_closecurtain = 0;
                        }
                        if (InputStatus.Is_stopCurtain == 1) {
                            Send_Controller.isClicked = 1;
                            Send_Controller.send_type = 23;
                            InputStatus.Is_stopCurtain = 0;
                        }
                        //判断厕所风机状态
                        if (InputStatus.key_restroom == 1) {
                            if (ThreadTest.mThread != null) {
                                ThreadTest.stopThread();
                            }
                            Send_Controller.isClicked = 1;
                            Send_Controller.send_type = 4;

                            ThreadTest.startThread();
                            InputStatus.key_restroom = 0;
                            //注意延时
                        }
                        if (InputStatus.Is_openDoor == 1) {
                            //窗户感应
                            Send_Controller.isClicked = 1;
                            Send_Controller.send_type = 4;
                            InputStatus.Is_openDoor = 0;
                        }

                        if (InputStatus.Is_closeDoor == 1) {
                            //窗户感应
                            Send_Controller.isClicked = 1;
                            Send_Controller.send_type = 3;
                            InputStatus.Is_closeDoor = 0;
                        }
                        //判断打开窗户
                        if (InputStatus.Is_openwin == 1) {
                            //open 开窗
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=9;
                            InputStatus.Is_openwin = 0;
                        }
                        if (InputStatus.Is_closeWin == 1) {
                            //关闭窗户
                            Send_Controller.isClicked=1;
                            Send_Controller.send_type=11;
                            InputStatus.Is_closeWin = 0;
                        }

                        if (InputStatus.KeyLight == true) {
//                            I1.setBackgroundColor(Color.rgb(255,0,0));
                            if (InputStatus.KeyLight != InputStatus.KeyLight_pre) {
                                Send_Controller.isClicked = 1;
                                Send_Controller.send_type = 1;
                                InputStatus.classLight=1;
                                InputStatus.KeyLight_pre = InputStatus.KeyLight;
                            }

                        } else if (InputStatus.KeyLight == false) {
//                            I1.setBackgroundColor(Color.rgb(196,196,196));
                            if (InputStatus.KeyLight != InputStatus.KeyLight_pre) {
                                Send_Controller.isClicked = 1;
                                Send_Controller.send_type = 6;
                                InputStatus.KeyLight_pre = InputStatus.KeyLight;
                            }
                        }
//                  if (k2.equals("1")) {
//                            I2.setBackgroundColor(Color.rgb(255,0,0));
//                        } else if (k2.equals("0")) {
//                            I2.setBackgroundColor(Color.rgb(196,196,196));
//                        }
                        if (InputStatus.KeyLight == true) {

                            // Send_Controller.isClicked=1;
                            // Send_Controller.send_type=2;
                            Log.i("Class", InputStatus.classLight + " ");
                            switch (InputStatus.classLight) {

                                case 1:

                                    PreferenceUtils.putInt(getApplicationContext(),
                                            Constants.LIGHT_UPDATE,1);
//                                    mainPager.initView();
                                    if (InputStatus.classLight != InputStatus.classLight_pre) {
                                        Send_Controller.isClicked = 1;
                                        Send_Controller.send_type = 1;
                                        InputStatus.classLight_pre = InputStatus.classLight;
                                        Log.i("一级灯", "....");
                                    }
                                    break;
                                case 2:
                                    PreferenceUtils.putInt(getApplicationContext(),
                                            Constants.LIGHT_UPDATE,2);
//                                    mainPager.initView();
                                    if (InputStatus.classLight != InputStatus.classLight_pre) {
                                        Send_Controller.isClicked = 1;
                                        Send_Controller.send_type = 2;
                                        InputStatus.classLight_pre = InputStatus.classLight;
                                        Log.i("二级灯", "....");
                                    }
                                    break;
                                case 3:
                                    PreferenceUtils.putInt(getApplicationContext(),
                                            Constants.LIGHT_UPDATE,3);
//                                    mainPager.initView();
                                    if (InputStatus.classLight != InputStatus.classLight_pre) {
                                        Send_Controller.isClicked = 1;
                                        Send_Controller.send_type = 3;
                                        InputStatus.classLight_pre = InputStatus.classLight;
                                        Log.i("三级灯", "....");
                                    }
                                    break;
                                case 4:

                                    PreferenceUtils.putInt(getApplicationContext(),
                                            Constants.LIGHT_UPDATE,4);
                                    if (InputStatus.classLight != InputStatus.classLight_pre) {
                                        Send_Controller.isClicked = 1;
                                        Send_Controller.send_type = 4;
                                        InputStatus.classLight_pre = InputStatus.classLight;
                                        Log.i("四级灯", "....");
                                    }
                                    break;
//                                case 5:
//
//                                    PreferenceUtils.putInt(getApplicationContext(),
//                                            Constants.LIGHT_UPDATE,5);
//                                    if (InputStatus.classLight != InputStatus.classLight_pre) {
//                                        Send_Controller.isClicked = 1;
//                                        Send_Controller.send_type = 5;
//                                        InputStatus.classLight_pre = InputStatus.classLight;
//                                        Log.i("5级", "....");
//                                    }
//                                    break;

                            }
                        } else if (InputStatus.KeyLight == false) {
                            //  Send_Controller.isClicked=1;
                            //  Send_Controller.send_type=1;

                        }
                    }

                    break;
            }
        }
    };
    Runnable r = new Runnable() {

        @Override
        public void run() {
            if (Send_Controller.isClicked == 1) {
                //发送
                Log.e("线程发送", "------------- " + Send_Controller.send_type);
                OrderUtil.senOrder2(getApplication(), Send_Controller.send_type);
                Log.e("点击了", "" + Send_Controller.send_type);
                Send_Controller.isClicked = 0;
                Send_Controller.send_type = 0;

//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            } else if (Send_Controller.isClicked == 2) {
                OrderUtil02.senOrder2(getApplication(), Send_Controller.send_type);
                Send_Controller.isClicked = 0;
                Send_Controller.send_type = 0;

            } else {
                Log.e("线程发送", "-------------");
                PLCSerialPortUtil.sendData(1, "030407E20001020000DE8D");

            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mHandler.postDelayed(this, 100);
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //设置横向布局
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //1，添加容器
        PLCSerialPortUtil.getSerilport_PLC(7, 115200);
        try {
            PLCSerialPortUtil.outputStream_plc.write(TransformUtils.hexStringToBytes(TransformUtils.encode("S1:9600")));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        new ReadbroadThread(this).start();
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        //2，加载
        bindViews();
        //初始化串口

        handler.postDelayed(r, 100);

        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Message message = new Message();
                        message.obj = InputStatus.input12;
                        message.what = 1;
                        mHandler.sendMessage(message);
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();



        //设置32uart波特率
        try {
            PLCSerialPortUtil.outputStream_plc.write(TransformUtils.hexStringToBytes(TransformUtils.encode("S1:9600")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //打开发送的线程持续与板子通信
        rbState.setChecked(true);
        //3，创建数据库，数据库创建。

        mainPager = new MainPager(this);
        lightPager = new LightPager(this);


    }

    private void bindViews() {
        rgTabBar.setOnCheckedChangeListener(this);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_state:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_lamp:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_curtain:
                vpager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.rb_window:
                vpager.setCurrentItem(PAGE_FOUR);
                break;
        }
    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rbState.setChecked(true);
                    break;
                case PAGE_TWO:
                    rbLamp.setChecked(true);
                    break;
                case PAGE_THREE:
                    rbCurtain.setChecked(true);
                    break;
                case PAGE_FOUR:
                    rbWindow.setChecked(true);
                    break;
            }
        }
    }

//    private void SendScheduledThreadPool() {
//
//        ScheduledExecutorService scheduledThreadPool = Executors.newSingleThreadScheduledExecutor();
//        //Test2:延迟执行，并且周期性执行
//        /**
//         * 第一个参数是Runnable，第二个参数是延迟时间，第三个参数是巡行间隔，第四个参数是时间的单位
//         */
//        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                //传感器发送
//                PLCSerialPortUtil.sendData(SENSER_TYPE, "0103000000070408");
//                Log.e("", "发送 ");
//            }
//        }, 2, 3, TimeUnit.SECONDS);
//
//        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                //接受
//
//
//            }
//        }, 2, 3, TimeUnit.SECONDS);
//
//
//    }

}
