package com.example.jerusalemguid.ui.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jerusalemguid.R;
import com.example.jerusalemguid.ui.models.Articale;

import java.util.List;

public class ArticaleAdapter extends RecyclerView.Adapter<ArticaleAdapter.ArticaleHolder> {
    private Context context ;
    private List<Articale> articaleList;
    public ArticaleAdapter(Context context , List<Articale> articales){
        this.context = context ;
        this.articaleList = articales;
    }

    @NonNull
    @Override
    public ArticaleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_posts_layout,parent,false);
        return new ArticaleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticaleHolder holder, int position) {
     Articale articale = articaleList.get(position);
        holder.description.setText(articale.getDescription());
        holder.title.setText(articale.getTitle());
        holder.publishedAt.setText(articale.getPublishedAt());
        holder.auther.setText(articale.getAuthor());
        Glide.with(context).load(articale.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return articaleList == null ? 0 : articaleList.size();
    }


    public class ArticaleHolder extends RecyclerView.ViewHolder{
        ImageView imageView ;
        TextView auther ,  title , description , publishedAt  ;
        public ArticaleHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title_tv);
            description = itemView.findViewById(R.id.desc_tv);
            publishedAt = itemView.findViewById(R.id.publish_tv);
            auther = itemView.findViewById(R.id.auther_tv);

        }
    }
}
