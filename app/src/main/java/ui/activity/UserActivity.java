package ui.activity;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用户登录界面
 * Created by Lenovo on 2018/5/10.
 */

public class UserActivity extends BaseActivity {


    @BindView(R.id.user_title)
    TextView userTitle;
    @BindView(R.id.user_u)
    TextView userU;
    @BindView(R.id.user_mm)
    TextView userMm;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.user_mmj)
    EditText userMmj;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.button2)
    Button button2;
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置横向布局
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        TextPaint paint = userTitle.getPaint();
        paint.setFakeBoldText(true);
        TextPaint paint1 = userU.getPaint();
        paint1.setFakeBoldText(true);
        TextPaint paint2 = userMm.getPaint();
        paint2.setFakeBoldText(true);


    }

    @OnClick({R.id.checkBox, R.id.textView, R.id.login, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.checkBox:
                //记住密码
                break;
            case R.id.textView:
                //忘记密码
                break;
            case R.id.login:
                String name = editText3.getText().toString().trim();
                String pwd = userMmj.getText().toString().trim();

                if (name.equals("666666") && pwd.equals("111111")){

                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_LONG).show();

                }else {
                       Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_LONG).show();

                }
                break;
            case R.id.button2:
                finish();
                break;
        }
    }
}


