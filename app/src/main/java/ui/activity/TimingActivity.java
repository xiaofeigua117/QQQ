package ui.activity;



import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.widget.ImageView;

import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimingActivity extends BaseActivity {

    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.im_add)
    ImageView imAdd;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_timing);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.tv_edit, R.id.im_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_edit:
                finish();
                break;
            case R.id.im_add:
                getPopupWindow();
                popupWindow.showAtLocation(view, Gravity.LEFT,0,0);
                break;
        }
    }
    protected  void  initPopupWindow(){
        // TODO: 15/10/9
        //获取自定义布局文件activity_pop_left.xml 布局文件
        final View popipWindow_view = getLayoutInflater().inflate(R.layout.bottom_dialog,null,false);
        //创建Popupwindow 实例，200，LayoutParams.MATCH_PARENT 分别是宽高
        popupWindow = new PopupWindow(popipWindow_view,500, ViewGroup.LayoutParams.MATCH_PARENT,true);
      //设置动画效果
        popupWindow.setAnimationStyle(R.style.AnimationFade);
        //点击其他地方消失
        popipWindow_view.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popipWindow_view != null && popipWindow_view.isShown()) {

                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));


    }


    /**
     * 获取PopipWinsow实例
     */
    private  void  getPopupWindow(){
        if (null!=popupWindow){
            popupWindow.dismiss();
            return;
        }else {
            initPopupWindow();
        }}


}
