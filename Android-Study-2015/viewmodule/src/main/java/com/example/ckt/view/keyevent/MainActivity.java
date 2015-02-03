package com.example.ckt.view.keyevent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ckt.view.setlistener.R;

/**
 * Created by ckt on 2/3/15.
 */
public class MainActivity extends Activity{

    private TextView tvAlpha;
    private ImageView ivImg;
    private int alphaValue;

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
    }
}
