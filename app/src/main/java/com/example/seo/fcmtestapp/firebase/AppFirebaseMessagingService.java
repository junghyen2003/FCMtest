package com.example.seo.fcmtestapp.firebase;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.seo.fcmtestapp.MainActivity;
import com.example.seo.fcmtestapp.R;
import com.example.seo.fcmtestapp.manager.datamanager.PropertyManager;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by seo on 2018. 7. 4..
 */

public class AppFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    int badge_count; //배지 카운트//
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> pushDataMap = remoteMessage.getData();

        sendNotification(pushDataMap); //공지등록//
        set_alarm_badge(); //배지를 등록//
    }

    private void sendNotification(Map<String, String> data) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //알림설정(채널적용)//
        Notification.Builder notificationBuilder = new Notification.Builder(this, PropertyManager.getInstance().getChannelId())
                .setContentTitle(data.get("title"))
                .setContentText(data.get("msg"))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(contentIntent);

        PropertyManager.getNotificationManager().notify(0 , notificationBuilder.build());
    }

    public void set_alarm_badge() {
        Log.d("json control", "notify receive");

        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");

        //배지의 카운트를 공유저장소로부터 가져온다.//
        badge_count = PropertyManager.getInstance().get_badge_number();
        badge_count++; //0으로 되어있기에 1로 만들어준다.//
        //패키지 이름과 클래그 이름설정.//

        intent.putExtra("badge_count", badge_count);

        //문자열로 대입 가능//
        intent.putExtra("badge_count_package_name", getApplicationContext().getPackageName()); //패키지 이름//
        //배지의 적용은 맨 처음 띄우는 화면을 기준으로 한다.//
        intent.putExtra("badge_count_class_name", MainActivity.class.getName()); //맨 처음 띄우는 화면 이름//

        //변경된 값으로 다시 공유 저장소 값 초기화.//
        PropertyManager.getInstance().setBadge_number(badge_count);

        sendBroadcast(intent);
    }
}
