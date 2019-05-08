//package com.example.mysecondapp.fragment.classification;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.mysecondapp.R;
//import com.example.mysecondapp.adapter.StrategyHeaderRVAdapter;
//import com.example.mysecondapp.bean.CStrategyHeaderBean;
//import com.example.mysecondapp.interfaces.CStrategyHeaderAPI;
//import com.google.gson.Gson;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * Created by Raindeavor丶W
// * Created on 2019/5/7
// */
//public class StrategyHeaderFragment extends Fragment{
//
//    RecyclerView strategyHeaderRV;
//    StrategyHeaderRVAdapter strategyHeaderRVAdapter;
//    List<CStrategyHeaderBean.DataBean.ColumnsBean> columnsBeans = new ArrayList<>();
//    String str1;
//    Context context;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        final View view = inflater.inflate(R.layout.classification_strategy_rv_header,null);
//        return  view;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        strategyHeaderRV = getActivity().findViewById(R.id.rv_strategy_header);
//        strategyHeaderRVAdapter = new StrategyHeaderRVAdapter(context,columnsBeans);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        strategyHeaderRV.setLayoutManager(linearLayoutManager);
//        strategyHeaderRV.setAdapter(strategyHeaderRVAdapter);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://api.liwushuo.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        CStrategyHeaderAPI cStrategyHeaderAPI = retrofit.create(CStrategyHeaderAPI.class);
//
//        Call<ResponseBody> callStrategyHeader = cStrategyHeaderAPI.getCStrategyHeader();
//
//        callStrategyHeader.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    Log.e("response", response.toString());
//                    str1 = new String(response.body().bytes());//把原始数据转为字符串
//                    CStrategyHeaderBean cStrategyHeaderBean = new Gson().fromJson(str1,CStrategyHeaderBean.class);
//                    List<CStrategyHeaderBean.DataBean.ColumnsBean> columnsBeans1 =cStrategyHeaderBean.getData().getColumns();
//                    for (int i = 0; i <= columnsBeans1.size() - 1; i++) {
//                        columnsBeans.add(columnsBeans1.get(i));
//                    }
//                    //刷新页面
//                    strategyHeaderRVAdapter.notifyDataSetChanged();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.e("failure", "fail");
//            }
//        });
//    }
//}
