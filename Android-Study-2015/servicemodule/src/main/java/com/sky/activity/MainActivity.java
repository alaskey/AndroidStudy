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

    private static final String TAG = "SKY";

    private Button btnbind;
    private Button btnStart;

    private ServiceConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViews();

        btnbind.setOnClickListener(this);
        btnStart.setOnClickListener(this);

        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.v("TAG", "on service connected");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.v(TAG, "on service disconnected");
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "activity on destroy");
    }

    private void findViews() {

        btnbind = (Button) findViewById(R.id.btnBind);
        btnStart = (Button) findViewById(R.id.btnStart);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnBind:
                intent = new Intent(MainActivity.this, ServiceDemo.class);
                bindService(intent, conn, BIND_AUTO_CREATE);
                unbindService(conn);
                break;
            case R.id.btnStart:
                intent = new Intent(MainActivity.this, ServiceDemo.class);
                startService(intent);
//                stopService(intent);
                break;
        }
    }
}
