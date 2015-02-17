package com.sky.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.ui.R;


/**
 * Created by ckt on 15-2-17.
 */
public class MeFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View fmMe = inflater.inflate(R.layout.fragment_me, container, false);

        return fmMe;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
