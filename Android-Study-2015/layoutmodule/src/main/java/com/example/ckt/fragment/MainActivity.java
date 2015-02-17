package com.example.ckt.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.ckt.layoutmodule.R;

/**
 * Created by ckt on 15-2-17.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment_main);

        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        MyFragment myFragment = new MyFragment();

        ft.add(R.id.scFragmentMain, myFragment);

        ft.commit();
    }
}
