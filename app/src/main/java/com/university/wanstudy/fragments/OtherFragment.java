package com.university.wanstudy.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.university.wanstudy.MajorActivity;
import com.university.wanstudy.R;
import com.university.wanstudy.adapters.GenreAdapter;
import com.university.wanstudy.model.GenreModel;
import com.university.wanstudy.utils.NetUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 3大类页面
 */
public class OtherFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private static final String TAG = OtherFragment.class.getSimpleName();
    private String genreUrl = "http://api.wanmen.org:13132/major?genre_id=";
    private int genre_id;//课程类型id
    private GenreAdapter adapter;//课程适配器
    private GridView genre_grid;//课程
    private SwipeRefreshLayout genre_swipe_container;//原生下拉刷新
    private List<GenreModel> data;//课程数据源
    private RequestParams genreParams;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        genre_id = getArguments().getInt("genre_id", 1);
        Log.e(TAG, "genre_id:" + genre_id);
        initView(view);

        initData();

        return view;
    }

    //声明DbManager.DaoConfig
    public static DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName("wuStudy.db")
            .setDbVersion(1)
//            .setDbDir(new File("/mnt/sdcard"))
            .setAllowTransaction(true);
    //声明DbManager
    private DbManager db;


    //初始化数据
    private void initData() {
        //实例化DbManager
        db = x.getDb(daoConfig);
        //片段网络可用
        if (NetUtil.isNetworkAvailable(getActivity())) {
//            Toast.makeText(getContext(), "当前有可用网络！", Toast.LENGTH_SHORT).show();
            havaNetwork();
        } else {
            //Snackbar.make(genre_grid, "当前没有可用网络！", Snackbar.LENGTH_LONG).setAction("action", null).show();
            Toast.makeText(getContext(), "当前没有可用网络！", Toast.LENGTH_SHORT).show();
            noNetwork();
        }
    }

    /**
     * 网络不可用
     */
    private void noNetwork() {
        //从数据库中取数据
        try {
//                WhereBuilder whereBuilder=WhereBuilder.b();
            data = db.selector(GenreModel.class).orderBy("id", false).where("genre_id", "=", genre_id).findAll();
            for (GenreModel model : data) {
                Log.e(TAG, "Genre: " + model.getName());
            }
            if (data != null) {
                adapter.upDateRes(data);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 网络可用
     */
    private void havaNetwork() {
        genreParams = new RequestParams(genreUrl + genre_id);
        //获取gridview数据
        x.http().get(genreParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    //解析json
                    JSONArray array = new JSONArray(result);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GenreModel>>() {
                    }.getType();
                    data = gson.fromJson(array.toString(), type);
                    adapter.upDateRes(data);
                    //保存到数据库
                    for (GenreModel genreModel : data) {
                        try {
                            db.save(genreModel);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.e(TAG, "genre-onSuccess_" + result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "genre-onError_" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "genre-onCancelled_" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "genre-onFinished");
            }
        });
    }

    private void initView(View view) {
        adapter = new GenreAdapter(getContext(), getData(), R.layout.genre_item);
        genre_grid = (GridView) view.findViewById(R.id.genre_grid);
        genre_grid.setAdapter(adapter);
        genre_grid.setOnItemClickListener(this);
        //实例化下拉刷新
        genre_swipe_container = (SwipeRefreshLayout) view.findViewById(R.id.genre_swipe_container);
        genre_swipe_container.setOnRefreshListener(this);
        //设置刷新时小球的颜色
        genre_swipe_container.setColorSchemeResources(R.color.colorBlue);
    }

    //原始数据
    private List<GenreModel> getData() {
        List<GenreModel> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GenreModel genreModel = new GenreModel();
            genreModel.setName("专业" + i);
            genreModel.setId(i);
            data.add(genreModel);
        }
        return data;
    }

    //下拉刷新监听
    @Override
    public void onRefresh() {
        initData();
        //完成更新后隐藏小球
        genre_swipe_container.setRefreshing(false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GenreModel adapterItem = adapter.getItem(position);
        Intent intent = new Intent(getContext(), MajorActivity.class);
        intent.putExtra("major_title", adapterItem.getName());
        intent.putExtra("major_id", adapterItem.getId());
        startActivity(intent);
    }
}
