package com.example.sky.activitymodule;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ckt on 15-1-30.
 */

public class LifeCircle extends Activity{


    private TextView txtMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lifecircle);


        Toast.makeText(LifeCircle.this,"onCreate,执行数据初始化操作",Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        Toast.makeText(LifeCircle.this,"onstart..用户可以看见，但是不可以操作",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        Toast.makeText(LifeCircle.this,"onResume..获取到用户焦点，可以进行操作",Toast.LENGTH_SHORT).show();;
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        Toast.makeText(LifeCircle.this,"onPause..失去焦点，开始保存数据",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();

        Toast.makeText(LifeCircle.this,"onPause..失去焦点，开始保存数据",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();

        Toast.makeText(LifeCircle.this,"onStop",Toast.LENGTH_SHORT);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        Toast.makeText(LifeCircle.this,"onDestroy",Toast.LENGTH_SHORT);
    }


    private void alterDialog(){


    }


}