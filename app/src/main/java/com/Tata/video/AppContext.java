package com.Tata.video;

import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.Tata.video.utils.L;
import com.tencent.bugly.crashreport.CrashReport;
import com.Tata.video.http.HttpUtil;
import com.Tata.video.jpush.JMessageUtil;
import com.Tata.video.jpush.JPushUtil;
import com.Tata.video.utils.SharedPreferencesUtil;

import cn.sharesdk.framework.ShareSDK;
import cn.tillusory.sdk.TiSDK;


/**
 * Created by cxf on 2017/8/3.
 */

public class AppContext extends MultiDexApplication {

    public static AppContext sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //初始化腾讯bugly
        Log.e("#AppContext1"," === = = ");
       // CrashReport.initCrashReport(getApplicationContext());
        //初始化http
        Log.e("#AppContext2"," === = = ");
        HttpUtil.init();
        //初始化ShareSdk
        Log.e("#AppContext3"," === = = ");
        ShareSDK.initSDK(this);
        //初始化极光推送
        Log.e("#AppContext4"," === = = ");
        JPushUtil.getInstance().init();
        //初始化极光IM
        Log.e("#AppContext5"," === = = ");
        JMessageUtil.getInstance().init();
        //初始化萌颜
        Log.e("#AppContext6"," === = = ");
  //     try {
    //        TiSDK.init(AppConfig.BEAUTY_KEY, this);
  //      } catch (java.lang.UnsatisfiedLinkError e) {
 //           System.out.println("Oops!");
  //      }
        //获取uid和token
        String[] uidAndToken = SharedPreferencesUtil.getInstance().readUidAndToken();
        if (uidAndToken != null) {
            AppConfig.getInstance().login(uidAndToken[0], uidAndToken[1]);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(this);
        super.attachBaseContext(base);
    }
}
