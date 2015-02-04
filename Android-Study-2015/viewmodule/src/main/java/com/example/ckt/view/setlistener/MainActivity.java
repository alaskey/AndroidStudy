package com.example.ckt.view.setlistener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ckt.view.keyevent.R;


/**
 * Created by ckt on 2/3/15.
 */
public class MainActivity extends Activity implements View.OnClickListener {


    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_setlistener);

        findViews();

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
    }

    private void findViews() {

        btnOne = (Button)findViewById(R.id.btnOne);
        btnTwo = (Button)findViewById(R.id.btnTwo);
        btnThree = (Button)findViewById(R.id.btnThree);
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {
            case R.id.btnOne:
                intent = new Intent(MainActivity.this,MethodOne.class);
                startActivity(intent);
                break;
            case R.id.btnTwo:
                intent = new Intent(MainActivity.this,MethodTwo.class);
                startActivity(intent);
                break;
            case R.id.btnThree:
                intent = new Intent(MainActivity.this,MethodThree.class);
                startActivity(intent);
                break;
        }

    }
}
