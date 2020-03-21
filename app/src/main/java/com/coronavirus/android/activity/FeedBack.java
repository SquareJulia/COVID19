package com.coronavirus.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toolbar;

import com.coronavirus.android.R;

public class FeedBack extends AppCompatActivity {

    private WebView feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initView();
        initWebView();
    }

    public void initView(){
        androidx.appcompat.widget.Toolbar toolbar=(androidx.appcompat.widget.Toolbar)findViewById(R.id.feedback_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个返回图标
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();//返回
            }
        });
        toolbar.setTitle("意见反馈");
    }

    public void initWebView(){
        feedback=(WebView)findViewById(R.id.feedback);
        feedback.getSettings().setJavaScriptEnabled(true);
        feedback.setWebViewClient(new WebViewClient());
        feedback.setInitialScale(100);
        feedback.loadUrl("http://map.dedsec.site:3000/");
    }
}
