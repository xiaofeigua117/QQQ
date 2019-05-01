package ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import ui.activity.MainActivity;
import ui.fargment.CurtainFargment;
import ui.fargment.WindowFargment;
import ui.fargment.LampFargment;

import ui.fargment.StateFargment;

/**
 * Created by Jay on 2015/8/31 0031.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private StateFargment myState = null;
    private LampFargment myLamp = null;
    private WindowFargment myWindow = null;
    private CurtainFargment myCurtain = null;


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        myState = new StateFargment();
        myLamp = new LampFargment();
        myWindow = new WindowFargment();
        myCurtain = new CurtainFargment();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = myState;
                break;
            case MainActivity.PAGE_TWO:
                fragment = myLamp;
                break;
            case MainActivity.PAGE_THREE:
                fragment = myCurtain;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = myWindow;
                break;
        }
        return fragment;
    }


}

