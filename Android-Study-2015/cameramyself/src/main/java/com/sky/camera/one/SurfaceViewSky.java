package com.sky.camera.one;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import java.io.IOException;

/**
 * Created by ckt on 2/9/15.
 */
public class SurfaceViewSky extends SurfaceView implements SurfaceHolder.Callback {

    private Camera camera;
    private SurfaceHolder holder;

    public SurfaceViewSky(Context context, Camera camera) {
        super(context);

        this.camera = camera;
        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        try {

            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

        if (holder.getSurface() == null) {

            return;
        }

        try {
            this.camera.stopPreview();
        } catch (Exception e) {

        }

        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (Exception e) {

        }
    }
}













