package com.university.wanstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.university.wanstudy.adapters.MajorAdapter;
import com.university.wanstudy.model.MajorModel;
import com.university.wanstudy.utils.NetUtil;
import com.university.wanstudy.utils.SysExitUtil;
import com.university.wanstudy.view.TopBar;

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
 * 课程窗口
 */
public class MajorActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private static final String TAG = MajorActivity.class.getSimpleName();
    private int major_id;//课程总id
    private String majorUrl = "http://api.wanmen.org:13132/course?major_id=";
    private ListView major_list;//显示课程列表
    private SwipeRefreshLayout major_swipe;//下拉刷新
    private RequestParams majorParams;
    private List<MajorModel> data;//列表数据源
    private MajorAdapter adapter;//列表适配器
    private TopBar major_topbar;//自定义TopBar
    private String majorTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysExitUtil.activityList.add(this);
        setContentView(R.layout.activity_major);
        //获取专业页传进来的id
        Intent intent = getIntent();
        majorTitle = intent.getStringExtra("major_title");
        major_id = intent.getIntExtra("major_id", 10);
        initView();
        initData();
    }

    //声明DbManager.DaoConfig
    private DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
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
        if (NetUtil.isNetworkAvailable(this)) {
//            Toast.makeText(this, "当前有可用网络！", Toast.LENGTH_SHORT).show();
            havaNetwork();
        } else {
//            Snackbar.make(major_list, "当前没有可用网络！", Snackbar.LENGTH_LONG).setAction("action", null).show();
//            Toast.makeText(this, "当前没有可用网络！", Toast.LENGTH_SHORT).show();
            noNetwork();
        }
    }

    private void noNetwork() {
        //从数据库中取数据
        try {
//                WhereBuilder whereBuilder=WhereBuilder.b();
            data = db.selector(MajorModel.class).orderBy("id", true).where("major_id", "=", major_id).findAll();
            for (MajorModel model : data) {
                Log.e(TAG, "Major: " + model.getName());
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
        //初始化数据
        majorParams = new RequestParams(majorUrl + major_id);
        //获取gridview数据
        x.http().get(majorParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "genre-onSuccess_" + result);
                try {
                    //解析json
                    JSONArray array = new JSONArray(result);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<MajorModel>>() {
                    }.getType();
                    data = gson.fromJson(array.toString(), type);
                    //更新数据源
                    adapter.upDateRes(data);
                    //保存到数据库
                    for (MajorModel majorModel : data) {
                        try {
                            db.save(majorModel);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }

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

    //初始化布局
    private void initView() {
        major_list = (ListView) findViewById(R.id.major_list);
        adapter = new MajorAdapter(this, getData(), R.layout.major_item);
        major_list.setAdapter(adapter);
        major_list.setOnItemClickListener(this);
        //实例化下拉刷新
        major_swipe = (SwipeRefreshLayout) findViewById(R.id.major_swipe);
        major_swipe.setColorSchemeResources(R.color.colorBlue);
        major_swipe.setOnRefreshListener(this);
        //设置刷新时小球的颜色
        major_topbar = (TopBar) findViewById(R.id.major_topbar);
        major_topbar.setBack();
        major_topbar.setTitle(majorTitle);
    }

    //原始假数据
    private List<MajorModel> getData() {
        List<MajorModel> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            MajorModel majorModel = new MajorModel();
            majorModel.setId(i);
            majorModel.setName("课程" + i);
            majorModel.setTeacherName("讲师" + i);
            majorModel.setNumOfClasses(10);
            majorModel.setTimePerPart(15);
            data.add(majorModel);
        }
        return data;
    }

    //下拉刷新监听
    @Override
    public void onRefresh() {
        initData();
        //完成更新后隐藏小球
        major_swipe.setRefreshing(false);
    }

    //跳转视频页
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MajorModel adapterItem = adapter.getItem(position);
        Snackbar.make(view, "course_id:" + adapterItem.getId(), Snackbar.LENGTH_LONG).setAction("action", null).show();
        Intent intent=new Intent(this,PlayActivity.class);
        intent.putExtra("id",adapterItem.getId()+"");
        startActivity(intent);
    }
}
