package com.example.mysecondapp.fragment.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysecondapp.R;
import com.example.mysecondapp.adapter.CordialXRVAdapter;
import com.example.mysecondapp.bean.ListBean;
import com.example.mysecondapp.interfaces.ListAPI;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CordialBoxAreaFragment extends Fragment {

    XRecyclerView cordialXRV;
    CordialXRVAdapter cordialXRVAdapter;
    List<String> data = new ArrayList<>();
    List<ListBean.DataBean.ItemsBean> items = new ArrayList<>();
    String str1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cordialboxarea, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cordialXRV = (XRecyclerView) ((getActivity()).findViewById(R.id.xRV_cordial));
        for (int i = 0; i <= 20; i++) {
            data.add("红红火火恍恍惚惚哈哈哈哈哈！");
        }
        cordialXRVAdapter = new CordialXRVAdapter(items, data);
        cordialXRV.setLayoutManager(new GridLayoutManager(getContext(), 2));
        cordialXRV.setAdapter(cordialXRVAdapter);
        // 可以设置是否开启加载更多/下拉刷新
        cordialXRV.setLoadingMoreEnabled(false);
        cordialXRV.setRefreshProgressStyle(ProgressStyle.BallZigZag); //设定下拉刷新样式
        cordialXRV.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag);//设定上拉加载样式

        //添加头部
        LayoutInflater headerInflater = LayoutInflater.from(getContext());
        View headerView = headerInflater.inflate(R.layout.list_cordial_xrv_header, null);
        cordialXRV.addHeaderView(headerView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ListAPI listAPI = retrofit.create(ListAPI.class);
        Call<ResponseBody> callList = listAPI.getList();

        callList.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e("response", response.toString());
                    str1 = new String(response.body().bytes());//把原始数据转为字符串
                    ListBean listBean = new Gson().fromJson(str1, ListBean.class);
                    List<ListBean.DataBean.ItemsBean> items1 = listBean.getData().getItems();
                    for (int i = 0; i <= items1.size() - 1; i++) {
                        items.add(items1.get(i));
                    }
                    //刷新页面
                    cordialXRVAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("failure", "fail");
            }
        });

        cordialXRV.setLoadingListener(new XRecyclerView.LoadingListener() {
            //下拉刷新监听
            @Override
            public void onRefresh() {
                cordialXRV.refreshComplete();
            }


            //上拉加载监听
            @Override
            public void onLoadMore() {
            }
        });
    }
//    public List<ListBean.DataBean.ItemsBean> getItems(){
//        return this.items;
//    }
}
