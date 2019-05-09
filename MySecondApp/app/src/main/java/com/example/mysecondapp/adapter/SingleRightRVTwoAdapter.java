package com.example.mysecondapp.adapter;

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
 * Created on 2019/5/9
 */
public class SingleRightRVTwoAdapter extends RecyclerView.Adapter<SingleRightRVTwoAdapter.MyViewHolder> {
    List<CSingleProductBean.DataBean.CategoriesBean.SubcategoriesBean> subcategoriesBeans=new ArrayList<>();

    public SingleRightRVTwoAdapter(List<CSingleProductBean.DataBean.CategoriesBean.SubcategoriesBean> subcategoriesBeans)
    {
        this.subcategoriesBeans=subcategoriesBeans;
    }
    @NonNull
    @Override
    public SingleRightRVTwoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleproduct_rv_right_item_two, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SingleRightRVTwoAdapter.MyViewHolder myViewHolder, int i) {
        Picasso.get().load(subcategoriesBeans.get(i).getIcon_url()).placeholder(R.mipmap.img_load).fit().into(myViewHolder.img_singleP_rv_right_two);
        myViewHolder.tv_singleP_rv_right_two.setText(subcategoriesBeans.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return subcategoriesBeans.size();
    }

    class  MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_singleP_rv_right_two;
        TextView tv_singleP_rv_right_two;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_singleP_rv_right_two=itemView.findViewById(R.id.img_singleP_rv_right_two);
            tv_singleP_rv_right_two =itemView.findViewById(R.id.tv_singleP_rv_right_two);
        }
    }
}
