package com.coronavirus.android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coronavirus.android.R;
import com.coronavirus.android.activity.NewsActivity;
import com.coronavirus.android.activity.NewsContentActivity;
import com.coronavirus.android.data.News;
import com.coronavirus.android.others.NewsAdapter;
import com.coronavirus.android.others.NewsGet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 推荐界面的fragment
 */
public class RecommendNewsFragment extends Fragment {

    private List<News> newsList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.recommend_news_fragment,container,false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                NewsGet.setNews(newsList);
                Objects.requireNonNull(RecommendNewsFragment.this.getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView newsRecyclerView=view.findViewById(R.id.news);
                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(RecommendNewsFragment.this.getActivity());
                        newsRecyclerView.setLayoutManager(linearLayoutManager);
                        NewsAdapter newsAdapter=new NewsAdapter(newsList);
                        newsAdapter.setOnItemViewClickListener(new NewsAdapter.onItemViewClickListener() {
                            @Override
                            public void onClick(int position) {
                                News news = newsList.get(position);
                                String contentUrl=news.getContentUrl();
                                Intent intent = new Intent(RecommendNewsFragment.this.getActivity(), NewsContentActivity.class);
                                intent.putExtra("url", contentUrl);
                                startActivity(intent);
                            }
                        });
                        newsRecyclerView.setAdapter(newsAdapter);
                    }
                });
            }
        }).start();
        return view;
    }


}
