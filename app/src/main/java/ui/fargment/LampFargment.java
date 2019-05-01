package ui.fargment;

import android.support.annotation.IdRes;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.demo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ui.pager.AtmospherePager;
import ui.pager.BasePager;
import ui.pager.MainPager;
import ui.pager.LightPager;
import ui.pager.NightPager;
import ui.view.NoScrollViewPager;

/**
 * Created by Lenovo on 2019/1/7.
 * 灯光
 */

public class LampFargment extends BaseFragment {

    @BindView(R.id.rb_zd)
    RadioButton rbZd;
    @BindView(R.id.rb_td)
    RadioButton rbTd;
    @BindView(R.id.rb_yd)
    RadioButton rbYd;
    @BindView(R.id.rb_fwd)
    RadioButton rbFwd;

    Unbinder unbinder;
    @BindView(R.id.rg_content)
    RadioGroup rgContent;
    private ArrayList<BasePager> mPagers;
    private NoScrollViewPager vpContent;

    @Override
    protected void initData() {

    }

    @Override
    protected View initView() {
        //加载界面
        View view = View.inflate(getActivity(), R.layout.fargment_lamp, null);
        vpContent = (NoScrollViewPager) view.findViewById(R.id.vp_content);
        unbinder = ButterKnife.bind(this, view);
        mPagers = new ArrayList<BasePager>();
        MainPager mainPager = new MainPager(mActivty);
        LightPager lightPager = new LightPager(mActivty);
        NightPager nightPager = new NightPager(mActivty);
        AtmospherePager atmospherePager = new AtmospherePager(mActivty);
        mPagers.add(mainPager);
        mPagers.add(lightPager);
        mPagers.add(nightPager);
        mPagers.add(atmospherePager);

        vpContent.setAdapter(new ContentAdapter());

        rgContent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_zd:
                        // 切换viewpager 参2 禁用滑动效果
                        vpContent.setCurrentItem(0, true);
                        break;
                    case R.id.rb_td:
                        vpContent.setCurrentItem(1, true);
                        break;
                    case R.id.rb_yd:
                        vpContent.setCurrentItem(2, true);
                        break;
                    case R.id.rb_fwd:
                        vpContent.setCurrentItem(3, true);
                        break;

                    default:
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

    @Override
    public void onStop() {
        super.onStop();

//        vpContent.setAdapter(new ContentAdapter());
    }

    @Override
    public void onResume() {
        super.onResume();

//        vpContent.setAdapter(new ContentAdapter());
    }

    private class ContentAdapter extends PagerAdapter {
        //滚动的数量
        @Override
        public int getCount() {
            return mPagers.size();
        }

        //绑定控件
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = mPagers.get(position);
            // 获取页面的view
            View view = basePager.mRootView;
            container.addView(view);
            return view;
        }

        //移除控件
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        //绑定view
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
