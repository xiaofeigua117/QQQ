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

public class NightPager extends BasePager {
    public NightPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_night, null);
        RadioGroup rgNight = (RadioGroup) view.findViewById(R.id.rg_night);
        RadioButton  rbYOne = (RadioButton) view.findViewById(R.id.rb_tong_one);
        RadioButton rbYTwo = (RadioButton) view.findViewById(R.id.rb_tong_two);
//        initData(1);
        rgNight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_night_one:
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=16;



                        break;
                    case R.id.rb_night_two:

                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=17;
                        break;


                }
            }
        });
        return view;
    }

    @Override
    public void initData(int i) {

    }
}
