package com.newsworld;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CustomViewHolder> {
    private Context context;
    private List<HeadLine> headline;

    public NewsAdapter(Context context, List<HeadLine> headline) {
        this.context = context;
        this.headline = headline;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.rownews,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textViewTile.setText(headline.get(position).getTitle());
        holder.textViewSource.setText(headline.get(position).getSource().getName());
        holder.Description.setText(headline.get(position).getDescription());
        holder.Publish.setText(headline.get(position).getPublishedAt());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,FullNewsActivity.class);
                intent.putExtra("Title",headline.get(position).getTitle());
                intent.putExtra("Description",headline.get(position).getDescription());
                intent.putExtra("Image",headline.get(position).getUrlToImage());
                intent.putExtra("Content",headline.get(position).getContent());
                intent.putExtra("Time",headline.get(position).getPublishedAt());
                intent.putExtra("Channel",headline.get(position).getSource().getName());
                intent.putExtra("Url",headline.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        if (headline.get(position).getUrlToImage() != null) {
            Glide.with(context).load(headline.get(position).getUrlToImage()).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return headline.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTile, textViewSource, Description, Publish;
        ImageView imageView;
        CardView cardView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTile = itemView.findViewById(R.id.titletxt);
            Description = itemView.findViewById(R.id.Description);
            textViewSource = itemView.findViewById(R.id.Source);
            cardView = itemView.findViewById(R.id.cardview);
            imageView = itemView.findViewById(R.id.imgHeadline);
            Publish = itemView.findViewById(R.id.PublishedAt);
        }
    }
}
