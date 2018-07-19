package com.example.seo.fcmtestapp.manager.datamanager;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.seo.fcmtestapp.manager.MyApplication;

/**
 * Created by seo on 2018. 7. 6..
 */

public class PropertyManager {
    //FCM 알람관련 뱃지카운터.//
    private static final String ALARM_BADGE_NUMBER = "alarm_badge_number";
    private static int badge_number = 0;

    //알림채널 관련//
    private static String CHANNEL_ID = "12345"; //패키지당 고유한 값으로 설정//
    private static String CHANNEL_NAME = "test alarm"; //알림에 대한 주제//
    private static String CHANNEL_DESCRIPTION = "test alarm info"; //알림에 대한 세부설명//

    //PropertyManger는 고유의 데이터이기에 싱글톤 디자인 패턴으로 설계//
    private static PropertyManager instance;

    //NotificationChannel, NotificationManager은 앱 실행 시 초기 한번만 만들어져야 하므로 싱글톤 디자인 패턴으로 설계//
    private static NotificationChannel notificationChannel;
    private static NotificationManager notificationManager;

    //공유 프래퍼런스 생성//
    SharedPreferences mPrefs;
    SharedPreferences.Editor mEdittor;

    //생성자//
    private PropertyManager() {
        Context context = MyApplication.getContext(); //현재 앱의 자원을 얻어온다.//

        //프래퍼런스를 사용하도록 설정//
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mEdittor = mPrefs.edit();
    }

    //싱글톤 패턴//
    public static PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
        }

        return instance;
    }

    public static NotificationChannel getNotificationChannel(){
        if(notificationChannel == null){
            notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        }

        return notificationChannel;
    }

    public static NotificationManager getNotificationManager(){
        if(notificationManager == null){
            Context context = MyApplication.getContext(); //현재 앱의 자원을 얻어온다.//

            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return notificationManager;
    }

    public void setAlarmChannel(){
        Log.d("channel setting: ", "--------------------[start]");

        notificationManager = PropertyManager.getNotificationManager();
        notificationChannel = PropertyManager.getNotificationChannel();

        notificationChannel.setDescription(CHANNEL_DESCRIPTION);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.GREEN);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        notificationManager.createNotificationChannel(notificationChannel);

        Log.d("channel setting: ", "--------------------[end]");
    }

    public String getChannelId(){
        return CHANNEL_ID;
    }

    public int get_badge_number() {
        return mPrefs.getInt(ALARM_BADGE_NUMBER, badge_number);
    }

    public void setBadge_number(int badge_number) {
        mEdittor.putInt(ALARM_BADGE_NUMBER, badge_number);
        mEdittor.commit();
    }
}
