package com.university.wanstudy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.university.wanstudy.R;
import com.university.wanstudy.model.VideoDir;

import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public class MyDirListViewAdapter extends BaseAdapter {

    private List<VideoDir> videoDirs;
    private Context context;

    public MyDirListViewAdapter(List<VideoDir> videoDirs, Context context) {
        this.videoDirs = videoDirs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return videoDirs.size();
    }

    @Override
    public VideoDir getItem(int position) {
        return videoDirs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder holder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.dirsecondfragment,parent,false);
            holder=new MyViewHolder();
            holder.textView= (TextView) convertView.findViewById(R.id.xuhao);
            holder.textView1= (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }else {
            holder= (MyViewHolder) convertView.getTag();
        }
        holder.textView.setText(position+"");
        holder.textView1.setText(videoDirs.get(position).getName());
        return convertView;
    }

    class MyViewHolder {
        TextView textView;
        TextView textView1;
    }
}
