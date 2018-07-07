package com.example.seo.fcmtestapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.seo.fcmtestapp.manager.datamanager.PropertyManager;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBadgeCountInit();
    }

    private void setBadgeCountInit() {
        Intent i = new Intent("android.intent.action.BADGE_COUNT_UPDATE");

        i.putExtra("badge_count", 0); //다시 배지카운터를 0으로 초기화.//
        i.putExtra("badge_count_package_name", getApplicationContext().getPackageName());
        i.putExtra("badge_count_class_name", MainActivity.class.getName());

        //변경된 값으로 다시 공유 저장소 값 초기화.//
        PropertyManager.getInstance().setBadge_number(0);

        sendBroadcast(i); //브로드캐스트를 이용.//
    }
}
