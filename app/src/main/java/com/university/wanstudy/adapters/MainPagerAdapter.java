package com.university.wanstudy.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * TabLayout与ViewPager联动的适配器
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> data;
    private List<String> titles;

    public MainPagerAdapter(FragmentManager fm, List<Fragment> data, List<String> titles) {
        super(fm);
        this.data = data;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    /**
     * 想要与TabLayout联动,需要重写getPageTitle()方法,
     * @param position
     * @return  作为Fragment对应的标题
     */
    @Override
    public CharSequence getPageTitle(int position) {

        return titles.get(position);
    }
}

