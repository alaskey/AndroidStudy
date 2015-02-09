package com.sky.forresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sky.camera.video.R;


/**
 * Created by ckt on 2/6/15.
 */
public class MainActivity extends Activity {

    private Button btnBegin;
    private EditText etRequest;
    private EditText etResult;
    private TextView tvExtra;
    private final int DOUBLE_ONE = 11;
    private final int DOUBLE_TWO = 22;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_for_result);

        btnBegin = (Button) findViewById(R.id.btnBegin);
        etRequest = (EditText) findViewById(R.id.etRequest);
        etResult = (EditText) findViewById(R.id.etResult);
        tvExtra = (TextView)findViewById(R.id.tvExtra);


        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this,ForwardActivity.class);

                startActivityForResult(intent,DOUBLE_ONE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        etRequest.setText(String.valueOf(requestCode));
        etResult.setText(String.valueOf(resultCode));

        Bundle bundle = data.getExtras();

        String str = bundle.getString("one") + "_" + bundle.getString("two");

        tvExtra.setText(str);
    }
}
