package com.sky.camera.video;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        releaseMediaRecorder();
        releaseCamera();
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
                if (btnVideo.getText().toString().trim().equals("begin")) {
                    btnVideo.setText("end");
                    if (prepareVideoRecorder()) {
                        recorder.start();
                    }
                } else if (btnVideo.getText().toString().trim().equals("end")) {
                    btnVideo.setText("begin");
                    recorder.stop();
                    releaseMediaRecorder();
                }
                break;
            default:
                releaseMediaRecorder();
                releaseCamera();
                break;
        }
    }

    private static Camera getCameraInstance(Context context) {

        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return null;
        }

        Camera camera = null;

        try {
            camera = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setFocusModeAuto(camera);

        setMeteringAreas(camera);

        camera.setFaceDetectionListener(new MyFaceDetectionListener());

        return camera;
    }


    private void findViews() {

        flPreview = (FrameLayout) findViewById(R.id.flPreview);
        btnVideo = (Button) findViewById(R.id.btnVideo);
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


    private boolean prepareVideoRecorder() {

        Log.d("msgsky", "come in prepare");

        if (null == camera) {
            camera = getCameraInstance(MainActivity.this);
        }
        if (null == recorder) {
            recorder = new MediaRecorder();
        }
        if (null == mySurfaceView) {
            mySurfaceView = new MySurfaceView(MainActivity.this, camera);

            flPreview.addView(mySurfaceView);
        }

        // 1. unlock camera
        try {
            camera.unlock();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2. configure MediaRecorder
        recorder.setCamera(camera);
        recorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        recorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P));

        // capture every 10 seconds
        recorder.setCaptureRate(0.03);

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

        if (null != recorder) {
            recorder.reset();
            recorder.release();
            recorder = null;

            camera.lock();
        }
    }

    private void releaseCamera() {

        if (null != camera) {
            camera.release();
            camera = null;
        }
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (KeyEvent.KEYCODE_BACK == keyCode) {
            releaseMediaRecorder();
            releaseCamera();
            Log.d("msgsky", "resolve key back finish");

            finish();
        }

        return super.onKeyDown(keyCode, event);
    }

    private void checkCameraParamters(Camera camera) {

        Camera.Parameters params = camera.getParameters();

        List<String> focusModes = params.getSupportedFocusModes();

        if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
            // ...
        }
    }

    private static void setFocusModeAuto(Camera camera) {

        Camera.Parameters params = camera.getParameters();

        String focusMode = params.getFocusMode();

        Log.v("msgsky", "focus moded: " + focusMode);

        params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

        camera.setParameters(params);
    }


    private static void setMeteringAreas(Camera camera) {

        Camera.Parameters params = camera.getParameters();

        if (0 < params.getMaxNumMeteringAreas()) {

            Log.v("msgsky", "now setting metering area");

            List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();

            Rect areaRect1 = new Rect(-100, -100, 100, 100);
            meteringAreas.add(new Camera.Area(areaRect1, 600));

            Rect areaRect2 = new Rect(800, -1000, 1000, -800);
            meteringAreas.add(new Camera.Area(areaRect2, 400));

            params.setMeteringAreas(meteringAreas);
            camera.setParameters(params);
        }
    }


}
