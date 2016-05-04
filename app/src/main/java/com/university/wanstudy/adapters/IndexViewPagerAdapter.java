package com.university.wanstudy.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkk on 2016/4/20.
 */
public class IndexViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> data;

    public IndexViewPagerAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }

    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    public void updateRes(List<Fragment> data) {
        if (data != null && data.size() > 0) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

}
