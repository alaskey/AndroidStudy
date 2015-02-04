package com.example.ckt.view.keyevent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Created by ckt on 2/3/15.
 */
public class MainActivity extends Activity{

    private TextView tvAlpha;
    private ImageView ivImg;
    private int alphaValue;
    private EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_keyevent);

        findViews();
    }

    private void findViews() {

        tvAlpha = (TextView) findViewById(R.id.tvAlpha);
        ivImg = (ImageView) findViewById(R.id.ivImg);
        alphaValue = 100;
        ivImg.setAlpha(alphaValue);
        tvAlpha.setText("alpha="+alphaValue*100/0xfff+"%");
        etInput = (EditText)findViewById(R.id.etInput);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        final String TAG = "in key event";

        Log.v(TAG,"onKeyDown keyCode= "+keyCode);
        Log.v(TAG, "onKeyDown string = " + event.toString());

        switch(keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                alphaValue = alphaValue >= 0xFF ? 0xFF : alphaValue + 10;
                Toast.makeText(MainActivity.this,"up",Toast.LENGTH_SHORT);
                Log.v("=========","up");
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                alphaValue = alphaValue <= 0x0 ? 0x0 : alphaValue - 10;
                Toast.makeText(MainActivity.this,"down",Toast.LENGTH_SHORT);
                Log.v("--------","down");
                break;
        }

       ivImg.setAlpha(alphaValue);


        return super.onKeyDown(keyCode, event);
    }
}
