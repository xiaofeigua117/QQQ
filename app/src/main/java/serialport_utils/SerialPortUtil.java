package serialport_utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android_serialport_api.SerialPort;

/**
 * 串口初始化类
 * @author blithe
 * @date 2018/2/1.
 *
 */
class SerialPortUtil {
    //串口对象
    private SerialPort serialPort1=null;
    //创建串口输出流
    private   OutputStream outputStream_se1=null;
    //创建串口入流
    private   InputStream inputStream_se1=null;
    //串口调用基地址
    private String BaseSerialPath="/dev/ttyS";
    //初始化s
    public SerialPortUtil(){}
    /**
     * 配置初始化串口1
     * @param  baudrate 串口1波特率s
     * @return  无返回
     */
     public  void setSerialport(int num,int baudrate){
         try {
            // serialPort1= new SerialPort(new File("/dev/ttyS1"), baudrate, 0);
             serialPort1= new SerialPort(new File(BaseSerialPath+num), baudrate, 0);

         //获取输出流1、向外设输出数据
         if (outputStream_se1 == null) {
             outputStream_se1 = serialPort1.getOutputStream();
         }
         //串口对象获取输入流1、接收到的数据
         if (inputStream_se1 == null) {
             inputStream_se1 = serialPort1.getInputStream();
         }
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

    /**
     * 获取串口
     * @return 返回对应串口
     *
     */
     public SerialPort getSerilport(){

         return  serialPort1;
     }

    /**
     * 测试串口打印相关信息
     * @param serialPort
     *
     */
     public    String getSerialPortInfo(SerialPort serialPort){
        return serialPort.toString();



     }
}
