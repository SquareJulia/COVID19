package com.coronavirus.android.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coronavirus.android.R;
import com.coronavirus.android.fragment.RecommendNewsFragment;
import com.coronavirus.android.fragment.WebViewFragment;
import com.coronavirus.android.others.NewsTypeAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 展示新闻以及预测数据的activity
 * @author zjy
 */
public class NewsActivity extends AppCompatActivity {

    private List<String> types=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        try {
            initView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化视图
     * @throws IOException
     */
    private void initView() throws IOException {
        Toolbar toolbar=findViewById(R.id.news_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个返回图标
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();//返回
            }
        });
        toolbar.setTitle("新闻资讯");
        setTypes();
        initNewsTypeRecyclerView();
        replaceFragment(new RecommendNewsFragment());
    }

    /**
     * 初始化标题栏
     */
    private void initNewsTypeRecyclerView(){
        RecyclerView newsTypeRecyclerView=findViewById(R.id.titles);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        newsTypeRecyclerView.setLayoutManager(linearLayoutManager);
        NewsTypeAdapter newsTypeAdapter=new NewsTypeAdapter(types);
        //recyclerView的点击事件
        newsTypeAdapter.setOnItemClickListener(new NewsTypeAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                for (int i=0;i<types.size();i++){
                    if (i==position){
                        View view=linearLayoutManager.findViewByPosition(position);
                        assert view != null;
                        TextView textView=view.findViewById(R.id.type_text);
                        changeTextViewToClick(textView);
                        continue;
                    }
                    View view=linearLayoutManager.findViewByPosition(i);
                    TextView textView=view.findViewById(R.id.type_text);
                    textView.setTextColor(Color.BLACK);
                    textView.setTextSize(20);
                }
                switch (position){
                    case 0:
                        //推荐
                        replaceFragment(new RecommendNewsFragment());
                        break;
                    case 1:
                        //数据相关
                        replaceFragment(new WebViewFragment("http://map.dedsec.site/data_news.html"));
                        break;
                    case 2:
                        //民情相关
                        replaceFragment(new WebViewFragment("http://map.dedsec.site/citizen_news.html"));
                        break;
                    case 3:
                        //数据预测
                        replaceFragment(new WebViewFragment("http://map.dedsec.site/predict.html"));
                }
            }
        });
        newsTypeRecyclerView.setAdapter(newsTypeAdapter);
    }

    /**
     * 设置新闻类型
     */
    private void setTypes(){
        types.add("推荐");
        types.add("数据相关");
        types.add("民情相关");
        types.add("数据预测");
    }

    /**
     * 修改textView的大小和颜色
     * @param textView 要修改的textView
     */
    private void changeTextViewToClick(TextView textView){
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(25);
    }

    /**
     * 替换碎片
     * @param fragment 将要替换的碎片
     */
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.replace,fragment);
        transaction.commit();
    }
}
