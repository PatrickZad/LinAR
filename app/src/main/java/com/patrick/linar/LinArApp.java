package com.patrick.linar;

import android.app.Application;

import com.baidu.ar.bean.DuMixARConfig;
import com.baidu.ar.util.Res;


public class LinArApp extends Application {
    public static final int Langguage_ZN=0;
    public static final int Languuage_KR=1;
    public static int currentLangguage=0;
    @Override
    public void onCreate() {
        super.onCreate();
        Res.addResource(this);
        DuMixARConfig.setAppId("16137249");
        DuMixARConfig.setAPIKey("ra2ly5vPB2Vdo8yIl61jcX3V");
        DuMixARConfig.setSecretKey("lI0nDBh4o3F7xshyLoGG0jltIZgOwrxi");
    }
}
