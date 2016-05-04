package com.university.wanstudy.user;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.university.wanstudy.R;
import com.university.wanstudy.adapters.MainPagerAdapter;
import com.university.wanstudy.fragments.VideoItemFragment;
import com.university.wanstudy.utils.SysExitUtil;
import com.university.wanstudy.view.TopBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 离线视频页面
 */
public class VideoActivity extends AppCompatActivity {

    private TopBar video_topBar;
    private TabLayout video_tab;
    private ViewPager video_viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysExitUtil.activityList.add(this);
        setContentView(R.layout.activity_video);
        initView();
    }

    private void initView() {
        //实例TopBar
        video_topBar = (TopBar) findViewById(R.id.video_topBar);
        video_topBar.setBack();
        video_topBar.setTitle("离线视频");
        //实例化导航栏
        video_tab = (TabLayout) findViewById(R.id.video_tab);
        video_tab.setTabMode(TabLayout.MODE_FIXED);
        //fragment数据源
        List<Fragment> dataList=new ArrayList<>();
        dataList.add(new VideoItemFragment());
        dataList.add(new VideoItemFragment());
        //标题数据源
        List<String> titleList=new ArrayList<>();
        titleList.add("已下载");
        titleList.add("下载中");
        //实例化适配器
        MainPagerAdapter adapter=new MainPagerAdapter(getSupportFragmentManager(),dataList,titleList);
        video_viewpager = (ViewPager) findViewById(R.id.video_viewpager);
        video_viewpager.setAdapter(adapter);
        //使ViewPager与TabLayout进行联动
        video_tab.setupWithViewPager(video_viewpager);
    }
}
