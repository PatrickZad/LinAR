package com.baidu.ardemo;

import com.baidu.ar.bean.DuMixARConfig;
import com.baidu.ar.util.Res;

import android.app.Application;

public class ARApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Res.addResource(this);
        // 设置App Id
        DuMixARConfig.setAppId("您的App Id");
        // 设置API Key
        DuMixARConfig.setAPIKey("您的API Key");
        // 设置Secret Key
        DuMixARConfig.setSecretKey("您的Secret Key");
    }
}
