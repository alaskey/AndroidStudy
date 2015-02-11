package com.sky.camera.video;

import android.hardware.Camera;
import android.util.Log;

/**
 * Created by ckt on 2/11/15.
 */
public class MyFaceDetectionListener implements Camera.FaceDetectionListener{

    @Override
    public void onFaceDetection(Camera.Face[] faces, Camera camera) {

        if (faces.length > 0) {
            Log.d("msgsky","face detected:" + faces.length
            + " face 1 location x: " + faces[0].rect.centerX()
            + " y: " + faces[0].rect.centerY());
        }
    }
}
