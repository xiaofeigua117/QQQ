package threads;

/**
 * Created by 10188 on 2019/1/23.
 */

public class ThreadTest {

    public  static MyThread mThread;

    public static void startThread(){
        mThread = new MyThread();
        mThread.start();

    }

    public static void stopThread(){
        if(mThread!=null){
            mThread.interrupt();
            mThread = null;
        }
    }

}
