package com.patrick.linar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context context;
    private List<NewsItem> newslist;
    public NewsAdapter(List newslist){
        this.newslist=newslist;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardview;
        ImageView newsimg;
        TextView newstext;
        public ViewHolder(View view){
            super(view);
            cardview=(CardView)view;
            newsimg=(ImageView)view.findViewById(R.id.new_img);
            newstext=(TextView)view.findViewById(R.id.new_title);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null){
            context=viewGroup.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.news_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NewsItem news=this.newslist.get(i);
        viewHolder.newstext.setText(news.getTitle());
        Glide.with(context).load(news.getImgid()).into(viewHolder.newsimg);
    }

    @Override
    public int getItemCount() {
        return this.newslist.size();
    }
}
