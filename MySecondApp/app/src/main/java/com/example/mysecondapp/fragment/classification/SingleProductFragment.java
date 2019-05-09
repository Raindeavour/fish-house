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
import android.widget.LinearLayout;

import com.example.mysecondapp.R;
import com.example.mysecondapp.adapter.SinglePLeftRVAdapter;
import com.example.mysecondapp.adapter.SinglePRightRVAdapter;
import com.example.mysecondapp.bean.CSingleProductBean;
import com.example.mysecondapp.bean.CStrategyHeaderBean;
import com.example.mysecondapp.interfaces.CSingleProductAPI;
import com.example.mysecondapp.interfaces.CStrategyHeaderAPI;
import com.example.mysecondapp.interfaces.OnItemClickListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

/**
 * Created by Raindeavor丶W
 * Created on 2019/5/6
 */
public class SingleProductFragment extends Fragment {

    RecyclerView singlePLeftRV, singlePRightRV;
    SinglePLeftRVAdapter singlePLeftRVAdapter;
    SinglePRightRVAdapter singlePRightRVAdapter;

    private boolean mShouldScroll;
    private int mToPosition;

    String str1;
    List<CSingleProductBean.DataBean.CategoriesBean> categoriesBeans = new ArrayList<>();
    List<CSingleProductBean.DataBean.CategoriesBean.SubcategoriesBean> subcategoriesBeans = new ArrayList<>();

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
        singlePRightRVAdapter = new SinglePRightRVAdapter(getContext(), categoriesBeans, subcategoriesBeans);

        final LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        singlePLeftRV.setLayoutManager(linearLayoutManager1);
        singlePLeftRV.setAdapter(singlePLeftRVAdapter);

        final LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        singlePRightRV.setLayoutManager(linearLayoutManager2);
        singlePRightRV.setAdapter(singlePRightRVAdapter);

        singlePLeftRVAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                //从下往上滑正常,从上往下滑有些item不能置顶
//                singlePRightRV.smoothScrollToPosition(position);
                //不具有滑动效果
                linearLayoutManager2.scrollToPositionWithOffset(position, 0);
                //选中置于第二个position
                if (position <=categoriesBeans.size() - 7) {
//                    if (singlePLeftRV.getChildAt(position).getTop()==0) {
//                        singlePLeftRV.smoothScrollBy(0, (singlePLeftRV.getChildAt(1).getHeight()));
//                    } else {
//                    Log.e(TAG,"position0的top:"+ singlePLeftRV.getChildAt(0).getTop()+"" );
//                    Log.e(TAG, "position1的top:"+singlePLeftRV.getChildAt(1).getTop()+"" );
//                    Log.e(TAG, "position0的height:"+singlePLeftRV.getChildAt(0).getHeight()+"" );
//                    Log.e(TAG, "position:"+position+"的top:"+singlePLeftRV.getChildAt(position).getTop()+"" );
//                    Log.e(TAG, "position:"+position+"的height:"+singlePLeftRV.getChildAt(position).getHeight()+"" );
                        singlePLeftRV.smoothScrollToPosition(position + 7);
//                    }
                }

            }
        });

        singlePRightRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                //dx为RecyclerView沿着X轴(横向)滑动时偏移量.
                //正数为正向滑动(向右)偏移量,负数为反向滑动(向左)偏移量
                //dy为RecyclerView沿着y轴(纵向)滑动时偏移量.
                //正数为正向滑动(向下)偏移量,负数为反向滑动(向上)偏移量
                //firstVisibleItem 为RecyclerView 可见的第一个item的position
                //lastVisibleItem 为RecyclerView 可见的最后一个item的position
                //firstCompletelyVisibleItem 完全可见的第一个item
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    int firstItem = linearLayoutManager2.findFirstVisibleItemPosition();
                    int firstItem2 = linearLayoutManager2.findFirstCompletelyVisibleItemPosition();
                    if (firstItem2 == categoriesBeans.size() - 1) {
                        linearLayoutManager1.scrollToPositionWithOffset(firstItem + 1, 150);
                        singlePLeftRVAdapter.setSelectionPosition(firstItem + 1);
                        singlePLeftRVAdapter.notifyDataSetChanged();
                    } else {
                        linearLayoutManager1.scrollToPositionWithOffset(firstItem, 150);
                        singlePLeftRVAdapter.setSelectionPosition(firstItem);
                        singlePLeftRVAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


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

    public void getSubCategoriesBean() {
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
                    for (int i = 0; i <= subcategoriesBeans1.size() - 1; i++) {
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
