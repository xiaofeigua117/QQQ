package ui.fargment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.demo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import serialport_utils.DataSenser;
import serialport_utils.PLCSerialPortUtil;
import serialport_utils.Send_Controller;
import threads.ReadbroadThread;

/**
 * Created by Lenovo on 2018/1/4
 * 显示界面
 */

public class StateFargment extends BaseFragment {


    private static final int ONE =1 ;
    private static final int TWO = 2;

    @BindView(R.id.tv_wd)
    TextView tvWd;
    @BindView(R.id.tv_sd)
    TextView tvSd;
    @BindView(R.id.tv_kqzl)
    TextView tvKqzl;
    @BindView(R.id.tv_jq)
    TextView tvJq;
    @BindView(R.id.tv_pmt)
    TextView tvPmt;
    @BindView(R.id.tv_pms)
    TextView tvPms;
    @BindView(R.id.tv_eyht)
    TextView tvEyht;
    Unbinder unbinder;
    Message message = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ONE:
                    //更新时间
//                    String date = (String) msg.obj;
                    String[] date = (String[]) msg.obj;
                    tvTime.setText(date[7]);
                    tvWD.setText(date[5]);
                    tvSD.setText(date[4]);
                    tvKQZL.setText(date[1]);
                    tvEYHT.setText(date[0]);
                    tvJQ.setText(date[2]);
                    tvPMS.setText(date[6]);
                    tvPMT.setText(date[3]);
                    break;
                case TWO:

                    break;
            };


        }
    };
    private TextView tvTime;
    private TextView tvWD;
    private TextView tvSD;
    private TextView tvEYHT;
    private TextView tvKQZL;
    private TextView tvJQ;
    private TextView tvPMS;
    private TextView tvPMT;


    public StateFargment() {

    }


    @Override
    protected void initData() {

    }

    @Override
    protected View initView() {
        //加载界面
        View view = View.inflate(getActivity(), R.layout.fargment_state, null);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
        tvWD = (TextView) view.findViewById(R.id.tv_wd);
        tvSD = (TextView) view.findViewById(R.id.tv_sd);
        tvEYHT = (TextView) view.findViewById(R.id.tv_eyht);
        tvKQZL = (TextView) view.findViewById(R.id.tv_kqzl);
        tvJQ = (TextView) view.findViewById(R.id.tv_jq);
        tvPMS = (TextView) view.findViewById(R.id.tv_pms);
        tvPMT = (TextView) view.findViewById(R.id.tv_pmt);


        unbinder = ButterKnife.bind(this, view);
        //获取当前时间
        // HH:mm:ss



        new Thread() {
            @Override
            public void run() {
                SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                Date date = new Date(System.currentTimeMillis());
                String data = simpleDateFormat.format(date) + "分";
                Send_Controller.isClicked=1;
                Send_Controller.send_type=15;
                String[] dataSensers = DataSenser.dataSensers;
                dataSensers[7] = data;
                message = handler.obtainMessage(ONE, dataSensers);
                handler.sendMessage(message);
                handler.postDelayed(this, 30000);
            }
        }.start();




        return view;

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
