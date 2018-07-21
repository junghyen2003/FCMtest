package com.example.seo.fcmtestapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.Manifest;

import com.example.seo.fcmtestapp.manager.datamanager.PropertyManager;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_PERMISSION_CONTACTS=1;

    Button testbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBadgeCountInit();

        testbutton = (Button)findViewById(R.id.testbutton);

        testbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showContactsPermission(); //런타임 권한체크//
            }
        });
    }

    private void showContactsPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                showExplanation("요청 권한이 필요합니다.", "[주소록] 접근권한이 있어야지 서비스가 가능합니다.", Manifest.permission.READ_CONTACTS, REQUEST_PERMISSION_CONTACTS); //권한필요 설명//
            } else {
                requestPermission(Manifest.permission.READ_CONTACTS, REQUEST_PERMISSION_CONTACTS);
            }
        } else {
            Toast.makeText(MainActivity.this, "요청권한이 이미 승인되었습니다. 서비스를 계속합니다.", Toast.LENGTH_SHORT).show();

            //이후 작업진행//
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CONTACTS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "권한 승인", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "권한 거부로 정상적인 서비스가 불가합니다. 서비스를 이용하실려면 권한을 승인해야 합니다.", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void showExplanation(String title, String message, final String permission, final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requestPermission(permission, permissionRequestCode); //권한 재요청//
                    }
                });
        builder.create().show();
    }

    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permissionName}, permissionRequestCode); //권한요청 다이얼로그//
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
