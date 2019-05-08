package com.example.mysecondapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysecondapp.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChoiceXRVAdapter extends XRecyclerView.Adapter<ChoiceXRVAdapter.MyViewHolder> {

    List<String> data = new ArrayList<String>();
    List<String> introduction = new ArrayList<>();
    List<String> cover_image_url = new ArrayList<>();
    List<String> title = new ArrayList<>();
    List<String> column_title = new ArrayList<>();
    List<Integer> likes_count = new ArrayList<>();
    List<Boolean> liked = new ArrayList<>();
    Context context;

    public ChoiceXRVAdapter(Context context, List<String> cover_image_url, List<String> title, List<String> column_title, List<String> introduction, List<Integer> likes_count, List<Boolean> liked) {
        this.context = context;
        this.cover_image_url = cover_image_url;
        this.title = title;
        this.column_title = column_title;
        this.introduction = introduction;
        this.likes_count = likes_count;
        this.liked = liked;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.homepage_choice_xrv_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        final String url;
        int count;
        myViewHolder.tv_title.setText(title.get(i));
        myViewHolder.tv_introduction.setText(introduction.get(i));
        myViewHolder.tv_column_title.setText(column_title.get(i));
        myViewHolder.tv_count.setText(likes_count.get(i) + "");
        if (liked.get(i))
        {
            myViewHolder.img_like.setSelected(true);
            myViewHolder.tv_count.setText((Integer.parseInt(myViewHolder.tv_count.getText().toString()) + 1) + "");
        }
        else
        {
            myViewHolder.img_like.setSelected(false);
        }
            myViewHolder.img_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (liked.get(i)) {
                        liked.set(i, false);
                        myViewHolder.tv_count.setText((Integer.parseInt(myViewHolder.tv_count.getText().toString()) - 1) + "");
                        myViewHolder.img_like.setSelected(Boolean.FALSE);
                        Toast.makeText(context, "取消收藏！", Toast.LENGTH_SHORT).show();
                    } else {
                        liked.set(i, true);
                        myViewHolder.tv_count.setText((Integer.parseInt(myViewHolder.tv_count.getText().toString()) + 1) + "");
                        myViewHolder.img_like.setSelected(Boolean.TRUE);
                        Toast.makeText(context, "收藏成功！", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        url = cover_image_url.get(i);
        Picasso.get().load(url).placeholder(R.mipmap.img_load).into(myViewHolder.img_main);
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_introduction, tv_column_title, tv_count;
        ImageView img_main, img_like;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_introduction = itemView.findViewById(R.id.tv_introduction);
            tv_column_title = itemView.findViewById(R.id.tv_column_title);
            tv_count = itemView.findViewById(R.id.tv_count);
            img_main = itemView.findViewById(R.id.img_main);
            img_like = itemView.findViewById(R.id.img_like);
        }
    }
}