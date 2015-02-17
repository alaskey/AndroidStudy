package com.example.ckt.layoutmodule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.ckt.fragment.MyFragment;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnLinearLayout;
    private Button btnFrameLayout;
    private Button btnAbsoluteLayout;
    private Button btnRelativeLayout;
    private Button btnTableLayout;
    private Button btnDynamic;
    private Button btnScrollView;
    private Button btnFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        btnLinearLayout.setOnClickListener(this);
        btnFrameLayout.setOnClickListener(this);
        btnAbsoluteLayout.setOnClickListener(this);
        btnRelativeLayout.setOnClickListener(this);
        btnTableLayout.setOnClickListener(this);
        btnDynamic.setOnClickListener(this);
        btnScrollView.setOnClickListener(this);
        btnFragment.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void findViews() {

        btnLinearLayout = (Button) findViewById(R.id.btnLinearLayout);
        btnFrameLayout = (Button) findViewById(R.id.btnFrameLayout);
        btnAbsoluteLayout = (Button) findViewById(R.id.btnAbsoluteLayout);
        btnRelativeLayout = (Button) findViewById(R.id.btnRelativeLayout);
        btnTableLayout = (Button) findViewById(R.id.btnTableLayout);
        btnDynamic = (Button) findViewById(R.id.btnAddDynamic);
        btnScrollView = (Button) findViewById(R.id.btnScrollView);
        btnFragment = (Button) findViewById(R.id.btnFragment);
    }


    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {

            case R.id.btnLinearLayout:
                intent = new Intent(MainActivity.this, LinearLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btnFrameLayout:
                intent = new Intent(MainActivity.this, FrameLayoutActivity.class);
                startActivity(intent);
                break;

            case R.id.btnAbsoluteLayout:
                intent = new Intent(MainActivity.this, AbsoluteActivity.class);
                startActivity(intent);
                break;
            case R.id.btnRelativeLayout:
                intent = new Intent(MainActivity.this, RelativeLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btnTableLayout:
                intent = new Intent(MainActivity.this, TableLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btnAddDynamic:
                intent = new Intent(MainActivity.this,com.example.ckt.dynamic.MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnScrollView:
                intent = new Intent(MainActivity.this, com.example.ckt.scrollview.MainActivity.class);
                startActivity(intent);
            case R.id.btnFragment:
                intent = new Intent(MainActivity.this, com.example.ckt.fragment.MainActivity.class);
                startActivity(intent);
        }
    }
}
