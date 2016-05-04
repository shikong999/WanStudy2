package com.university.wanstudy.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.university.wanstudy.MyApp;
import com.university.wanstudy.R;
import com.university.wanstudy.utils.SysExitUtil;
import com.university.wanstudy.view.TopBar;

/**
 * 注册页
 */
public class SignupActivity extends AppCompatActivity {

    private TopBar sign_up_topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysExitUtil.activityList.add(this);
        setContentView(R.layout.activity_signup);
        initView();
    }

    private void initView() {
        sign_up_topBar = (TopBar) findViewById(R.id.sign_up_topBar);
        sign_up_topBar.setBack();
        sign_up_topBar.setTitle("注册");
    }
}
