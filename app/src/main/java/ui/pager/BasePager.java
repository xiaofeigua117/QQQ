package ui.pager;

import android.app.Activity;
import android.view.View;

/**
 * Created by Lenovo on 2019/1/18.
 */
public abstract class BasePager {

    public View mRootView;
    public Activity mActivity;

    public BasePager(Activity activity) {
        super();
        // 传进来上下文
        mActivity = activity;
        mRootView = initView();

    }

    /**
     * 子类实现 每个pager页面的view 最后返回
     *
     * @return
     */
    public abstract View initView();

    /**
     * 每个pager页面访问数据的方法 不需要可以空实现
     */
    public abstract void initData(int i);
}