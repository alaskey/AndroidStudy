package com.sky.camera.one;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by ckt on 2/6/15.
 */
public class MyCamera extends Activity {

    private MySurfaceView mySurfaceView;
    private static Camera camera;

    private Button btnCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera_one_mycamera);


        if (false == checkHardwareOfCamera(MyCamera.this)) {

            return;
        }


        camera = getCameraInstance();

        mySurfaceView = new MySurfaceView(this, camera);

        FrameLayout flPreview = (FrameLayout) findViewById(R.id.flPreview);

        flPreview.addView(mySurfaceView);


        btnCapture = (Button) findViewById(R.id.btnCapture);

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                camera.takePicture(null, null, picture);
            }
        });
    }

    // step 1: check is camera exists
    private boolean checkHardwareOfCamera(Context context) {

        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }


    // setp 2: open camera
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }


    private Camera.PictureCallback picture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"sky-one");

            if (!dir.exists()) {
                if (!dir.mkdir()) {

                    return ;
                }
            }

            String fileName = new SimpleDateFormat("yyyyMMdd:HHmmSS").format(new Date()).toString();

            File file = new File(dir.getPath() + File.separator + fileName + ".jpg");

            if (null == file) {

                return ;
            }

            try {

                FileOutputStream fos = new FileOutputStream(file);

                fos.write(data);

                fos.close();
            } catch (Exception e) {

            }
        }
    };
}




















