package com.sky.camera.one;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by ckt on 2/6/15.
 */
public class MainActivity extends Activity {

    private Button btnBeginCamera;
    private Button btnBeginSky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera_one_main);
        btnBeginCamera = (Button) findViewById(R.id.btnBeginCamera);

        btnBeginCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MyCamera.class);
                startActivity(intent);
            }
        });

        btnBeginSky = (Button) findViewById(R.id.btnBeginSky);

        btnBeginSky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MyCameraSky.class);
                startActivity(intent);
            }
        });
    }
}