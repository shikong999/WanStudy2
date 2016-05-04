package com.university.wanstudy.adapters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.university.wanstudy.R;
import com.university.wanstudy.model.IndexHot;

import org.xutils.x;

import java.util.List;

/**
 * Created by dkk on 2016/4/20.
 */
public class IndexGridViewHotAdapter extends MyAdapter<IndexHot> {

    private ImageView image;
    private TextView title;

    public IndexGridViewHotAdapter(Context context, List<IndexHot> data, int layRes) {
        super(context, data, layRes);
    }
    @Override
    public void binData(IndexHot indexHot, ViewHolder holder, int position) {
        image = ((ImageView) holder.getView(R.id.index_gridView_item_iv));
        title = ((TextView) holder.getView(R.id.index_gridView_item_tv));
        x.image().bind(image, indexHot.getSmallImgUrl());
        title.setText(indexHot.getName());
    }
}
