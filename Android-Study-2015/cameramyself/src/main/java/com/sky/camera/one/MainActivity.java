package com.sky.camera.one;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.ActionMode;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.sky.camera.one1.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by ckt on 2/9/15.
 */
public class MainActivity extends Activity implements View.OnClickListener{

    private FrameLayout flPreview;
    private Button btnCapture;

    private Camera camera;
    private SurfaceViewSky surfaceViewSky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViews();

        if (false == checkCameraHardward(this)) {

            return ;
        }

        camera = getCameraInstance();

        surfaceViewSky = new SurfaceViewSky(this, camera);
        flPreview.addView(surfaceViewSky);

        btnCapture.setOnClickListener(this);
    }


    // 1.
    private boolean checkCameraHardward(Context context) {

        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {

            return true;
        } else {

            return false;
        }
    }

    // 2.
    public static Camera getCameraInstance() {

        Camera camera = null;

        try {

            camera = Camera.open();
        } catch (Exception e) {

            camera = null;
        }

        return camera;
    }


    private Camera.PictureCallback picture = new Camera.PictureCallback() {


        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            String currentTime = new SimpleDateFormat("yyyyMMdd:HHmmSS").format(new Date()).toString();

            File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"oneSky");

            if (!dir.exists()) {
                if (!dir.mkdir()) {
                    return ;
                }
            }

            File file = new File(dir.getPath() + File.separator + currentTime + ".jpg");

            try {

                FileOutputStream fos = new FileOutputStream(file);

                fos.write(data);

                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            camera.stopPreview();
            camera.startPreview();
        }
    };


    private void findViews() {

        flPreview = (FrameLayout) findViewById(R.id.flPreview);
        btnCapture = (Button) findViewById(R.id.btnCapture);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnCapture:
                camera.takePicture(null,null,picture);


                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        camera.release();
    }
}
