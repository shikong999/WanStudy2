package com.university.wanstudy.adapters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.university.wanstudy.MyApp;
import com.university.wanstudy.R;
import com.university.wanstudy.model.GenreModel;

import org.xutils.x;

import java.util.List;

/**
 * 专业列表适配器
 */
public class GenreAdapter extends MyBaseAdapter<GenreModel> {
    public GenreAdapter(Context context, List<GenreModel> data, int... layouts) {
        super(context, data, layouts);
    }

    @Override
    public void bindData(ViewHolder holder, GenreModel genreModel) {
        ImageView genreImg = (ImageView) holder.getView(R.id.genre_item_img);
        //加载图片
        String logoUrl = genreModel.getLogoUrl();
        if (logoUrl != null) {
            x.image().bind(genreImg, logoUrl, MyApp.options);
        }
        //设置标题
        ((TextView) holder.getView(R.id.genre_item_title)).setText(genreModel.getName());
    }
}
