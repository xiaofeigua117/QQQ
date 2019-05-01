package ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo.R;

import java.io.IOException;

import serialport_utils.DataSenser;
import serialport_utils.InputStatus;
import serialport_utils.OrderUtil;
import serialport_utils.PLCSerialPortUtil;
import serialport_utils.Send_Controller;
import serialport_utils.TransformUtils;
import threads.HelloTest;
import threads.ReadbroadThread;
import threads.ThreadTest;


public class TestActivity extends AppCompatActivity {

    Context context=null;
    TextView I1, I2,I7,I8,o1,o2,o3,o4,o5,o6,o10,o11;
    private ListView lists;
    String [] items ={"主灯一级","主灯2级","主灯3级","筒灯 ","窗帘开","窗帘停止","窗帘关","夜灯开","窗户开","窗户停止","窗户关","读取房间数据","设置波特率","远程开锁","远程关锁","切换扬声器1","切换扬声器2","开始读取板子数据"};
    ArrayAdapter<String> adapter;


   Handler mHandler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           switch (msg.what){
               case 1:
                   if(msg.obj!=null){
                       String k1=((String) msg.obj).substring(11,12);
                       String window4=((String) msg.obj).substring(6,7);
                       String k2=((String)msg.obj).substring(10,11);
                       String curtin1=((String)msg.obj).substring(9,10);
                       String curtin2=((String)msg.obj).substring(8,9);
                       String wash9=((String) msg.obj).substring(3,4);
                   //判断窗户状态
                      //判断窗户状态
                       if(InputStatus.Is_opencurtain==1){
                           Log.i("打开了","打开了阿欢要挂了");
                           Send_Controller.isClicked=1;
                           Send_Controller.send_type=21;
                           InputStatus.Is_opencurtain=0;
                       }if(InputStatus.Is_closecurtain==1){
                           Log.i("关闭了","打开了阿欢要挂了");
                           Send_Controller.isClicked=1;
                           Send_Controller.send_type=22;
                           InputStatus.Is_closecurtain=0;
                       }
                       if(InputStatus.Is_stopCurtain==1){
                           Send_Controller.isClicked=1;
                           Send_Controller.send_type=23;
                           InputStatus.Is_stopCurtain=0;
                       }
                       //判断厕所风机状态
                       if(InputStatus.key_restroom==1){
                          if(ThreadTest.mThread!=null){
                              ThreadTest.stopThread();
                          }
                           Send_Controller.isClicked=1;
                           Send_Controller.send_type=4;

                           ThreadTest.startThread();
                           InputStatus.key_restroom=0;
                           //注意延时
                       }
                       //判断打开窗户
                       if(InputStatus.Is_openwin==1){
                           Send_Controller.isClicked=2;
                           Send_Controller.send_type=1;
                           InputStatus.Is_openwin=0;
                       }if(InputStatus.Is_closeWin==1){
                           Send_Controller.isClicked=2;
                           Send_Controller.send_type=2;
                           InputStatus.Is_closeWin=0;
                       }

                       if(InputStatus.KeyLight==true){
                           I1.setBackgroundColor(Color.rgb(255,0,0));
                          if(InputStatus.KeyLight!= InputStatus.KeyLight_pre){
                              Send_Controller.isClicked=1;
                              Send_Controller.send_type= InputStatus.classLight;
                              InputStatus.KeyLight_pre= InputStatus.KeyLight;
                          }

                       }else if(InputStatus.KeyLight==false){
                           I1.setBackgroundColor(Color.rgb(196,196,196));
                           if(InputStatus.KeyLight!= InputStatus.KeyLight_pre){
                               Send_Controller.isClicked=1;
                               Send_Controller.send_type=6;
                               InputStatus.classLight=1;
                               InputStatus.KeyLight_pre= InputStatus.KeyLight;
                           }
                       }
//                       if(k1.equals("1")){
//                           I1.setBackgroundColor(Color.rgb(255,0,0));
//                       }else if(k1.equals("0")){
//                           I1.setBackgroundColor(Color.rgb(196,196,196));
//                       }
                        if(k2.equals("1")){
                           I2.setBackgroundColor(Color.rgb(255,0,0));
                       }else if(k2.equals("0")){
                           I2.setBackgroundColor(Color.rgb(196,196,196));
                       }
                        if(InputStatus.KeyLight==true){

                           // Send_Controller.isClicked=1;
                           // Send_Controller.send_type=2;
                            Log.i("Class", InputStatus.classLight+" ");
                            switch (InputStatus.classLight){

                                case 1:
                                    setColor(o1);

                                    if(InputStatus.classLight!= InputStatus.classLight_pre){
                                        Send_Controller.isClicked=1;
                                        Send_Controller.send_type=1;
                                        InputStatus.classLight_pre= InputStatus.classLight;
                                        Log.i("一级灯","....");
                                    }
                                    break;
                                case 2:
                                    setColor(o2);
                                    if(InputStatus.classLight!= InputStatus.classLight_pre){
                                        Send_Controller.isClicked=1;
                                        Send_Controller.send_type=2;
                                        InputStatus.classLight_pre= InputStatus.classLight;
                                        Log.i("二级灯","....");
                                    }
                                    break;
                                case 3:
                                    setColor(o3);
                                    if(InputStatus.classLight!= InputStatus.classLight_pre){
                                        Send_Controller.isClicked=1;
                                        Send_Controller.send_type=3;
                                        InputStatus.classLight_pre= InputStatus.classLight;
                                        Log.i("三级灯","....");
                                    }
                                    break;
                                case 4:
                                    setColor(o4);
                                    if(InputStatus.classLight!= InputStatus.classLight_pre){
                                        Send_Controller.isClicked=1;
                                        Send_Controller.send_type=4;
                                        InputStatus.classLight_pre= InputStatus.classLight;
                                        Log.i("四级灯","....");
                                    }
                                    break;
                                case 5:
                                    setColor(o5);
                                    if(InputStatus.classLight!= InputStatus.classLight_pre){
                                        Send_Controller.isClicked=1;
                                        Send_Controller.send_type=5;
                                        InputStatus.classLight_pre= InputStatus.classLight;
                                        Log.i("四级灯","....");
                                    }
                                    break;

                            }
                        }else if(InputStatus.KeyLight==false){
                                  //  Send_Controller.isClicked=1;
                                  //  Send_Controller.send_type=1;
                                    cancleColor();
                        }
                   }

                   break;
           }
       }
   };
    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
        }}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        setTitle("功能测试列表");
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,items);
        lists=(ListView)findViewById(R.id.lists);
        I1= (TextView)findViewById(R.id.l1);
        I2= (TextView)findViewById(R.id.l2);
        I7= (TextView)findViewById(R.id.l7);
        I8= (TextView)findViewById(R.id.l8);

        o1=(TextView)findViewById(R.id.o1);
        o2=(TextView)findViewById(R.id.o2);
        o3=(TextView)findViewById(R.id.o3);
        o4=(TextView)findViewById(R.id.o4);
        o5=(TextView)findViewById(R.id.o5);
        o6=(TextView)findViewById(R.id.o6);
        o10=(TextView)findViewById(R.id.o10);
        o11=(TextView)findViewById(R.id.o11);

        lists.setAdapter(adapter);
        context=this;


        //释放的晚一点
    //初始化串口

        PLCSerialPortUtil.getSerilport_PLC(7,115200);

        new ReadbroadThread(context).start();
        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("第"+(i+1),i+"");
                switch (i+1){
                    case 1:
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=1;
                        break;
                    case 2:
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=2;
                        break;
                    case 3:
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=3;
                        break;
                    case 4:
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=4;
                        break;
                    case 5:
                        //窗帘开
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=12;

                        break;
                    case 6:
//                        窗帘停止
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=13;

                        break;

                    case 7:
                        //窗帘关
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=14;

                        break;

                    case 8:
                        //夜灯开
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=7;

                        break;

                    case 9:
                        //窗户开
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=9;
                        break;
                    case 10:
                        //窗户停止
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=10;
                        break;
                    case 11:
//                        窗户关
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=11;
                        break;
                    case 12:
                        Send_Controller.isClicked=1;

                        Send_Controller.send_type=15;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
                        builder.setTitle("传感器数据")
                                //.setIcon(R
                                .setItems(DataSenser.dataSensers, new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(TestActivity.this, DataSenser.dataSensers[which], Toast.LENGTH_SHORT).show();
                                        Log.i("abc", "i" + which);
                                    }
                                }).show();
                        break;
                    case 13:
                        //设置32uart波特率
                        try {
                            PLCSerialPortUtil.outputStream_plc.write(TransformUtils.hexStringToBytes(TransformUtils.encode("S1:9600")));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 14:
                        OrderUtil.senOrder2(context,12);

                        break;
                    case 15:
                        OrderUtil.senOrder2(context,13);

                        break;
                    case 16:
                        //切换扬声器
                        HelloTest.getStart(1);
                        break;
                    case 17:
                        HelloTest.getStart(0);
                        break;
                    case 18:
                        //开始读取板子输入

                        new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                while (true){
                                    try {
                                        Message message=new Message();
                                        message.obj= InputStatus.input12;
                                        message.what=1;
                                        mHandler.sendMessage(message);
                                        sleep(200);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }.start();
                        break;
                }


            }
        });


    }
    public void setColor(View view){
        cancleColor();
        view.setBackgroundColor(Color.rgb(255,0,0));
    }
    public void cancleColor(){
        o1.setBackgroundColor(Color.rgb(196,221,170));
        o2.setBackgroundColor(Color.rgb(196,221,170));
        o3.setBackgroundColor(Color.rgb(196,221,170));
        o4.setBackgroundColor(Color.rgb(196,221,170));
        o5.setBackgroundColor(Color.rgb(196,221,170));
        o6.setBackgroundColor(Color.rgb(196,221,170));


    }

}
