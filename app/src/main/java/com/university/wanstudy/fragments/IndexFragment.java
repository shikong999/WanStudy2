package com.university.wanstudy.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.university.wanstudy.PlayActivity;
import com.university.wanstudy.R;
import com.university.wanstudy.adapters.IndexGridViewHotAdapter;
import com.university.wanstudy.adapters.IndexViewPagerAdapter;
import com.university.wanstudy.model.IndexHot;
import com.university.wanstudy.model.IndexViewPagerItem;
import com.university.wanstudy.view.MyGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class IndexFragment extends Fragment implements Handler.Callback, AdapterView.OnItemClickListener {


    private static final String TAG = IndexFragment.class.getSimpleName();
    private ViewPager viewPager;
    private static final String HOT_URL = "http://api.wanmen.org:13132/hotCourse";
    private static final String RECOMMENT_URL = "http://api.wanmen.org:13132/newCourse";
    private static final String ITEM_URL = "http://api.wanmen.org:13132/banner";
    private IndexViewPagerAdapter viewPagerAdapter;
    private Handler handler;
    private int index = 0;
    private MyGridView gridViewHot;
    private MyGridView gridViewRecomment;
    private IndexGridViewHotAdapter indexGridViewHotAdapter;
    private IndexGridViewHotAdapter indexGridViewRecommentAdapter;

    public IndexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_index, container, false);
        //注册viewPager
        viewPager = ((ViewPager) view.findViewById(R.id.index_viewPager));
        //热门课程适配器
        indexGridViewHotAdapter = new IndexGridViewHotAdapter(getContext(), null, R.layout.index_gridview_item);
        indexGridViewRecommentAdapter = new IndexGridViewHotAdapter(getContext(), null, R.layout.index_gridview_item);
        gridViewHot = ((MyGridView) view.findViewById(R.id.index_gridView_hot));
        gridViewHot.setAdapter(indexGridViewHotAdapter);
        gridViewRecomment = ((MyGridView) view.findViewById(R.id.index_gridView_recomment));
        gridViewRecomment.setAdapter(indexGridViewRecommentAdapter);
        //热门课程设置监听
        gridViewHot.setOnItemClickListener(this);
        //推荐课程设置监听
        gridViewRecomment.setOnItemClickListener(this);
        //轮播页的viewPager适配器
        viewPagerAdapter = new IndexViewPagerAdapter(getChildFragmentManager(), null);
        viewPager.setAdapter(viewPagerAdapter);
        initData();
        handler = new Handler(this);
        handler.sendEmptyMessageDelayed(1, 4000);
        //热门课程数据加载
        HotClass();
        //推荐课程的数据加载
        Recommend();
        return view;
    }

    private void Recommend() {
        RequestParams params = new RequestParams(RECOMMENT_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<IndexHot>>() {
                    }.getType();
                    List<IndexHot> data = gson.fromJson(jsonArray.toString(), type);
                    indexGridViewRecommentAdapter.upDateRes(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void HotClass() {
        RequestParams params = new RequestParams(HOT_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<IndexHot>>() {
                    }.getType();
                    List<IndexHot> data = gson.fromJson(jsonArray.toString(), type);
                    indexGridViewHotAdapter.upDateRes(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initData() {
        RequestParams params = new RequestParams(ITEM_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<IndexViewPagerItem>>() {
                    }.getType();
                    List<IndexViewPagerItem> indexViewPagerItems = gson.fromJson(jsonArray.toString(), type);
                    int size = indexViewPagerItems.size();
                    List<Fragment> data = new ArrayList<Fragment>();
                    for (int i = 0; i < size; i++) {
                        IndexViewPagerItem indexViewPagerItem = indexViewPagerItems.get(i);
//                        Log.e(TAG, "indexViewPagerItem--" + indexViewPagerItem);
                        IndexViewPagerItemFragment indexViewPagerItemFragment = new IndexViewPagerItemFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("image", indexViewPagerItem);
                        indexViewPagerItemFragment.setArguments(bundle);
                        data.add(indexViewPagerItemFragment);
                        viewPagerAdapter.updateRes(data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled");
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished");
            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                handler.sendEmptyMessageDelayed(1, 4000);
                viewPager.setCurrentItem(index++);
                if (index >= 4) {
                    index = 0;
                }
                break;
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), PlayActivity.class);
        switch (view.getId()) {
            case R.id.index_gridView_hot:
                Toast.makeText(getActivity(), "正在开发中哦---", Toast.LENGTH_SHORT).show();
                IndexHot indexHot = indexGridViewHotAdapter.getItem(position);
                intent.putExtra("id", indexHot.getId() + "");
                break;
            case R.id.index_gridView_recomment:
                IndexHot recomment = indexGridViewRecommentAdapter.getItem(position);
                intent.putExtra("id", recomment.getId() + "");
                Snackbar.make(view, "正在开发中哦---", Snackbar.LENGTH_SHORT).show();
                break;
        }
        startActivity(intent);
    }
}
