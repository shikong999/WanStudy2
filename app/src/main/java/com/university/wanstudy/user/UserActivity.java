package com.university.wanstudy.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.university.wanstudy.MyApp;
import com.university.wanstudy.R;
import com.university.wanstudy.utils.SysExitUtil;
import com.university.wanstudy.view.TopBar;

/**
 * 用户页面
 */
public class UserActivity extends AppCompatActivity {

    private TopBar user_topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysExitUtil.activityList.add(this);
        setContentView(R.layout.activity_user);
        initView();
    }

    private void initView() {
        user_topBar = (TopBar) findViewById(R.id.user_topBar);
        //设置退出功能
        user_topBar.setBack();
        //设置标题
        user_topBar.setTitle("用户");
    }
}
