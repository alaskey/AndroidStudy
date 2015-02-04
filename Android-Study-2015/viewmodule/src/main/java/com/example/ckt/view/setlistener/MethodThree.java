package com.example.ckt.view.setlistener;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ckt.view.keyevent.R;

/**
 * Created by ckt on 2/3/15.
 * set class implements listener
 */
public class MethodThree extends Activity {

    private TextView tvText;
    private Button btnRed;
    private Button btnBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setlistener);

        findViews();

        btnRed.setOnClickListener(new BtnRedListener());
        btnBlue.setOnClickListener(new BtnBlueListener());
    }

    private void findViews() {

        tvText = (TextView) findViewById(R.id.tvText);
        btnRed = (Button) findViewById(R.id.btnRed);
        btnBlue = (Button) findViewById(R.id.btnBlue);
    }

    private class BtnRedListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            tvText.setText("Red3~");
        }
    }

    private class BtnBlueListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            tvText.setText("blue3~");
        }
    }
}
