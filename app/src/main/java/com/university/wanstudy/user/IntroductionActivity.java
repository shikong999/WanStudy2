package com.university.wanstudy.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.university.wanstudy.R;
import com.university.wanstudy.utils.SysExitUtil;
import com.university.wanstudy.view.TopBar;

/**
 * 软件介绍
 */
public class IntroductionActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysExitUtil.activityList.add(this);
        setContentView(R.layout.activity_introduction);
        initView();
    }

    private void initView() {
        TopBar introduction_back = (TopBar) findViewById(R.id.introduction_back);
        introduction_back.setBack();
        introduction_back.setTitle("课程");
        //微博
        LinearLayout introduction_weibo = (LinearLayout) findViewById(R.id.introduction_weibo);
        introduction_weibo.setOnClickListener(this);
        //贴吧
        LinearLayout introduction_tieba = (LinearLayout) findViewById(R.id.introduction_tieba);
        introduction_tieba.setOnClickListener(this);
        //人人
        LinearLayout introduction_renren = (LinearLayout) findViewById(R.id.introduction_renren);
        introduction_renren.setOnClickListener(this);
        //空间
        LinearLayout introduction_qzone = (LinearLayout) findViewById(R.id.introduction_qzone);
        introduction_qzone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        //隐式意图
        intent.setAction(Intent.ACTION_VIEW);
        switch (v.getId()) {
            case R.id.introduction_weibo:
                intent.setData(Uri.parse("http://weibo.com"));//设置一个URI地址
                break;
            case R.id.introduction_tieba:
                intent.setData(Uri.parse("http://tieba.baidu.com/"));//设置一个URI地址
                break;
            case R.id.introduction_renren:
                intent.setData(Uri.parse("www.renren.com"));//设置一个URI地址
                break;
            case R.id.introduction_qzone:
                intent.setData(Uri.parse("http://qzone.qq.com/"));//设置一个URI地址
                break;
        }
        this.startActivity(intent);//用startActivity打开这个指定的网页。
    }
}
