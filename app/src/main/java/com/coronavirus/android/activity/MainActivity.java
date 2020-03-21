package com.coronavirus.android.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.coronavirus.android.R;
import com.google.android.material.navigation.NavigationView;

/**
 * 应用的主活动
 * @author zsy
 * zjy修改
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private static String url="http://map.dedsec.site";
    public static Bitmap shotWeb;
    private WebView webView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            initView();
        }

        /**
         * 初始化视图及点击事件
         */
        @SuppressLint("SetJavaScriptEnabled")
        private void initView(){
            Toolbar toolbar= findViewById(R.id.toolbar);
            NavigationView navigationView=findViewById(R.id.nav_view);
            mDrawerLayout= findViewById(R.id.drawer_layout);

            setSupportActionBar(toolbar);
            ActionBar actionBar=getSupportActionBar();
            if(actionBar!=null){
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.nav_baseline);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                android.webkit.WebView.enableSlowWholeDocumentDraw();
            }
            webView=findViewById(R.id.web);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);
            webView.setInitialScale(90);

            navigationView.setNavigationItemSelectedListener(new NavigationView
                    .OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        //进入新闻界面
                        case R.id.nav_news:
                            Intent startNews=new Intent(MainActivity.this,NewsActivity.class);
                            startActivity(startNews);
                            break;
                        //进入反馈界面
                        case R.id.nav_message:
                            Intent startFeedBack=new Intent(MainActivity.this,FeedBack.class);
                            startActivity(startFeedBack);
                            break;
                        //进入孔明灯祈福界面
                        case R.id.nav_light:
                            Intent startLight=new Intent(MainActivity.this, LightMainActivity.class);
                            startActivity(startLight);
                            break;
                        default:
                    }
                    return true;
                }
            });
        }

        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.toolbar,menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()){
                case R.id.shot://展示长截图
                    shotWeb=captureLongWebViewLollipop(webView);//截图
                    Intent intent=new Intent(MainActivity.this,ShowActivity.class);
                    startActivity(intent);
                    break;
                case android.R.id.home:
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    break;
                default:
            }
            return true;
        }

        public Bitmap ShotWeb(WebView mWebView){
            mWebView.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            mWebView.layout(0, 0, mWebView.getMeasuredWidth(), mWebView.getMeasuredHeight());
            mWebView.setDrawingCacheEnabled(true);
            mWebView.buildDrawingCache();
            Bitmap longImage = Bitmap.createBitmap(mWebView.getMeasuredWidth(),
                    mWebView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(longImage);  // 画布的宽高和 WebView 的网页保持一致
            Paint paint = new Paint();
            canvas.drawBitmap(longImage, 0, mWebView.getMeasuredHeight(), paint);
            mWebView.draw(canvas);
        return longImage;
        }

    public static Bitmap captureLongWebViewLollipop(WebView webView){
        int height = webView.getContentHeight();
        int width = webView.getWidth();
        int pH = webView.getHeight();
        Bitmap bm = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bm);
        int top = height;
        while (top > 0) {
            if (top < pH) {
                top = 0;
            } else {
                top -= pH;
            }
            canvas.save();
            canvas.clipRect(0, top, width, top + pH);
            webView.scrollTo(0, top);
            webView.draw(canvas);
            canvas.restore();
        }
        return bm;
    }

}
