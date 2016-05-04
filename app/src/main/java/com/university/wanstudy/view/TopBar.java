package com.university.wanstudy.view;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.university.wanstudy.R;


/**
 * 自定义TopBar
 * 构造函数使用this调用的好处
 * 方便我们统一管理,无论从那个构造进入,都会调用三个参数的构造
 */
public class TopBar extends LinearLayout implements View.OnClickListener {
    private static final String TAG = TopBar.class.getSimpleName();
    private ImageView back;
    private TextView title;
    private Context context;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        //实例化inflater来填充布局
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.topbar, this, true);
        //findViewById查找子view
        back = ((ImageView) findViewById(R.id.topbar_back));
        title = ((TextView) findViewById(R.id.topbar_title));
        title.setTextColor(Color.WHITE);
        //默认都是隐藏,只有设置属性时才显示出来
        back.setVisibility(INVISIBLE);
        title.setVisibility(INVISIBLE);
    }

    /**
     * 设置Title
     *
     * @param content
     */
    public void setTitle(String content) {
        title.setVisibility(VISIBLE);
        title.setText(content);
    }

    /**
     * 设置回退按钮
     */
    public void setBack() {
        back.setVisibility(VISIBLE);
        back.setOnClickListener(this);
    }

    /**
     * 设置回退监听
     */
    public void setBackListener(OnClickListener listener) {
        back.setVisibility(VISIBLE);
        back.setOnClickListener(listener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.topbar_back:
                //调用Activity的finish方法
                Log.e(TAG, "回退");
                ((AppCompatActivity) context).finish();
                break;
        }
    }
}
