package threads;

import android.content.Context;
import android.util.Log;

import serialport_utils.OrderUtil;
import serialport_utils.OrderUtil02;
import serialport_utils.PLCSerialPortUtil;
import serialport_utils.Send_Controller;

/**
 * Created by 10188 on 2019/1/7.
 */

public class ReadbroadThread extends Thread {
    Context context;
        public ReadbroadThread(Context context){
            this.context=context;
        }
    @Override
    public void run() {
        while (true){
            Log.i("发送.....", "----------- ");
            if(Send_Controller.isClicked==1){
                OrderUtil.senOrder2(context, Send_Controller.send_type);
                Log.e("点击了",""+ Send_Controller.send_type);
                Send_Controller.isClicked=0;
                Send_Controller.send_type=0;


            }else if(Send_Controller.isClicked==2){
                OrderUtil02.senOrder2(context, Send_Controller.send_type);
                Send_Controller.isClicked=0;
                Send_Controller.send_type=0;

            }else{
                PLCSerialPortUtil.sendData(1,"030407E20001020000DE8D");
            }
        super.run();
        try {
            sleep(Send_Controller.time_readinput);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
    }
}
