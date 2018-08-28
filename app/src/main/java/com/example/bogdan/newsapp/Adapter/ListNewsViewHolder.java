package com.example.bogdan.newsapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.bogdan.newsapp.Interface.ItemClickListener;
import com.example.bogdan.newsapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ItemClickListener itemClickListener;

    TextView article_title;
    //RelativeDateTimeFormatter article_time;
    CircleImageView article_image;

    public ListNewsViewHolder(View itemView) {
        super(itemView);

        article_image= (CircleImageView) itemView.findViewById(R.id.source_image);
        article_title= (TextView) itemView.findViewById(R.id.source_name);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
