package com.sky.camera.byintent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.sky.camera.one.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.DIRECTORY_PICTURES;

/**
 * Created by ckt on 2/4/15.
 */
public class MainActivity extends Activity {

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    private Button btnCameraSky;
    private Button btnVideoSky;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;

    private static final int RESULT_OK = 1;
    private static final int RESULT_FAILED = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_byintent_main);


        btnCameraSky = (Button) findViewById(R.id.btnCameraSky);
        btnVideoSky = (Button) findViewById(R.id.btnVideoSky);


        btnCameraSky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // create dir
                File dir = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES), "skyCamera");

                if (!dir.exists()) {

                    boolean flg = dir.mkdirs();


                    if (false == flg) {

                        Log.v("error", "cant make " + dir);
                        return;
                    }
                }

                String currentTime = new SimpleDateFormat("yyyyMMdd:HHmmSS").format(new Date()).toString();

                // create file
                File file = new File(dir.getPath() + File.separator + currentTime + ".jpg");

                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });


        btnVideoSky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                File dir = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES), "skyCamera");
                if (!dir.exists()) {

                    if (!dir.mkdir()) {

                        Log.v("error", "cant mkdir");
                        return;
                    }
                }

                String currentTime = new SimpleDateFormat("yy-DD:hh-MM-SS").format(new Date()).toString();

                File file = new File(dir + File.separator + currentTime + ".mp4");

                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

                startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        Toast.makeText(MainActivity.this, "image sucess", Toast.LENGTH_SHORT).show();
                        break;
                    case RESULT_FAILED:
                        Toast.makeText(MainActivity.this, "image failed", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "unkown reason failed", Toast.LENGTH_SHORT).show();
                }
                break;
            case CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        Toast.makeText(MainActivity.this, "image sucess", Toast.LENGTH_SHORT).show();
                        break;
                    case RESULT_FAILED:
                        Toast.makeText(MainActivity.this, "image failed", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "unkown reason failed", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}











