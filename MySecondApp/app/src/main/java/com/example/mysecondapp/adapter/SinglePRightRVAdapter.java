package com.example.mysecondapp.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
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
    List<CSingleProductBean.DataBean.CategoriesBean.SubcategoriesBean> subcategoriesBeans = new ArrayList<>();

    public SinglePRightRVAdapter(List<CSingleProductBean.DataBean.CategoriesBean> categoriesBeans, List<CSingleProductBean.DataBean.CategoriesBean.SubcategoriesBean> subcategoriesBeans) {
        this.categoriesBeans = categoriesBeans;
        this.subcategoriesBeans = subcategoriesBeans;
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


            if (i>=0&&i<7) {
                Picasso.get().load(categoriesBeans.get(0).getSubcategories().get(i).getIcon_url()).placeholder(R.mipmap.img_load).fit().into(myViewHolder.img_singleP_rv_right);
                myViewHolder.tv_singleP_rv_right.setText(categoriesBeans.get(0).getSubcategories().get(i).getName());
            }
            else if (i>=7&&i<18)
            {
                Picasso.get().load(categoriesBeans.get(1).getSubcategories().get(i-7).getIcon_url()).placeholder(R.mipmap.img_load).fit().into(myViewHolder.img_singleP_rv_right);
                myViewHolder.tv_singleP_rv_right.setText(categoriesBeans.get(1).getSubcategories().get(i-7).getName());
            }
            else if(i>=18&&i<29)
            {
                Picasso.get().load(categoriesBeans.get(2).getSubcategories().get(i-18).getIcon_url()).placeholder(R.mipmap.img_load).fit().into(myViewHolder.img_singleP_rv_right);
                myViewHolder.tv_singleP_rv_right.setText(categoriesBeans.get(2).getSubcategories().get(i-18).getName());
            }
            else {
                Picasso.get().load(R.mipmap.img_load).fit().into(myViewHolder.img_singleP_rv_right);
                myViewHolder.tv_singleP_rv_right.setText("未赋值");
            }

//        Picasso.get().load(subcategoriesBeans.get(i).getIcon_url()).placeholder(R.mipmap.img_load).fit().into(myViewHolder.img_singleP_rv_right);
//        myViewHolder.tv_singleP_rv_right.setText(subcategoriesBeans.get(i).getName());
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (int i = 0; i <categoriesBeans.size(); i++) {
            count += categoriesBeans.get(i).getSubcategories().size();
        }
        return count;
//        return  subcategoriesBeans.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_singleP_rv_right;
        TextView tv_singleP_rv_right;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_singleP_rv_right = itemView.findViewById(R.id.tv_singleP_rv_right);
            img_singleP_rv_right = itemView.findViewById(R.id.img_singleP_rv_right);
        }
    }
}
