package com.example.mysecondapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mysecondapp.R;
import com.example.mysecondapp.bean.CSingleProductBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raindeavor丶W
 * Created on 2019/5/8
 */
public class SinglePRightRVAdapter extends RecyclerView.Adapter<SinglePRightRVAdapter.MyViewHolder> {

    List<CSingleProductBean.DataBean.CategoriesBean> categoriesBeans = new ArrayList<>();
    List<CSingleProductBean.DataBean.CategoriesBean.SubcategoriesBean> subcategoriesBeans=new ArrayList<>();

    Context context;
    public SinglePRightRVAdapter(Context context,List<CSingleProductBean.DataBean.CategoriesBean> categoriesBeans,List<CSingleProductBean.DataBean.CategoriesBean.SubcategoriesBean> subcategoriesBeans) {
        this.context=context;
        this.categoriesBeans = categoriesBeans;
        this.subcategoriesBeans=subcategoriesBeans;
    }

    @NonNull
    @Override
    public SinglePRightRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleproduct_rv_right_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SinglePRightRVAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.tv_right_item_name.setText(categoriesBeans.get(i).getName());
        //动态传入
        myViewHolder.singleRightRVTwoAdapter = new SingleRightRVTwoAdapter(categoriesBeans.get(i).getSubcategories());
        myViewHolder.rv_singleP_right_item.setAdapter(myViewHolder.singleRightRVTwoAdapter);
        myViewHolder.rv_singleP_right_item.setLayoutManager(new GridLayoutManager(context,3));
    }

    @Override
    public int getItemCount() {
        return categoriesBeans.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_right_item_name;
        RecyclerView rv_singleP_right_item;
        SingleRightRVTwoAdapter singleRightRVTwoAdapter;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_right_item_name = itemView.findViewById(R.id.tv_right_item_name);
            rv_singleP_right_item = itemView.findViewById(R.id.rv_singleP_right_item);
        }
    }
}
