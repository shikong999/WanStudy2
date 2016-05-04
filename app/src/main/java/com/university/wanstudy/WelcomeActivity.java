package com.university.wanstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.university.wanstudy.utils.NetUtil;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //网络判断
        if (!NetUtil.isNetworkAvailable(this)) {
            Toast.makeText(this, "离线模式", Toast.LENGTH_SHORT).show();
        }
        //定时跳转
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.putExtra("isWelcome", true);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}
