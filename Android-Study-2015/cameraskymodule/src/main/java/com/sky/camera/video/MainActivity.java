package com.sky.camera.video;

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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ckt on 2/9/15.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private FrameLayout flPreview;
    private Button btnVideo;

    private Camera camera;
    private MediaRecorder recorder;
    private MySurfaceView mySurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera_vedio);

        findViews();
        camera = getCameraInstance(MainActivity.this);
        recorder = new MediaRecorder();

        mySurfaceView = new MySurfaceView(MainActivity.this, camera);

        flPreview.addView(mySurfaceView);

        btnVideo.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != recorder) {
            recorder.release();
            camera.lock();
        }

        if (null != camera) {
            camera.release();
            camera = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        releaseMediaRecorder();
        releaseCamera();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnVideo:
                Log.d("msgsky","btn begin");
                if (btnVideo.getText().toString().trim().equals("begin")) {
                    if (prepareVideoRecorder()) {
                        Log.d("msgsky","has prepare");
                        recorder.start();
                        btnVideo.setText("end");
                    }
                } else if(btnVideo.getText().toString().trim().equals("end")) {
                    Log.d("msgsky","end");
                    recorder.stop();
                    releaseMediaRecorder();
                    camera.unlock();
                    btnVideo.setText("begin");
                } else {
                    Log.d("msgsky","other");
                    releaseMediaRecorder();
                }
                break;
            default:
                recorder.release();
                break;
        }
    }

    private static Camera getCameraInstance(Context context) {

        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return null;
        }

        return Camera.open();
    }


    private void findViews() {

        flPreview = (FrameLayout) findViewById(R.id.flPreview);
        btnVideo = (Button) findViewById(R.id.btnVideo);
    }


    private File getOutputMediaFile() {

        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"oneSky");

        if (!dir.exists()) {
            if (!dir.mkdir()) {
                return null;
            }
        }

        String currentTime = new SimpleDateFormat("yyyyMMdd:HHmmSS").format(new Date()).toString();

        File file = new File(dir.getPath() + File.separator + currentTime + ".mp4");

        return file;
    }


    private boolean prepareVideoRecorder() {
        Log.d("msgsky","come in prepare");

        recorder = new MediaRecorder();

        // 1. unlock camera
        camera.unlock();
        Log.d("msgsky","unlock");
        // 2. configure MediaRecorder
        recorder.setCamera(camera);

        recorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        Log.d("msgsky","quality");
        recorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P));

        recorder.setOutputFile(getOutputMediaFile().toString());

        recorder.setPreviewDisplay(mySurfaceView.getHolder().getSurface());

        try {
            recorder.prepare();
        } catch (Exception e) {
            releaseMediaRecorder();
            return false;
        }
        return true;
    }

    private void releaseMediaRecorder() {

        if (null == recorder) {
            recorder.reset();
            recorder.release();
            recorder = null;

            camera.lock();
        }
    }

    private void releaseCamera() {

        if (null == camera) {
            camera.release();
            camera = null;
        }
    }
}
