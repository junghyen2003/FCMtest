package com.example.seo.fcmtestapp.manager.datamanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.seo.fcmtestapp.manager.MyApplication;

/**
 * Created by seo on 2018. 7. 6..
 */

public class PropertyManager {
    private static final String ALARM_BADGE_NUMBER = "alarm_badge_number";

    //FCM 알람관련 뱃지카운터.//
    private static int badge_number = 0;
    //PropertyManger는 고유의 데이터이기에 싱글톤 디자인 패턴으로 설계//
    private static PropertyManager instance;

    //공유 프래퍼런스 생성//
    SharedPreferences mPrefs;
    SharedPreferences.Editor mEdittor;

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

    public int get_badge_number() {
        return mPrefs.getInt(ALARM_BADGE_NUMBER, badge_number);
    }

    public void setBadge_number(int badge_number) {
        mEdittor.putInt(ALARM_BADGE_NUMBER, badge_number);
        mEdittor.commit();
    }
}
