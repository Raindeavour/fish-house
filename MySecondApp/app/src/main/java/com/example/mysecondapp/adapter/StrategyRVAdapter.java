package com.example.mysecondapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.donkingliang.headerviewadapter.adapter.HeaderViewAdapter;
import com.example.mysecondapp.R;
import com.example.mysecondapp.bean.CStrategyHeaderBean;
import com.example.mysecondapp.bean.CStrategyItemBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raindeavor丶W
 * Created on 2019/5/7
 */
public class StrategyRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<CStrategyItemBean.DataBean.ChannelGroupsBean> channelGroupsBeans = new ArrayList<>();
    Context context;

    List<CStrategyHeaderBean.DataBean.ColumnsBean> columnsBeans= new ArrayList<>();

    public StrategyRVAdapter(Context context, List<CStrategyItemBean.DataBean.ChannelGroupsBean> channelGroupsBeans,List<CStrategyHeaderBean.DataBean.ColumnsBean> columnsBeans) {
        this.context = context;
        this.channelGroupsBeans = channelGroupsBeans;
        this.columnsBeans=columnsBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (viewType == 1) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.classification_strategy_rv_header, viewGroup, false);
            holder = new HeaderViewHolder(view);
        } else if (viewType == 2) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.classification_strategy_rv_item, viewGroup, false);
            holder = new MyViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof HeaderViewHolder) {
            if (i == 0) {
                StrategyHeaderRVAdapter strategyHeaderRVAdapter=new StrategyHeaderRVAdapter(context,columnsBeans);
                GridLayoutManager layoutManager = new GridLayoutManager(context,3);
                layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
                ((HeaderViewHolder)holder).strategyHeaderRV.setLayoutManager(layoutManager);
                ((HeaderViewHolder)holder).strategyHeaderRV.setHasFixedSize(false);
                ((HeaderViewHolder)holder).strategyHeaderRV.setAdapter(strategyHeaderRVAdapter);
            }
        } else if (holder instanceof MyViewHolder) {
            i=i-1;
            ((MyViewHolder)holder).tv_strategy_name.setText(channelGroupsBeans.get(i).getName());
            Picasso.get().load(channelGroupsBeans.get(i).getChannels().get(0).getCover_image_url()).placeholder(R.mipmap.img_load).fit().into(((MyViewHolder)holder).img_cv_one);
            Picasso.get().load(channelGroupsBeans.get(i).getChannels().get(1).getCover_image_url()).placeholder(R.mipmap.img_load).fit().into(((MyViewHolder)holder).img_cv_two);
            Picasso.get().load(channelGroupsBeans.get(i).getChannels().get(2).getCover_image_url()).placeholder(R.mipmap.img_load).fit().into(((MyViewHolder)holder).img_cv_three);
            Picasso.get().load(channelGroupsBeans.get(i).getChannels().get(3).getCover_image_url()).placeholder(R.mipmap.img_load).fit().into(((MyViewHolder)holder).img_cv_four);
            Picasso.get().load(channelGroupsBeans.get(i).getChannels().get(4).getCover_image_url()).placeholder(R.mipmap.img_load).fit().into(((MyViewHolder)holder).img_cv_five);
            Picasso.get().load(channelGroupsBeans.get(i).getChannels().get(5).getCover_image_url()).placeholder(R.mipmap.img_load).fit().into(((MyViewHolder)holder).img_cv_six);
        }


    }

    @Override
    public int getItemCount() {
        return (channelGroupsBeans.size()+1);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_strategy_name;
        ImageView img_cv_one, img_cv_two, img_cv_three, img_cv_four, img_cv_five, img_cv_six;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_strategy_name =(TextView) itemView.findViewById(R.id.tv_strategy_name);
            img_cv_one =(ImageView) itemView.findViewById(R.id.img_cv_one);
            img_cv_two = itemView.findViewById(R.id.img_cv_two);
            img_cv_three = itemView.findViewById(R.id.img_cv_three);
            img_cv_four = itemView.findViewById(R.id.img_cv_four);
            img_cv_five = itemView.findViewById(R.id.img_cv_five);
            img_cv_six = itemView.findViewById(R.id.img_cv_six);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        RecyclerView strategyHeaderRV;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            strategyHeaderRV = itemView.findViewById(R.id.rv_strategy_header);
        }
    }

    public int getItemViewType(int position) {
        int flag = 0;
        if (position == 0) {
            flag = 1;
        } else {
            flag = 2;
        }
        return flag;
    }
}

