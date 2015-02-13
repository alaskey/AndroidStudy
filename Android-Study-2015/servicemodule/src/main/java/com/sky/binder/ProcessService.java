package com.sky.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by ckt on 15-2-13.
 */
public class ProcessService extends Service {

    public static final int MSG_SAYHELLO = 22;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
