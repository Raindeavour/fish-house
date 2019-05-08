package com.example.mysecondapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mysecondapp.R;
import com.example.mysecondapp.bean.CSingleProductBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raindeavor丶W
 * Created on 2019/5/8
 */
public class SinglePLeftRVAdapter extends RecyclerView.Adapter<SinglePLeftRVAdapter.MyViewHolder> {

    List<CSingleProductBean.DataBean.CategoriesBean> categoriesBeans = new ArrayList<>();

    public SinglePLeftRVAdapter(List<CSingleProductBean.DataBean.CategoriesBean> categoriesBeans) {
        this.categoriesBeans = categoriesBeans;
    }

    @NonNull
    @Override
    public SinglePLeftRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleproduct_rv_left_item,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SinglePLeftRVAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.tv_singleP_rv_left.setText(categoriesBeans.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return categoriesBeans.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_singleP_rv_left;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_singleP_rv_left=itemView.findViewById(R.id.tv_singleP_rv_left);
        }
    }
}
