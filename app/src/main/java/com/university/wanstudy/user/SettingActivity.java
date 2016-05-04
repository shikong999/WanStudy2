package com.university.wanstudy.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.university.wanstudy.R;
import com.university.wanstudy.utils.DataCleanManager;
import com.university.wanstudy.utils.SysExitUtil;
import com.university.wanstudy.view.TopBar;

/**
 * 设置页
 */
public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private TopBar setting_topBar;
    private LinearLayout setting_clear;
    private LinearLayout setting_introduction;
    private LinearLayout setting_update;
    private Button setting_quit;
    private TextView setting_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysExitUtil.activityList.add(this);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        setting_topBar = (TopBar) findViewById(R.id.setting_topBar);
        //设置退出功能
        setting_topBar.setBack();
        //设置标题
        setting_topBar.setTitle("设置");
        //清理缓存
        setting_clear = (LinearLayout) findViewById(R.id.setting_clear);
        setting_clear.setOnClickListener(this);
        setting_data = (TextView) findViewById(R.id.setting_data);
        //获取当前缓存大小
        getCache();
        //软件介绍
        setting_introduction = (LinearLayout) findViewById(R.id.setting_introduction);
        setting_introduction.setOnClickListener(this);
        //软件升级
        setting_update = (LinearLayout) findViewById(R.id.setting_update);
        setting_update.setOnClickListener(this);
        //退出应用
        setting_quit = (Button) findViewById(R.id.setting_quit);
        setting_quit.setTextColor(Color.WHITE);
        setting_quit.setOnClickListener(this);
    }

    private void getCache() {
        //获取缓存大小
        try {
            setting_data.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_clear:
                Snackbar.make(v, "清除缓存", Snackbar.LENGTH_LONG).show();
                DataCleanManager.clearAllCache(this);
                getCache();
                break;
            case R.id.setting_introduction:
                Snackbar.make(v, "软件介绍", Snackbar.LENGTH_LONG).show();
                startActivity(new Intent(this,IntroductionActivity.class));
                break;
            case R.id.setting_update:
                Snackbar.make(v, "已是最新版本", Snackbar.LENGTH_LONG).show();
//                Toast.makeText(this, "已是最新版本", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_quit:
                Snackbar.make(v, "退出应用", Snackbar.LENGTH_LONG).show();
                SysExitUtil.exit();
                break;
        }
    }

}
