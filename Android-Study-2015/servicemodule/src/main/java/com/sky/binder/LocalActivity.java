package com.sky.binder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.sky.activity.R;

/**
 * Created by ckt on 15-2-13.
 */
public class LocalActivity extends Activity {
    private final String TAG = "SKY";

    private ServiceConnection sConn;
    private LocalService sDemo;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_binder_main);

        Log.v(TAG, "main activity: " + Thread.currentThread().getId());

        init();
    }


    private void init() {

        sConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                Log.v(TAG, "service connected");

                sDemo = ((LocalService.LocalBinder) service).getService();
                sDemo.sayHello();

                stopService(intent);

                Log.v(TAG, "has top service");

                LocalActivity.this.unbindService(sConn);
                LocalActivity.this.finish();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.v(TAG, "service disConnected");
            }
        };
    }


    @Override
    protected void onStart() {
        super.onStart();

        intent = new Intent(LocalActivity.this, LocalService.class);

        LocalActivity.this.bindService(intent, sConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
