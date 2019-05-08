package com.example.mysecondapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mysecondapp.R;
import com.example.mysecondapp.bean.ListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CordialXRVAdapter extends XRecyclerView.Adapter<CordialXRVAdapter.MyViewHolder> {

    List<String> data = new ArrayList<String>();
    List<ListBean.DataBean.ItemsBean> items = new ArrayList<>();

    public CordialXRVAdapter(List<ListBean.DataBean.ItemsBean> items, List<String> data) {
        this.items = items;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_cordial_xrv_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_cordial.setText(items.get(i).getName());
        myViewHolder.tv_cordial_price.setText(items.get(i).getPrice());
        Picasso.get().load(items.get(i).getImage_urls().get(0)).placeholder(R.mipmap.img_load).fit().into(myViewHolder.img_cordial);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cordial, tv_cordial_price;
        ImageView img_cordial;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_cordial = itemView.findViewById(R.id.tv_cordial);
            tv_cordial_price = itemView.findViewById(R.id.tv_cordial_price);
            img_cordial = itemView.findViewById(R.id.img_cordial);
        }
    }
}