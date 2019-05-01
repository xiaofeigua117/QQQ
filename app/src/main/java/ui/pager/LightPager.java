package ui.pager;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.demo.R;

import serialport_utils.Send_Controller;

/**
 * Created by Lenovo on 2019/1/18.
 */

public class LightPager extends BasePager {

    public RadioButton rbTOne;
    public  RadioButton rbTTwo;

    public LightPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_light, null);
        RadioGroup rgTong = (RadioGroup) view.findViewById(R.id.rg_tong);
        rbTOne = (RadioButton) view.findViewById(R.id.rb_tong_one);
        rbTTwo = (RadioButton) view.findViewById(R.id.rb_tong_two);
//        initData(1);
        rgTong.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_tong_one:
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=22;



                        break;
                    case R.id.rb_tong_two:
//                        Toast.makeText(mActivity, "关灯", Toast.LENGTH_SHORT).show();
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=23;
                        break;


                }
            }
        });
        return view;
    }

    @Override
    public void initData(int i) {
        switch (i) {
            case 1:
                    rbTOne.setChecked(true);
                break;
            case 2:

                break;

        }
    }


}
