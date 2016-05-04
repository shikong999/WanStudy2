package com.university.wanstudy.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.tauth.Tencent;
import com.university.wanstudy.MainActivity;
import com.university.wanstudy.MyApp;
import com.university.wanstudy.R;
import com.university.wanstudy.fragments.IndexFragment;
import com.university.wanstudy.utils.SysExitUtil;
import com.university.wanstudy.view.TopBar;

/**
 * 登录页
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TopBar login_topBar;
    private EditText user;
    private EditText password;
    private Button login;
    private TextView register;
    private TextView unRemind;
    private ImageView qqLogin;
    private Tencent mTencent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysExitUtil.activityList.add(this);
        setContentView(R.layout.activity_login);
//        // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
//// 其中APP_ID是分配给第三方应用的appid，类型为String。
//        mTencent = Tencent.createInstance("222222", this.getApplicationContext());
        initView();
    }

    private void initView() {
        login_topBar = (TopBar) findViewById(R.id.login_topBar);
        login_topBar.setBack();
        login_topBar.setTitle("登录");
        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login_login);
        login.setOnClickListener(this);
        register = (TextView) findViewById(R.id.login_register);
        register.setOnClickListener(this);
        unRemind = (TextView) findViewById(R.id.login_unremind);
        unRemind.setOnClickListener(this);
        qqLogin = ((ImageView) findViewById(R.id.qq_login));
        qqLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_login:
                String p = password.getText().toString().trim();
                String u = user.getText().toString().trim();
                if ("123".equals(p) && "123".equals(u)) {
                    Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, IndexFragment.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "登陆有误，请重新输入账号和密码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_register:
//                Intent intent=new Intent(LoginActivity.class,)
                break;
            case R.id.qq_login:

                break;

        }
    }

}
