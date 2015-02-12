package com.example.ckt.dynamic;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ckt.layoutmodule.R;

/**
 * Created by ckt on 2/12/15.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "SKY";

    private LinearLayout lyDynamic;
    private Button btnAddButton;

    private LinearLayout lyMy;

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dynamic_main);

        findViews();

        initUI();

        btnAddButton.setOnClickListener(this);

    }


    private void findViews() {

        lyDynamic = (LinearLayout) findViewById(R.id.lyDynamic);
        btnAddButton = (Button) findViewById(R.id.btnAddButton);

        lyMy = new LinearLayout(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddButton:
                TextView tv = new TextView(this);
                tv.setText(String.valueOf(i++));

                lyMy.addView(tv);
                break;
        }
    }

    private void initUI() {

        lyMy.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams lyMyParams
                = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        lyDynamic.addView(lyMy,lyMyParams);
    }
}
