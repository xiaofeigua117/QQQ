package ui.pager;

import android.app.Activity;
import android.view.View;

import com.example.administrator.demo.R;

/**
 * Created by Lenovo on 2019/1/18.
 */

public class AtmospherePager extends BasePager {
    public AtmospherePager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_atmosphere, null);


        return view;
    }

    @Override
    public void initData(int i) {

    }


}
