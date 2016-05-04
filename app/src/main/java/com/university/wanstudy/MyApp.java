package com.university.wanstudy;

import android.app.Application;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by dkk on 2016/4/20.
 */
public class MyApp extends Application {

    public static ImageOptions options;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        //x.image设置
        options=new ImageOptions.Builder()
                .setFadeIn(true)
                .setUseMemCache(true)//允许缓存
                .build();
    }
}
