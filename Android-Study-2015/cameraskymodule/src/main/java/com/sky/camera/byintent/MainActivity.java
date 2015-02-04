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



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.DIRECTORY_PICTURES;

/**
 * Created by ckt on 2/4/15.
 */
public class MainActivity extends Activity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE=100;
    private Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    private Button btnCamera;
    private Button btnCameraSky;
    private Button btnVideoSky;
    private Button btnForResult;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_byintent_main);


        btnCamera = (Button) findViewById(R.id.btnCamera);
        btnCameraSky = (Button) findViewById(R.id.btnCameraSky);
        btnVideoSky = (Button) findViewById(R.id.btnVideoSky);
        btnForResult = (Button) findViewById(R.id.btnForResult);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // create intent to take a picture and return control to the calling application
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // create a file to save image
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });

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

                startActivity(intent);
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

                startActivity(intent);
            }
        });


//    *******************


        btnForResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,StartActivityForResult.class);

                startActivityForResult(intent,100);
            }
        });


    }




    public static Uri getOutputMediaFileUri(int type) {

        return Uri .fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES),
        "myCamera");


        if (!mediaStorageDir.exists()) {

            if (!mediaStorageDir.mkdirs()) {

                Log.d("myCamera", "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File mediaFile;

        if (type == MEDIA_TYPE_IMAGE) {

            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {

            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
        } else {

            return null;
        }

        return mediaFile;
    }
}













