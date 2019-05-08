package com.example.mysecondapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mysecondapp.R;
import com.example.mysecondapp.bean.CStrategyHeaderBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raindeavor丶W
 * Created on 2019/5/7
 */
public class StrategyHeaderRVAdapter extends RecyclerView.Adapter<StrategyHeaderRVAdapter.MyViewHolder> {

    List<CStrategyHeaderBean.DataBean.ColumnsBean> columnsBeans= new ArrayList<>();
    Context context;

    public  StrategyHeaderRVAdapter(Context context,List<CStrategyHeaderBean.DataBean.ColumnsBean> columnsBeans)
    {
        this.context=context;
        this.columnsBeans=columnsBeans;
    }
    @NonNull
    @Override
    public StrategyHeaderRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.strategy_rv_header_item,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StrategyHeaderRVAdapter.MyViewHolder myViewHolder, int i) {
        Picasso.get().load(columnsBeans.get(i).getCover_image_url()).placeholder(R.mipmap.img_load).fit().into(myViewHolder.img_strategy_header_item);
        myViewHolder.tv_strategy_header_item_title.setText(columnsBeans.get(i).getTitle());
        myViewHolder.tv_strategy_header_item_author.setText(columnsBeans.get(i).getAuthor());
    }

    @Override
    public int getItemCount() {
        return columnsBeans.size();
    }

    class  MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView img_strategy_header_item;
        TextView tv_strategy_header_item_title,tv_strategy_header_item_author;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_strategy_header_item=itemView.findViewById(R.id.img_strategy_header_item);
            tv_strategy_header_item_title =itemView.findViewById(R.id.tv_strategy_header_item_title);
            tv_strategy_header_item_author=itemView.findViewById(R.id.tv_strategy_header_item_author);
        }
    }
}
