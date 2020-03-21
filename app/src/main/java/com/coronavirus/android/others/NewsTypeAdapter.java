package com.coronavirus.android.others;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coronavirus.android.R;

import java.io.IOException;
import java.util.List;

/**
 *新闻类型的标题
 */
public class NewsTypeAdapter extends RecyclerView.Adapter<NewsTypeAdapter.ViewHolder> {

    private List<String> types;
    private OnItemClickListener onItemClickListener;

    public NewsTypeAdapter(List<String> types){
        this.types=types;
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public NewsTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.news_type_item,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsTypeAdapter.ViewHolder holder, final int position) {
        String typeName=types.get(position);
        holder.typeText.setText(typeName);
        if (position==0){
            holder.typeText.setTextSize(25);
            holder.typeText.setTextColor(Color.BLUE);
        }
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView typeText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            typeText=itemView.findViewById(R.id.type_text);
        }
    }
}
