package ui.fargment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import serialport_utils.OrderUtil;
import serialport_utils.Send_Controller;


/**
 * Created by Lenovo on 2019/1/7.
 * 窗户
 */

public class WindowFargment extends BaseFragment {

    @BindView(R.id.window)
    ImageView window;
    //    @BindView(R.id.window_open)
//    Button windowOpen;
//    @BindView(R.id.window_out)
//    Button windowOut;
//    @BindView(R.id.window_close)
//    Button windowClose;
    Unbinder unbinder;
    @BindView(R.id.rb_windowkai)
    RadioButton rbWindowkai;
    @BindView(R.id.rb_windowting)
    RadioButton rbWindowting;
    @BindView(R.id.rb_windowguan)
    RadioButton rbWindowguan;
    @BindView(R.id.ll_round)
    LinearLayout llRound;
    private RadioGroup rgWindow;


    @Override
    protected void initData() {

    }

    @Override
    protected View initView() {
        //加载界面
        View view = View.inflate(getActivity(), R.layout.fargment_window, null);

        unbinder = ButterKnife.bind(this, view);
        rgWindow = (RadioGroup) view.findViewById(R.id.rg_window);
        for (int i = 0; i < 3; i++) {
            // 添加下面的小圆点
            ImageView grayRoundView = new ImageView(getContext());

            // 设置灰色的圆图
            grayRoundView.setImageResource(R.drawable.gray_round);
            // 添加小圆点的距离
            if (i != 0) {
                // 生成布局参数
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.leftMargin = 15;// 设置左边距
                // 设置布局参数
                grayRoundView.setLayoutParams(layoutParams);
            }

            // 添加到布局
            llRound.addView(grayRoundView);
        }

        rgWindow.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_windowkai:

                        //窗户开
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=9;
//                        OrderUtil.senOrder2(getContext(),9);


                        break;
                    case R.id.rb_windowting:

                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=10;
                        break;
                    case R.id.rb_windowguan:

                        // 窗户关
                        Send_Controller.isClicked=1;
                        Send_Controller.send_type=11;
//                        OrderUtil.senOrder2(getContext(),11);
                        break;

                }

            }
        });
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.ll_round)
    public void onViewClicked() {
    }

//    @OnClick({R.id.window_open, R.id.window_out, R.id.window_close})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.window_open:
//                 Toast.makeText(getActivity(), "窗户开启", Toast.LENGTH_LONG).show();
//
//                window.setBackgroundResource(R.drawable.window1_anim);
//                AnimationDrawable windowBackGround = (AnimationDrawable) window.getBackground();
//                windowBackGround.start();
//                break;
//            case R.id.window_out:
//                break;
//            case R.id.window_close:
//                Toast.makeText(getActivity(), "窗户关闭", Toast.LENGTH_LONG).show();
//
//                window.setBackgroundResource(R.drawable.window_anim);
//                AnimationDrawable windowBackGround1 = (AnimationDrawable) window.getBackground();
//                windowBackGround1.start();
//                break;
//        }
//    }
}
