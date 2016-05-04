
package com.university.wanstudy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.university.wanstudy.adapters.MainPagerAdapter;
import com.university.wanstudy.fragments.IndexFragment;
import com.university.wanstudy.fragments.OtherFragment;
import com.university.wanstudy.user.ChattingActivity;
import com.university.wanstudy.user.LoginActivity;
import com.university.wanstudy.user.ReadingActivity;
import com.university.wanstudy.user.RecordActivity;
import com.university.wanstudy.user.SettingActivity;
import com.university.wanstudy.user.SignupActivity;
import com.university.wanstudy.user.UserActivity;
import com.university.wanstudy.user.VideoActivity;
import com.university.wanstudy.utils.SysExitUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Toolbar mToolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ViewPager viewPager;
    private TabLayout tab;
    private MainPagerAdapter adapter;
    private LinearLayout reading;
    private LinearLayout video;
    private LinearLayout chatting;
    private LinearLayout record;
    private LinearLayout share;
    private LinearLayout setting;
    private ImageView touxiang;
    private Button login;
    private Button sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SysExitUtil.activityList.add(this);
        isWelcome();
        initView();
    }

    private void isWelcome() {
        if (!getIntent().getBooleanExtra("isWelcome", false)) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        }
    }

    private void initView() {
        //toolbar与侧边联动
        mToolbar = ((Toolbar) this.findViewById(R.id.mian_toolbar));
        setSupportActionBar(mToolbar);
        drawer = ((DrawerLayout) findViewById(R.id.mian_drawer));
        share = ((LinearLayout) findViewById(R.id.share));
        share.setOnClickListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
        actionBarDrawerToggle.syncState();
        drawer.addDrawerListener(actionBarDrawerToggle);

        //导航栏与各页面联动
        tab = (TabLayout) findViewById(R.id.mian_tab);
        //MODE_SCROLLABLE:可滚动,MODE_FIXED:不足屏幕,自动填满
        tab.setTabMode(TabLayout.MODE_FIXED);
        viewPager = (ViewPager) findViewById(R.id.mian_viewpager);
        viewPager.setOffscreenPageLimit(3);
        //ViewPager数据源
        List<Fragment> data = new ArrayList<>();
        data.add(new IndexFragment());//首页
        //动态创建语言等3个页面
        for (int i = 1; i < 4; i++) {
            OtherFragment otherFragment = new OtherFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("genre_id", i);
            otherFragment.setArguments(bundle);
            data.add(otherFragment);
        }
        //导航栏标题数据源
        List<String> titles = new ArrayList<>();
        titles.add("首页");
        titles.add("大学");
        titles.add("中学");
        titles.add("语言");
        //实例化适配器
        adapter = new MainPagerAdapter(getSupportFragmentManager(), data, titles);
        viewPager.setAdapter(adapter);
        //使ViewPager与TabLayout进行联动
        tab.setupWithViewPager(viewPager);
        //*******************实例化侧滑栏View***************************
        touxiang = (ImageView) findViewById(R.id.touxiang);
        touxiang.setOnClickListener(this);
        login = (Button) findViewById(R.id.login);
        login.setTextColor(Color.WHITE);
        login.setOnClickListener(this);
        sign_up = (Button) findViewById(R.id.sign_up);
        sign_up.setTextColor(Color.WHITE);
        sign_up.setOnClickListener(this);
        reading = (LinearLayout) findViewById(R.id.reading);
        reading.setOnClickListener(this);
        video = (LinearLayout) findViewById(R.id.video);
        video.setOnClickListener(this);
        chatting = (LinearLayout) findViewById(R.id.chatting);
        chatting.setOnClickListener(this);
        record = (LinearLayout) findViewById(R.id.record);
        record.setOnClickListener(this);
        share = (LinearLayout) findViewById(R.id.share);
        share.setOnClickListener(this);
        setting = (LinearLayout) findViewById(R.id.setting);
        setting.setOnClickListener(this);
    }


    private boolean isExite;

    /**
     * 2次点击退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //如果按下的键是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //如果处于可退出状态
            if (isExite) {
                //调用系统的返回键退出
                return super.onKeyDown(keyCode, event);
            } else {
                //先将退出标识设为可退出状态
                isExite = true;
//                Toast.makeText(this, "再按一次返回键退出本应用", Toast.LENGTH_SHORT).show();
                //试用Snackbar
                Snackbar.make(drawer, "再按一次返回键退出本应用", Snackbar.LENGTH_LONG).setAction("action", null).show();
                //定时器
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //按过返回键后,如果2秒后没继续按返回,则重置为不可退出状态
                        isExite = false;
                    }
                }, 2000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);

    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("万门app就是好用");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("请将我分享给其他朋友们吧");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("这个app很好用哦");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share:
                showShare();
                break;
            //登录
            case R.id.login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            //注册
            case R.id.sign_up:
                startActivity(new Intent(this, SignupActivity.class));
                break;
            //课程阅读
            case R.id.reading:
                startActivity(new Intent(this, ReadingActivity.class));
                break;
            //离线视频
            case R.id.video:
                startActivity(new Intent(this, VideoActivity.class));
                break;
            //留言反馈
            case R.id.chatting:
                startActivity(new Intent(this, ChattingActivity.class));
                break;
            //播放记录
            case R.id.record:
                startActivity(new Intent(this, RecordActivity.class));
                break;
            //设置
            case R.id.setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            //用户详情页
            case R.id.touxiang:
                startActivity(new Intent(this, UserActivity.class));
                break;
        }
    }
}

