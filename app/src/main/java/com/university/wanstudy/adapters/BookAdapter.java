package com.university.wanstudy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.university.wanstudy.MyApp;
import com.university.wanstudy.R;
import com.university.wanstudy.model.Book;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/4/22.
 */
public class BookAdapter extends BaseAdapter {

    private List<Book> data;
    private Context context;

    public BookAdapter(List<Book> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Book getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.book_item,parent,false);
            holder.imageView= (ImageView) convertView.findViewById(R.id.listview_book_image);
            holder.textBookName= (TextView) convertView.findViewById(R.id.listview_book_name);
            holder.textContent= (TextView) convertView.findViewById(R.id.listview_book_indroduce);
            holder.textRequest= (TextView) convertView.findViewById(R.id.listview_book_required);
            holder.textWriter= (TextView) convertView.findViewById(R.id.listview_book_writer);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

//        try {
//            URL url=new URL(getItem(position).getLogoUrl());
//            InputStream inputStream = url.openStream();
//            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//            holder.imageView.setImageBitmap(bitmap);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        x.image().bind(holder.imageView, getItem(position).getLogoUrl(), MyApp.options);
        //ImageLoader.getInstance().displayImage(getItem(position).getLogoUrl(), holder.imageView);
        holder.textBookName.setText(getItem(position).getName());
        holder.textContent.setText(getItem(position).getDescription());
        if(getItem(position).isRequired()){
            holder.textRequest.setText("必读");
        }
        holder.textWriter.setText(getItem(position).getAuthor());
        return convertView;
    }

    private static class ViewHolder{

        ImageView imageView;
        TextView textBookName;
        TextView textContent;
        TextView textRequest;
        TextView textWriter;

    }
}
