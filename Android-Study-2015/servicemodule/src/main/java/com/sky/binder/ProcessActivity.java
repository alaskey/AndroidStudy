package com.sky.binder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import com.sky.activity.R;

/**
 * Created by ckt on 15-2-13.
 */
public class ProcessActivity extends Activity {

    private final String TAG = "SKY";
    private final int SAY_HELLO_TO_CLIENT = 9;

    private  ServiceConnection sConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_binder_main);

        init();
    }


    private void init() {

         sConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.v(TAG,"service connected");
                Messenger messenger = new Messenger(service);

                Message msg = new Message();

                msg.what = ProcessService.MSG_SAYHELLO;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }


    Messenger messenger_reciever = new Messenger(new IncomingHandler());

    class IncomingHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SAY_HELLO_TO_CLIENT:
                    Log.v(TAG, "process activity : " + Thread.currentThread().getId());
                    break;
                default:
            }
        }
    }
}
