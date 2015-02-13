package com.sky.video.one;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ckt on 2/9/15.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private FrameLayout flVideo;
    private Button btnVideo;

    private Camera camera;
    private MySurfaceView mySurfaceView;
    private MediaRecorder recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_video);

        findViews();

        if (!hasCamera(MainActivity.this)) {
            return;
        }

        camera = getCameraInstance(MainActivity.this);
        recorder = new MediaRecorder();
        mySurfaceView = new MySurfaceView(MainActivity.this, camera);

        flVideo.addView(mySurfaceView);

        btnVideo.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        releaseCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        releaseCamera();
    }

    private void findViews() {

        flVideo = (FrameLayout) findViewById(R.id.flVideo);
        btnVideo = (Button) findViewById(R.id.btnVideo);
    }

    private boolean hasCamera(Context context) {

        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    private static Camera getCameraInstance(Context context) {

        return Camera.open();
    }

    private void releaseRecord() {

        if (null == recorder) {
            return;
        }
        recorder.reset();
        recorder.release();
        recorder = null;

        camera.lock();
    }

    private void releaseCamera() {

        if (null != camera) {
            camera.release();
            camera = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnVideo:
                if (btnVideo.getText().toString().trim().equals("begin")) {
                    btnVideo.setText("end");
                    if (prepareVideoRecorder()) {
                        recorder.start();
                    }
                } else if (btnVideo.getText().toString().trim().equals("end")) {
                    btnVideo.setText("begin");
                    recorder.stop();
                    recorder.release();
                }
                break;
        }
    }


    private boolean prepareVideoRecorder(){

        if (null == camera) {
            camera = getCameraInstance(MainActivity.this);
        }
        if (null == recorder) {
            recorder = new MediaRecorder();
        }
        if (null == mySurfaceView) {
            mySurfaceView = new MySurfaceView(MainActivity.this, camera);
        }

        try {
            camera.unlock();
        } catch (Exception e) {
            return false;
        }

        recorder.setCamera(camera);
        recorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        recorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));

        recorder.setOutputFile(getOutputMediaFile().toString());

        recorder.setPreviewDisplay(mySurfaceView.getHolder().getSurface());

        try {
            recorder.prepare();
        } catch (Exception e) {
            return false;
        }

        return true;
    }


    private File getOutputMediaFile() {

        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "oneSky");

        if (!dir.exists()) {
            if (!dir.mkdir()) {
                return null;
            }
        }

        String currentTime = new SimpleDateFormat("yyyyMMdd:HHmmSS").format(new Date()).toString();

        File file = new File(dir.getPath() + File.separator + currentTime + ".mp4");

        return file;
    }

}
