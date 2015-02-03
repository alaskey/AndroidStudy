package com.example.ckt.view.setlistener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



/**
 * Created by ckt on 2/3/15.
 * inner listenerrrrrrrrr
 */
public class MethodOne extends Activity {

    private TextView tvText;
    private Button btnRed;
    private Button btnBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setlistener);

        findViews();

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvText.setText("red~");
            }
        });

        btnBlue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                tvText.setText("blue~");
            }
        });
    }

    private void findViews() {

        tvText = (TextView) findViewById(R.id.tvText);
        btnRed = (Button) findViewById(R.id.btnRed);
        btnBlue = (Button) findViewById(R.id.btnBlue);
    }
}
