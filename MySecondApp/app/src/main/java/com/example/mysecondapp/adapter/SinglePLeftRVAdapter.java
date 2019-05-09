package com.example.mysecondapp.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mysecondapp.R;
import com.example.mysecondapp.bean.CSingleProductBean;
import com.example.mysecondapp.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raindeavor丶W
 * Created on 2019/5/8
 */
public class SinglePLeftRVAdapter extends RecyclerView.Adapter<SinglePLeftRVAdapter.MyViewHolder> {

    List<CSingleProductBean.DataBean.CategoriesBean> categoriesBeans = new ArrayList<>();
    private int mSelectionPosition;
    private OnItemClickListener onItemClickListener;

    public SinglePLeftRVAdapter(List<CSingleProductBean.DataBean.CategoriesBean> categoriesBeans) {
        this.categoriesBeans = categoriesBeans;
    }

    @NonNull
    @Override
    public SinglePLeftRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleproduct_rv_left_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SinglePLeftRVAdapter.MyViewHolder myViewHolder, final int i) {
        //onBindeViewHolder方法的调用时机是item出现（或将要出现）在屏幕上时，这时需要向传入的viewHolder中填充数据等操作
        myViewHolder.tv_singleP_rv_left.setText(categoriesBeans.get(i).getName());
        if (onItemClickListener != null) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
   //                 setSelectionPosition(i);
                    notifyDataSetChanged();
                    mSelectionPosition = i;
                    onItemClickListener.OnItemClick(myViewHolder.itemView, i);
                }
            });
        }
        //点击后item与点击前的不同 或 滑动、滚动后item与事件前的item不同
        if (mSelectionPosition != i) {
            myViewHolder.tv_singleP_rv_left.setTextColor(Color.rgb(0, 0, 0));
            myViewHolder.v_sp_left_item.setVisibility(View.INVISIBLE);
        } else {
            myViewHolder.tv_singleP_rv_left.setTextColor(Color.RED);
            myViewHolder.v_sp_left_item.setVisibility(View.VISIBLE);
            myViewHolder.v_sp_left_item.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return categoriesBeans.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_singleP_rv_left;
        View v_sp_left_item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_singleP_rv_left = itemView.findViewById(R.id.tv_singleP_rv_left);
            v_sp_left_item = itemView.findViewById(R.id.v_sp_left_item);
        }
    }

    public void setSelectionPosition(int position) {
        mSelectionPosition = position;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
