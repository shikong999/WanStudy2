package com.university.wanstudy.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.university.wanstudy.R;

/**
 * 离线视频Item
 */
public class VideoItemFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_item, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
