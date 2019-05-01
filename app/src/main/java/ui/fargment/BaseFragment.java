package ui.fargment;

import android.app.Activity;
import android.icu.util.ValueIterator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by HASEE on 2017/2/5.
 */
public  abstract class BaseFragment extends Fragment{

    protected Activity mActivty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取依赖的activity
        mActivty = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 子类返回布局的view
        View view = initView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 子类初始化数据 没有可以空实现
     */
    protected abstract void initData();

    /**
     * 子类必须实现 并且返回布局的view
     *
     * @return
     */
    protected abstract View initView();
}

