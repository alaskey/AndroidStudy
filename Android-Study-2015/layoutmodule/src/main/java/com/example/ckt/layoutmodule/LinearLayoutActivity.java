package com.example.ckt.layoutmodule;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ckt on 2/5/15.
 */
public class LinearLayoutActivity extends Activity implements View.OnClickListener{

    private LinearLayout lyOne;
    private Button btnSubOne;
    private TextView tvWeightOne;
    private Button btnAddOne;

    private LinearLayout lyTwo;
    private Button btnSubTwo;
    private TextView tvWeightTwo;
    private Button btnAddTwo;

    private final float CHANGE_VALUE = 0.5f;
    private float weightValue;
    private final float MIN_VALUE = 0.0000001f;
    private final float MAX_VALUE = 10f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_linearlayout);

        findViews();

        btnSubOne.setOnClickListener(this);
        btnAddOne.setOnClickListener(this);

        btnSubTwo.setOnClickListener(this);
        btnAddTwo.setOnClickListener(this);
    }


    private void findViews() {

        lyOne = (LinearLayout) findViewById(R.id.lyOne);
        btnSubOne = (Button) findViewById(R.id.btnSubOne);
        tvWeightOne = (TextView) findViewById(R.id.tvWeightOne);
        btnAddOne = (Button) findViewById(R.id.btnAddOne);

        lyTwo = (LinearLayout) findViewById(R.id.lyTwo);
        btnSubTwo = (Button) findViewById(R.id.btnSubTwo);
        tvWeightTwo = (TextView) findViewById(R.id.tvWeightTwo);
        btnAddTwo = (Button) findViewById(R.id.btnAddTwo);
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.btnSubOne:
                weightValue = Float.valueOf(tvWeightOne.getText().toString()) - CHANGE_VALUE;

                if (weightValue<0) {

                    return ;
                }

                tvWeightOne.setText(String.valueOf(weightValue));
                lyOne.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,weightValue));
                break;
            case R.id.btnAddOne:
                weightValue = Float.valueOf(tvWeightOne.getText().toString()) + CHANGE_VALUE;

                if (Math.abs(weightValue-10) > MAX_VALUE) {

                    return ;
                }

                tvWeightOne.setText(String.valueOf(weightValue));
                lyOne.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,weightValue));
                break;
            case R.id.btnSubTwo:
                weightValue = Float.valueOf(tvWeightTwo.getText().toString()) - CHANGE_VALUE;

                if (weightValue < 0 ) {

                    return ;
                }

                lyTwo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, weightValue));
                tvWeightTwo.setText(String.valueOf(weightValue));
                break;
            case R.id.btnAddTwo:
                weightValue = Float.valueOf(tvWeightTwo.getText().toString()) + CHANGE_VALUE;

                if (Math.abs(weightValue-10) > MAX_VALUE) {

                    return ;
                }

                lyTwo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,weightValue));
                tvWeightTwo.setText(String.valueOf(weightValue));
                break;
        }
    }
}
