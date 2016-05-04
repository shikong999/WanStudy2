package com.university.wanstudy.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.university.wanstudy.R;
import com.university.wanstudy.utils.SysExitUtil;
import com.university.wanstudy.view.TopBar;

/**
 * 课程记录页面
 */
public class ReadingActivity extends AppCompatActivity {

    private TopBar reading_topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysExitUtil.activityList.add(this);
        setContentView(R.layout.activity_reading);
        initView();
    }

    private void initView() {
        reading_topBar = (TopBar) findViewById(R.id.reading_topBar);
        reading_topBar.setBack();
        reading_topBar.setTitle("课程阅读");
    }
}
