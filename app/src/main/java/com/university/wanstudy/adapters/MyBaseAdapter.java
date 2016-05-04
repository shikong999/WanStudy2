package com.university.wanstudy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 可以添加多布局
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private List<T> data;
    //子布局的layout的ID,放到一个数组里
    private int[] layouts;
    private LayoutInflater inflater;

    public MyBaseAdapter(Context context,List<T> data,int... layouts) {
        this.layouts = layouts;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (data==null){
            this.data=new ArrayList<>();
        }else {
            this.data=data;
        }
    }

    public void addRes(List<T> data){
        if (data!=null&&data.size()>0){
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void upDateRes(List<T> data){
        if (data!=null&&data.size()>0){
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 根据position获取对应的布局类型,int型,要求0,1,2,3...
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        //定义一个默认类型
        int type=0;
//获取指定位置对应的数据
        T t = getItem(position);
        //获取类对应的class
        Class<?> cls = t.getClass();
        try {
            //根据字段名获取指定字段
            Field field = cls.getDeclaredField("type");
            //设置权限,可获取指定字段
            field.setAccessible(true);
            //获取字段中的值
            type=field.getInt(t);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return type;
    }

    /**
     * 获取多布局的布局种类数
     * @return
     */
    @Override
    public int getViewTypeCount() {
        //传进来几个布局,就有多少总类
        return layouts.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            //实例化.layout id需要根据不同的type进行加载
            //获取position对应的viewType
            int viewType = getItemViewType(position);
            //获取viewType对应的布局
            int loyoutRes=layouts[viewType];
            //导入布局
            convertView=inflater.inflate(loyoutRes,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        bindData(holder,getItem(position));
        return convertView;
    }

    public abstract void bindData(ViewHolder  holder,T t);


    public static class ViewHolder{
        private View convertView;
        private Map<Integer,View> cacheView;

        public ViewHolder(View convertView) {
            this.convertView = convertView;
            cacheView=new HashMap<>();
        }

        public View getView(int resId){
            View view=null;
            if (cacheView.containsKey(resId)){
                view=cacheView.get(resId);
            }else {
                view=convertView.findViewById(resId);
                cacheView.put(resId,view);
            }
            return view;
        }
    }
}
