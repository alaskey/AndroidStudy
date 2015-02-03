package com.example.ckt.view.setlistener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by ckt on 2/3/15.
 * share listener
 */
public class MethodTwo extends Activity implements View.OnClickListener{

    private TextView tvText;
    private Button btnRed;
    private Button btnBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setlistener);

        findViews();

        btnRed.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
    }

    private void findViews() {

        tvText = (TextView) findViewById(R.id.tvText);
        btnRed = (Button) findViewById(R.id.btnRed);
        btnBlue = (Button) findViewById(R.id.btnBlue);
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.btnRed:
               tvText.setText("red2~");
               break;
            case R.id.btnBlue:
                tvText.setText("blue2~");
                break;
        }
    }

}
