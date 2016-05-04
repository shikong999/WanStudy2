package com.university.wanstudy.adapters;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.university.wanstudy.MyApp;
import com.university.wanstudy.R;
import com.university.wanstudy.model.MajorModel;

import org.xutils.x;

import java.util.List;

/**
 * 课程适列表配器
 */
public class MajorAdapter extends MyBaseAdapter<MajorModel> {
    public MajorAdapter(Context context, List<MajorModel> data, int... layouts) {
        super(context, data, layouts);
    }

    @Override
    public void bindData(ViewHolder holder, MajorModel majorModel) {
        //图片
        ImageView majorImg = (ImageView) holder.getView(R.id.major_item_img);
        String logoUrl = majorModel.getLogoUrl();
        if (logoUrl != null) {
        x.image().bind(majorImg, logoUrl, MyApp.options);
        }
        //标题
        TextView majorTitle = (TextView) holder.getView(R.id.major_item_title);
        majorTitle.setText(majorModel.getName());
        majorTitle.setTextColor(Color.BLACK);
        //简介
        TextView majorDescription = (TextView) holder.getView(R.id.major_item_content);
        majorDescription.setText(majorModel.getShortDescription());
        majorDescription.setTextColor(Color.BLACK);
        //讲师
        ((TextView) holder.getView(R.id.major_item_teacherName)).setText(majorModel.getTeacherName());
        //课时
        ((TextView) holder.getView(R.id.major_item_numOfClasses)).setText(majorModel.getNumOfClasses() + "讲");
        //时长
        ((TextView) holder.getView(R.id.major_item_timePerPart)).setText(majorModel.getTimePerPart() + "分/讲");
    }
}
