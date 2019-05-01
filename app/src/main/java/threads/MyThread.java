package threads;

import android.util.Log;

import serialport_utils.Send_Controller;

/**
 * Created by 10188 on 2019/1/23.
 */

class MyThread extends Thread{
    @Override
    public void run() {
        Log.e("运行了","22");
        super.run();
        while(!Thread.interrupted()){

            Log.e("30秒关闭风机","");
            try {
                for(int i=0;i<=10;i++){
                    sleep(1000);
                    Log.e("！！！！！关闭","！！"+i);
                }
                Log.e("！！！！！关闭风机","！！");
                Send_Controller.isClicked=1;
                Send_Controller.send_type=7;
                ThreadTest.stopThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }

    }
}