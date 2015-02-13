package com.sky.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sky.service.ServiceDemo;

/**
 * Created by ckt on 2/12/15.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private final String TAG = "SKY";

    private Button btnService;
    private Button btnHandler;
    private Button btnBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViews();

        btnService.setOnClickListener(this);
        btnHandler.setOnClickListener(this);
        btnBinder.setOnClickListener(this);
    }


    private void findViews() {

        btnService = (Button) findViewById(R.id.btnService);
        btnHandler = (Button) findViewById(R.id.btnHandler);
        btnBinder = (Button) findViewById(R.id.btnBinder);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnService:
                intent = new Intent(MainActivity.this, com.sky.service.MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnHandler:
                intent = new Intent(MainActivity.this, com.sky.handler.MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnBinder:
                intent = new Intent(MainActivity.this, com.sky.binder.MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
