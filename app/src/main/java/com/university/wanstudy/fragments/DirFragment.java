package com.university.wanstudy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.university.wanstudy.R;
import com.university.wanstudy.adapters.MyDirListViewAdapter;
import com.university.wanstudy.model.VideoDir;
import com.university.wanstudy.model.VideoDirChild;

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
public class DirFragment extends Fragment implements ExpandableListView.OnGroupExpandListener, AdapterView.OnItemClickListener {

    private String URLDIR="http://api.wanmen.org:13132/topic?course_id=";

    private String id;
    private ListView expandableListView;
    private View view;

    private MyDirListViewAdapter adapter;

    private List<VideoDir> dir;

    public DirFragment(String id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dir_fragment,container,false);
        expandableListView = ((ListView) view.findViewById(R.id.expandableListView));
        initData();
        expandableListView.setOnItemClickListener(this);
        return view;
    }

    private void initData() {
        String newURL=URLDIR+id;
        if(newURL!=null){
            RequestParams params=new RequestParams(newURL);
            x.http().get(params, new Callback.CommonCallback<String>() {

                @Override
                public void onSuccess(String  result) {

                    try {
                        JSONArray jsonArray=new JSONArray(result);
                        Gson gson=new Gson();
                        Type type=new TypeToken<List<VideoDir>>(){}.getType();
                        dir= gson.fromJson(jsonArray.toString(), type);
                        adapter=new MyDirListViewAdapter(dir,getContext());
                        expandableListView.setAdapter(adapter);
                 } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
        }

    }

    private void child(List<VideoDir> dir) {

    }

    @Override
    public void onGroupExpand(int groupPosition) {

    }

    @Override
    public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
        String url="http://api.wanmen.org:13132/part?topic_id="+dir.get(position).getId();
        RequestParams params=new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray jsonArray=new JSONArray(result);
                    Gson gson=new Gson();
                    Type type=new TypeToken<List<VideoDirChild>>(){}.getType();
                    List<VideoDirChild> data=gson.fromJson(jsonArray.toString(), type);
                    Toast toast=new Toast(getContext());
                    for(int i=0;i<data.size();i++){
                        TextView text=new TextView(getContext());
                        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        text.setLayoutParams(params);
                        text.setText(data.get(i).getName());
                        toast.setView(text);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
    }
}
