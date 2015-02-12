package com.sky.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by ckt on 2/12/15.
 */
public class ServiceDemo extends Service {

    private static final String TAG = "SKY";
    public static final String ACTION = "com.sky.service.ServiceDemo";

    @Override
    public IBinder onBind(Intent intent) {

        Log.v(TAG, "on bind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG,"on create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG,"on start commnand");
        stopSelf();
        Log.v(TAG,"stop self");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"service on destroy");
    }
}
