package com.example.mysecondapp.fragment.classification;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donkingliang.headerviewadapter.adapter.HeaderViewAdapter;
import com.example.mysecondapp.R;
import com.example.mysecondapp.adapter.StrategyHeaderRVAdapter;
import com.example.mysecondapp.adapter.StrategyRVAdapter;
import com.example.mysecondapp.bean.CStrategyHeaderBean;
import com.example.mysecondapp.bean.CStrategyItemBean;
import com.example.mysecondapp.interfaces.CStrategyItemAPI;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Raindeavor丶W
 * Created on 2019/5/6
 */
public class StrategyFragment extends Fragment {

    RecyclerView strategyRV;
    StrategyRVAdapter strategyRVAdapter;
    StrategyHeaderRVAdapter strategyHeaderRVAdapter;
    List<CStrategyItemBean.DataBean.ChannelGroupsBean> channelGroupsBeans=new ArrayList<>();
    List<CStrategyHeaderBean.DataBean.ColumnsBean> columnsBeans = new ArrayList<>();
    String str1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_strategy,null);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        strategyRV = getActivity().findViewById(R.id.rv_strategy);
        strategyRVAdapter = new StrategyRVAdapter(getContext(),channelGroupsBeans);
        HeaderViewAdapter headerViewAdapter = new HeaderViewAdapter(strategyRVAdapter);
        strategyRV.setLayoutManager(new LinearLayoutManager(getContext()));
        strategyRV.setAdapter(headerViewAdapter);

        //添加头部
        LayoutInflater headerInflater = LayoutInflater.from(getContext());
        View headerView = headerInflater.inflate(R.layout.strategy_rv_header_item,null);
        headerViewAdapter.addHeaderView(headerView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CStrategyItemAPI cStrategyItemAPI = retrofit.create(CStrategyItemAPI.class);

        Call<ResponseBody> callStrategy = cStrategyItemAPI.getCStrategyItem();

        callStrategy.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e("response", response.toString());
                    str1 = new String(response.body().bytes());//把原始数据转为字符串
                    CStrategyItemBean cStrategyItemBean = new Gson().fromJson(str1,CStrategyItemBean.class);
                    List<CStrategyItemBean.DataBean.ChannelGroupsBean> channelGroupsBeans1 =cStrategyItemBean.getData().getChannel_groups();
                    for (int i = 0; i <= channelGroupsBeans1.size() - 1; i++) {
                        channelGroupsBeans.add(channelGroupsBeans1.get(i));
                    }
                    //刷新页面
                    strategyRVAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("failure", "fail");
            }
        });
    }
}