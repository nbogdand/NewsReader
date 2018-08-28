package com.example.bogdan.newsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bogdan.newsapp.DetailArticle;
import com.example.bogdan.newsapp.Interface.ItemClickListener;
import com.example.bogdan.newsapp.Model.Article;
import com.example.bogdan.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsViewHolder>{

    List<Article> articleList = new ArrayList<>();
    Context context;

    public ListNewsAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.source_layout,parent,false);
        return new ListNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListNewsViewHolder holder, int position) {
        Picasso.with(context)
                .load(articleList.get(position).getUrlToImage())
                .into(holder.article_image);

        if(articleList.get(position).getTitle().length() > 65)
            holder.article_title.setText(articleList.get(position).getTitle().substring(0,65)+"...");
        else
            holder.article_title.setText(articleList.get(position).getTitle());



        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, DetailArticle.class);
                intent.putExtra("webUrl",articleList.get(position).getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

}
