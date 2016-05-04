package com.university.wanstudy.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.university.wanstudy.R;
import com.university.wanstudy.utils.SysExitUtil;
import com.university.wanstudy.view.TopBar;

/**
 * 留言反馈页面
 */
public class ChattingActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ChattingActivity.class.getSimpleName();
    private TopBar chatting_topBar;
    private Button chatting_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        SysExitUtil.activityList.add(this);
        initView();
    }

    private void initView() {
        chatting_topBar = (TopBar) findViewById(R.id.chatting_topBar);
        chatting_topBar.setBack();
        chatting_topBar.setTitle("留言反馈");
        chatting_submit = (Button) findViewById(R.id.chatting_submit);
        chatting_submit.setTextColor(Color.WHITE);
        chatting_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chatting_submit:
                Snackbar.make(v, "提交", Snackbar.LENGTH_LONG).show();
                break;
        }
    }
}
