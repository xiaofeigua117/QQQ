package serialport_utils;

import android.content.Context;
import android.util.Log;



import java.io.InputStream;
import java.io.OutputStream;

import android_serialport_api.SerialPort;
import threads.InputThread;
/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 *            佛曰:
 *                   写字楼里写字间，写字间里程序员；
 *                   程序人员写程序，又拿程序换酒钱。
 *                   酒醒只在网上坐，酒醉还来网下眠；
 *                   酒醉酒醒日复日，网上网下年复年。
 *                   但愿老死电脑间，不愿鞠躬老板前；
 *                   奔驰宝马贵者趣，公交自行程序员。
 *                   别人笑我忒疯癫，我笑自己命太贱；
 *                   不见满街漂亮妹，哪个归得程序员？
*/

/**
 * 串口操作类PLC
 * 设置PLC通信串口
 * @author  blithe
 * @date 2018/2/1
 */

public class PLCSerialPortUtil {
    //设置串口
    public static SerialPort serialPort_PLC;
    //设置是否发送标志
    public static boolean CheckSendFlag = false;
    //设置此串口接收线程
    public static Thread inputThread;

    //创建PLC输出流
    public static OutputStream outputStream_plc=null;
    //创建PLC输入流
    public static InputStream inputStream_plc=null;

    /**
     * 配置获取PLC串口，设置串口号，和波特率 ,设置输入输出，调用串口必须先实例化此方法
     *
     * 测试加入接收线程启动。
     *
     * @param num      plc串口号
     * @param baudrate 设置plc串口波特率
     * @return 返回plc串口
     */
    public static SerialPort getSerilport_PLC(int num, int baudrate) {
        //初始化串口
        SerialPortUtil serialportUtil = new SerialPortUtil();
        //相关串口设置参数,x选择响应的串口和波特率
        serialportUtil.setSerialport(num, baudrate);
        //实例化plc串口
        serialPort_PLC = serialportUtil.getSerilport();
        //获取PLC输入流
        inputStream_plc=serialPort_PLC.getInputStream();
        //获取plc输出流
        outputStream_plc=serialPort_PLC.getOutputStream();
        //测试线程
        inputThread = new InputThread(inputStream_plc);
        //测试加入接收线程开启
        inputThread.start();

        return serialPort_PLC;
    }


    /**
     * 写入数据
     *
     * @param type 发送类型，自定义判断
     *
     * @param data  发送的数据
     */
    public static void sendData(int type,String data){
        //初始化字节数组
        String o16= TransformUtils.encode("T1:");
        Log.i("16进制",o16+data);
        byte[] outs= TransformUtils.hexStringToBytes(o16+data);
        try {
            outputStream_plc.write(outs);
            outputStream_plc.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sendData_T2(int type,String data){
        //初始化字节数组
        String o16= TransformUtils.encode("T2:");

        Log.i("16进制",o16+data);
        byte[] outs= TransformUtils.hexStringToBytes(o16+data);
        try {
            outputStream_plc.write(outs);
            outputStream_plc.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sendData_T3(int type,String data){
        //初始化字节数组
        String o16= TransformUtils.encode("T3:");

        Log.i("16进制",TransformUtils.encode("1111"));
        byte[] outs= TransformUtils.hexStringToBytes(o16+data);
        try {
            outputStream_plc.write(outs);
            outputStream_plc.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sendData_T4(int type,String data){
        //初始化字节数组
        String o16= TransformUtils.encode("T4:");
        Log.i("16进制",o16+data);
        byte[] outs= TransformUtils.hexStringToBytes(o16+data);
        try {
            outputStream_plc.write(outs);
            outputStream_plc.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 写入数据以字符串方式写入，转换为ascii码发送
     *
     * @param type 发送类型，自定义判断
     *
     * @param data  发送的数据
     */

    public static void sendData2(int type,String data){
        //初始化字节数组
        byte[] outs=data.getBytes();
        //
        //String ordername= OrderHandleUtil.getTypeName(type);
        try {
            outputStream_plc.write(outs);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /****
     * 开始传感器接收
     * @param context
     */
    public static void startSenseReceive(Context context){
         //初始化一个串口S1为传感器串口
    }
    /**
     * plc 读取板子输入状态
     * @param
     */
    public static void getPLCInputStatus(String[] param){

    }
}
