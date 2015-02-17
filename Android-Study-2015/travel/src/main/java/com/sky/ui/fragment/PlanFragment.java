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
public class PlanFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View fmPlan = inflater.inflate(R.layout.fragment_plan, container, false);

        return fmPlan;
    }


    @Override
    public void onPause() {
        super.onPause();
    }
}
