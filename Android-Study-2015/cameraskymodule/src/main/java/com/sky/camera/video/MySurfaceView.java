package com.sky.camera.video;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by ckt on 2/9/15.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private Camera camera;
    private SurfaceHolder holder;

    public MySurfaceView(Context context, Camera camera) {
        super(context);

        this.camera = camera;

        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();

            startFaceDetection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        if (null == holder.getSurface() || camera == null) {
            return ;
        }

        try {
            camera.stopPreview();
            camera.setPreviewDisplay(holder);
            camera.startPreview();

            startFaceDetection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    private void startFaceDetection() {

        Camera.Parameters params = camera.getParameters();

        if (0 < params.getMaxNumDetectedFaces()) {

            camera.startFaceDetection();
        }
    }
}
