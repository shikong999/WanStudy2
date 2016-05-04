package com.university.wanstudy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.university.wanstudy.R;
import com.university.wanstudy.adapters.BookAdapter;
import com.university.wanstudy.model.Book;
import com.university.wanstudy.model.IndexHot;

import org.json.JSONArray;
import org.json.JSONException;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
public class IndroduceFragment extends Fragment {

    private static final String TAG=IndroduceFragment.class.getSimpleName();


    private String URL="http://api.wanmen.org:13132/course/";
    private String URL1="http://api.wanmen.org:13132/book?courseId=";
    private String id;

    private View view;
    private ImageView imageView;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private ListView listView;
    private View view1;
    private TextView textView3;

    public IndroduceFragment(String id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.introduce_fragment,container,false);
        imageView = ((ImageView) view.findViewById(R.id.tou_xiang));
        textView = ((TextView) view.findViewById(R.id.text_teacher_introduce));
        textView1 = ((TextView) view.findViewById(R.id.text_content));
        textView2 = ((TextView) view.findViewById(R.id.text_teacher_name));
        listView = ((ListView) view.findViewById(R.id.listview_book));
        textView3 = ((TextView) view.findViewById(R.id.tuijian));
        view1 = view.findViewById(R.id.videoView_fengexian3);
        initData();
        return view;
    }

    private void initData() {
        String newUrl=URL+id;
        String newUrl1=URL1+id;
        if(newUrl!=null){
            RequestParams params=new RequestParams(newUrl);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.e(TAG,"没有内容？"+result);
                    Gson gson=new Gson();
                    Log.e(TAG,"没有内容------？"+result);
                    IndexHot indexHot = gson.fromJson(result, IndexHot.class);
                    Log.e(TAG,"没有内容？"+indexHot.getLogoUrl());
                    ImageLoader.getInstance().displayImage(indexHot.getTeacherAvatarUrl(), imageView);
                    textView.setText(indexHot.getTeacherDescription());
                    textView1.setText(indexHot.getDescription());
                    textView2.setText(indexHot.getTeacherName());
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
            if(newUrl1!=null){
                RequestParams params1=new RequestParams(newUrl1);
                x.http().get(params1, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e(TAG,"没有内容？"+result);
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(result);
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Book>>() {
                            }.getType();
                            List<Book> data = gson.fromJson(jsonArray.toString(), type);
                            if(data.size()>=1){
                                BookAdapter adapter=new BookAdapter(data,getContext());
                                listView.setAdapter(adapter);
                            }else {
                                textView3.setVisibility(View.INVISIBLE);
                                view1.setVisibility(View.INVISIBLE);
                            }
                            Log.e(TAG,"没有内容------？"+result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        textView3.setVisibility(View.INVISIBLE);
                        view1.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });

            }
        }


    }
}
