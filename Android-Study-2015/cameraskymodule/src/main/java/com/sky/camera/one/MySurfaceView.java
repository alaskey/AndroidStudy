package com.sky.camera.one;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by ckt on 2/6/15.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private final String TAG = "MSG";


    private SurfaceHolder holder;
    private Camera camera;

    public MySurfaceView(Context context, Camera camera) {
        super(context);

        this.camera = camera;

        // install a surface holder.callback so we get notified
        // when the underlying surface is created and destroyed

        holder = getHolder();
        holder.addCallback(this);

        // deprecated setting, but required on Android versions prior to 3.0
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        try {

            camera.setPreviewDisplay(holder);

            camera.startPreview();
        } catch (IOException e) {
//            Log.d(TAG,"Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        if (null == holder.getSurface()) {

            // preview surface dont exist
            return ;
        }

        // stop preview before making change
        try {

            camera.stopPreview();
        } catch(Exception e) {

        }

        try {

            camera.setPreviewDisplay(holder);

            camera.startPreview();
        } catch(Exception e) {

//            Log.d(TAG,"Error starting camera preview" + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        camera.release();
    }
}
