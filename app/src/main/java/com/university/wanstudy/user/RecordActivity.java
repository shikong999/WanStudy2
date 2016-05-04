package com.university.wanstudy.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.university.wanstudy.R;
import com.university.wanstudy.utils.SysExitUtil;
import com.university.wanstudy.view.TopBar;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener {

    private TopBar record_topBar;
    private TextView record_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        SysExitUtil.activityList.add(this);
        initView();
    }

    private void initView() {
        record_topBar = (TopBar) findViewById(R.id.record_topBar);
        record_topBar.setBack();
        record_topBar.setTitle("播放记录");
        record_clear = (TextView) findViewById(R.id.record_clear);
        record_clear.setTextColor(Color.WHITE);
        record_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.record_clear:
                Snackbar.make(v, "播放记录已清空", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
