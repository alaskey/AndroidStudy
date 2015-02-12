package com.sky.handler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;


/**
 * Created by ckt on 2/12/15.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "SKY";
    private static final int HANDLER_CODE = 9;

    private Handler handler;

    private Button btnStart;
    private Button btnEnd;

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case HANDLER_CODE:
                        Log.v(TAG,"msg.what = " + HANDLER_CODE);
                        pd.dismiss();
                        break;
                }

            }
        };

        setContentView(R.layout.activity_main);

        findViews();

        setListeners();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnStart:
                pd = ProgressDialog.show(MainActivity.this, "loading", "load");
//                handler.post(updateThread);
                Thread thread = new Thread(updateThread);
                thread.start();
                break;
            case R.id.btnEnd:
//                handler.removeCallbacks(updateThread);
                break;

        }
    }

    private void setListeners() {

        btnStart.setOnClickListener(MainActivity.this);
        btnEnd.setOnClickListener(this);
    }


    private void findViews() {

        btnStart = (Button) findViewById(R.id.btnStart);
        btnEnd = (Button) findViewById(R.id.btnEnd);
    }


    Runnable updateThread = new Runnable() {
        @Override
        public void run() {
            Log.v(TAG, "update thread, " + Thread.currentThread().getId());

            handler.postDelayed(updateThread, 5000);

            handler.sendEmptyMessageDelayed(HANDLER_CODE,5000);

//            handler.sendEmptyMessage(HANDLER_CODE);
        }
    };
}
