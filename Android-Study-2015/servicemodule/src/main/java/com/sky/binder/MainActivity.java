package com.sky.binder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sky.activity.R;

/**
 * Created by ckt on 15-2-13.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnBinderLocal;
    private Button btnBinderProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_binder_main);

        findViews();

        btnBinderLocal.setOnClickListener(this);
        btnBinderProcess.setOnClickListener(this);
    }

    private void findViews() {
        btnBinderLocal = (Button) findViewById(R.id.btnBinderLocally);
        btnBinderProcess = (Button) findViewById(R.id.btnBinderProcess);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnBinderLocally:
                intent = new Intent(MainActivity.this, LocalActivity.class);
                startActivity(intent);
                break;
            case R.id.btnBinderProcess:
                intent = new Intent(MainActivity.this, ProcessActivity.class);
                startActivity(intent);
                break;
        }
    }
}