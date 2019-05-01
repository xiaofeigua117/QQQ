package serialport_utils;

import android.util.Log;

/**
 * Created by 10188 on 2019/1/8.
 * 板子状态
 * 读到数据和之前比较，不一样（处理），保存本次数据
 */

public class InputStatus {
    public static String input12;
    public static String input12_pre = "000000000000";
    public static int statusDoor = 0;
    public static int key_restroom = 0;
    public static int Is_opencurtain = 0;
    public static int Is_closecurtain = 0;
    public static int Is_stopCurtain = 0;
    public static boolean doorOpenFlay = true;
    public static boolean winOpenFlag = true;
    public static int Is_openwin = 0;
    public static int Is_closeWin = 0;

    public static int statusWindow = 0;
    public static int classLight = 1;
    public static int classLight_pre = 0;
    public static boolean KeyLight = false;
    public static boolean KeyLight_pre = false;
    public static int key_flag = 0;
    public static int Is_openDoor = 0;
    public static int Is_closeDoor = 0;
    public InputStatus() {
    }


    public void getInput12(String fanhuizhi) {
//        板子状态检测
        //4为数据按位转化16进制对应12输
        input12 = (String) CRC16M.hexString2binaryString(fanhuizhi).substring(4, 16);
        //比较灯开关数据
        //为灯光等级赋值
        if (!input12.substring(10, 11).equals(input12_pre.substring(10, 11))) {
            classLight++;
            if (classLight > 4) {
                classLight = 1;
            }
        }
        //为主开关赋值
        if (!input12.substring(11, 12).equals(input12_pre.substring(11, 12))) {
            KeyLight = !KeyLight;
        }
        //窗户感应\
        statusWindow = Integer.parseInt(input12.substring(6, 7));
        if (input12_pre.substring(6, 7).equals("0") && !input12_pre.substring(6, 7).equals(input12.substring(6, 7))) {

            if (doorOpenFlay) {
                Is_openDoor = 1;
            } else {
                Is_closeDoor = 1;
            }
            doorOpenFlay = !doorOpenFlay;

        }
        //门感应
        statusDoor = Integer.parseInt(input12.substring(5, 6));
//        if (!input12.substring(5, 6).equals(input12_pre.substring(5, 6))) {
//            statusDoor = 1;
//        } else {
//            statusDoor = 0;
//        }

        if (!input12.substring(9, 10).equals(input12_pre.substring(9, 10))) {
            Is_opencurtain = 1;
        } else {
            Is_opencurtain = 0;
        }
        if (!input12.substring(8, 9).equals(input12_pre.substring(8, 9))) {
            Is_closecurtain = 1;
        } else {
            Is_closecurtain = 0;
        }
        if (input12.substring(9, 10).equals("1") && input12.substring(8, 9).equals("1")) {
            Is_stopCurtain = 1;
        }
        if (input12_pre.substring(7, 8).equals("0") && !input12_pre.substring(7, 8).equals(input12.substring(7, 8))) {

            if (winOpenFlag) {
                Is_openwin = 1;
            } else {
                Is_closeWin = 1;
            }
            winOpenFlag = !winOpenFlag;

        }
        if (!input12_pre.substring(2, 3).equals(input12.substring(2, 3))) {
            key_restroom = 1;
        }
        //电动窗开关
        Log.i("input------>", "[点击灯主控" + KeyLight + " 切换灯" + classLight + "]");
        Log.i("input------>", "[点击开帘" + Is_opencurtain + " 点击了开启" + Is_closecurtain + "点击了开窗" + Is_openwin + "]");
        Log.i("input------>", "[窗户感应" + statusWindow + " 户门感应" + statusDoor + "厕所门" + key_restroom + "]");
        //厕所开关

        //厕所开关  比较复杂  需要考虑到联动
        //keyLight 为1才是开灯
        //保存新数据
        input12_pre = input12;

    }
}
