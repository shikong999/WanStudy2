package com.university.wanstudy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.university.wanstudy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dkk on 2016/4/20.
 */
public abstract class MyAdapter<T> extends BaseAdapter {
    private List<T> data;
    private LayoutInflater inflater;
    private int layRes;

    public MyAdapter(Context context, List<T> data, int layRes) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.layRes = layRes;
    }

    public void addRes(List<T> data) {
        if (data != null && data.size() > 0) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void upDateRes(List<T> data) {
        if (data != null && data.size() > 0) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public abstract void binData(T t, ViewHolder holder, int position);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(layRes, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        binData(getItem(position), holder, position);
        return convertView;
    }

    public static class ViewHolder {
        private View layout;
        private HashMap<Integer, View> cacheView;

        public ViewHolder(View convertView) {
            this.layout = convertView;
            cacheView = new HashMap<>();
        }

        public View getView(int resId) {
            View view = null;
            if (cacheView.containsKey(resId)) {
                view = cacheView.get(resId);
            } else {
                view = layout.findViewById(resId);
                cacheView.put(resId, view);
            }
            return view;
        }
    }
}
