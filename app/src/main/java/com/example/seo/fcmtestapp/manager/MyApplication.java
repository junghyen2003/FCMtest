package com.example.seo.fcmtestapp.manager;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.seo.fcmtestapp.manager.datamanager.PropertyManager;

/**
 * Created by seo on 2018. 7. 6..
 */

public class MyApplication extends Application{//매니패스트에 .name으로 등록//
    static Context context;

    public static Context getContext() {
        return context; //자원을 반환.//
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this; //현재 어플리케이션의 자원을 얻어온다.//

        Log.d("application start", "--------------------- [start]");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1) {
            Log.d("Android Version : ", "8.0, API 26");

            PropertyManager.getInstance().setAlarmChannel();
        }
    }
}
