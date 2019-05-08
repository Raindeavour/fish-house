package com.example.mysecondapp.fragment.classification;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysecondapp.R;
import com.example.mysecondapp.adapter.SinglePLeftRVAdapter;
import com.example.mysecondapp.adapter.SinglePRightRVAdapter;
import com.example.mysecondapp.bean.CSingleProductBean;
import com.example.mysecondapp.bean.CStrategyHeaderBean;
import com.example.mysecondapp.interfaces.CSingleProductAPI;
import com.example.mysecondapp.interfaces.CStrategyHeaderAPI;
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
public class SingleProductFragment extends Fragment {

    RecyclerView singlePLeftRV, singlePRightRV;
    SinglePLeftRVAdapter singlePLeftRVAdapter;
    SinglePRightRVAdapter singlePRightRVAdapter;

    String str1;
    List<CSingleProductBean.DataBean.CategoriesBean> categoriesBeans = new ArrayList<>();
    List<CSingleProductBean.DataBean.CategoriesBean.SubcategoriesBean> subcategoriesBeans=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_singleproduct, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getCategoriesBean();
        getSubCategoriesBean();

        singlePLeftRV = getActivity().findViewById(R.id.rv_singleP_left);
        singlePRightRV = getActivity().findViewById(R.id.rv_singleP_right);
        singlePLeftRVAdapter = new SinglePLeftRVAdapter(categoriesBeans);
        singlePRightRVAdapter = new SinglePRightRVAdapter(categoriesBeans,subcategoriesBeans);

        singlePLeftRV.setLayoutManager(new LinearLayoutManager(getContext()));
        singlePLeftRV.setAdapter(singlePLeftRVAdapter);

        singlePRightRV.setLayoutManager(new GridLayoutManager(getContext(),3));
        singlePRightRV.setAdapter(singlePRightRVAdapter);


    }

    public void getCategoriesBean() {

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CSingleProductAPI cSingleProductAPI = retrofit1.create(CSingleProductAPI.class);

        Call<ResponseBody> callSingleProduct = cSingleProductAPI.getCSingleProduct();

        callSingleProduct.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e("response", response.toString());
                    str1 = new String(response.body().bytes());//把原始数据转为字符串
                    CSingleProductBean cSingleProductBean = new Gson().fromJson(str1, CSingleProductBean.class);
                    List<CSingleProductBean.DataBean.CategoriesBean> categoriesBeans1 = cSingleProductBean.getData().getCategories();
                    for (int i = 0; i <= categoriesBeans1.size() - 1; i++) {
                        categoriesBeans.add(categoriesBeans1.get(i));
                    }
//                    Log.e("categoriesBeanssize",categoriesBeans1.size()+"");
                    //刷新页面
                    singlePLeftRVAdapter.notifyDataSetChanged();
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

    public void getSubCategoriesBean()
    {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CSingleProductAPI cSingleProductAPI = retrofit1.create(CSingleProductAPI.class);

        Call<ResponseBody> callSingleProduct = cSingleProductAPI.getCSingleProduct();

        callSingleProduct.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e("response", response.toString());
                    str1 = new String(response.body().bytes());//把原始数据转为字符串
                    CSingleProductBean cSingleProductBean = new Gson().fromJson(str1, CSingleProductBean.class);
                    List<CSingleProductBean.DataBean.CategoriesBean.SubcategoriesBean> subcategoriesBeans1 = cSingleProductBean.getData().getCategories().get(0).getSubcategories();
                    for (int i = 0; i <=subcategoriesBeans1.size() - 1; i++) {
                        subcategoriesBeans.add(subcategoriesBeans1.get(i));
                    }
                    //刷新页面
                    singlePLeftRVAdapter.notifyDataSetChanged();
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
