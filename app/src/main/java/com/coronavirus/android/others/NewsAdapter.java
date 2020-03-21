package com.coronavirus.android.others;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coronavirus.android.R;
import com.coronavirus.android.data.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> newsList;
    private onItemViewClickListener onItemViewClickListener;

    public NewsAdapter(List<News> newsList){
        this.newsList=newsList;
    }

    public void setOnItemViewClickListener(onItemViewClickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
    }

    public interface onItemViewClickListener{
        void onClick(int position);
    }
    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.ViewHolder holder, final int position) {
        final News news=newsList.get(position);
        holder.newsTitle.setText(news.getTitle());
        holder.newsImage.setImageBitmap(news.getBitmap());
        if (onItemViewClickListener !=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemViewClickListener.onClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView newsImage;
        TextView newsTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImage=itemView.findViewById(R.id.news_picture);
            newsTitle=itemView.findViewById(R.id.news_title);
        }
    }
}
