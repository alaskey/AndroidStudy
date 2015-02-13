package com.sky.binder;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.sky.activity.R;

public class LocalService extends Service {

    private final String TAG = "SKY";

    @Override
    public IBinder onBind(Intent intent) {

        return new LocalBinder();
    }


    public void sayHello() {

        Log.v(TAG, "service: " + Thread.currentThread().getId());

        Log.v(TAG, "hello sky");
    }

    public class LocalBinder extends Binder {

        public LocalService getService() {
            return LocalService.this;
        }
    }
}
