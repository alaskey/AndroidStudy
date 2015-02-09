package com.sky.forresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sky.camera.video.R;


/**
 * Created by ckt on 2/6/15.
 */
public class ForwardActivity extends Activity {

    private Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_for_result_forward);

        btnFinish = (Button) findViewById(R.id.btnFinish);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = ForwardActivity.this.getIntent();

                intent.putExtra("one","morning");
                intent.putExtra("two","night");

                setResult(12, intent);

                finish();
            }
        });
    }

}