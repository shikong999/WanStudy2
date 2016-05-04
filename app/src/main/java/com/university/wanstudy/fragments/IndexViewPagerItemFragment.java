package com.university.wanstudy.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.university.wanstudy.PlayActivity;
import com.university.wanstudy.R;
import com.university.wanstudy.model.IndexViewPagerItem;

import org.xutils.x;

/**
 * 首页轮播图Item
 */
public class IndexViewPagerItemFragment extends Fragment implements View.OnClickListener {


    private static final String TAG = IndexViewPagerItemFragment.class.getSimpleName();
    private IndexViewPagerItem data;
    private ImageView image;

    public IndexViewPagerItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_index_view_pager_item, container, false);
        FrameLayout layout= (FrameLayout) view.findViewById(R.id.index_viewPager_item);
        layout.setOnClickListener(this);
        image = ((ImageView) view.findViewById(R.id.index_viewPager_item_iv));
        data = getArguments().getParcelable("image");
        Log.e(TAG,"data----"+data.getLogoUrl());
        x.image().bind(image,data.getLogoUrl());
        return view;
    }

    /**
     * 跳转视频页
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getContext(), PlayActivity.class);
        intent.putExtra("id",data.getId()+"");
        startActivity(intent);
    }
}
