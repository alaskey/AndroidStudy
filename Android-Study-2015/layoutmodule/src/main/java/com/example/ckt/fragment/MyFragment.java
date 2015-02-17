package com.example.ckt.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ckt.layoutmodule.R;

/**
 * Created by ckt on 15-2-17.
 */
public class MyFragment extends Fragment {

    private final String TAG = "SKY";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //reSource：View的layout的ID
        //
        //root： 生成的层次结构的根视图
        //
        //return 填充的层次结构的根视图

        View rootView = inflater.inflate(R.layout.activity_fragment_info, container, false);

        ((TextView) rootView.findViewById(R.id.tvFragmentSay)).setText("it's fragment . java");

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
