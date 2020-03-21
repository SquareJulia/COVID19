package com.coronavirus.android.others;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.coronavirus.android.data.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NewsGet {

    /**
     * 爬取新闻标题以及图片
     * @param news 将要装入的list
     */
    public static void setNews(final List<News> news) {
        try{
            String url="https://www.leiphone.com/tag/疫情";
            Document document= connectToURL(url);
            Elements titleElements=document.select("div.list>ul.clr>li>div.box>div.word>h3>a");
            Elements imageElements=document.select("div.list>ul.clr>li>div.box>div.img>a[href]>img");
            Elements contentElements=document.select("div.list>ul.clr>li>div.box>div.img>a[href]");
            for (int i=0;i<titleElements.size();i++){
                String title=titleElements.get(i).text();;
                News newsToAdd=new News();
                newsToAdd.setTitle(title);
                news.add(newsToAdd);
            }
            for (int i=0;i<imageElements.size();i++){
                String imageUrl=imageElements.get(i).attr("data-original");
                news.get(i).setImageUrl(imageUrl);
            }
            int temp=0;
            for (int i=1;i<contentElements.size();i+=2){
                String contentUrl=contentElements.get(i).attr("href");
                news.get(temp).setContentUrl(contentUrl);
                temp++;
                if (temp>9)
                    break;
            }
            for (News tempNews:news){
                URL url1=new URL(tempNews.getImageUrl());
                Bitmap bitmap=BitmapFactory.decodeStream(url1.openStream());
                tempNews.setBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过url连接到jsoup
     * @param url url
     * @return
     * @throws IOException
     */
    private static Document connectToURL(String url) throws IOException {
        Document document=Jsoup.connect(url).get();
        return document;
    }


    private static String getNewsContent(String u) throws IOException {
        Document document=connectToURL(u);
        Elements contentEle=document.select("div.lph-article-comView>p");
        StringBuilder sb=new StringBuilder();
        for (Element e:contentEle){
            sb.append(e.text());
        }
        return sb.toString();
    }



//    /**
//     * 获取图片
//     * @param url url
//     * @return bitmap
//     */
//    public static Bitmap getHttpBitmap(String url){
//        URL myFileUrl=null;
//        Bitmap bitmap=null;
//        try {
//            myFileUrl=new URL(url);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        try {
//            HttpURLConnection connection= (HttpURLConnection) myFileUrl.openConnection();
//            connection.setConnectTimeout(3000);
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream is=connection.getInputStream();
//            bitmap= BitmapFactory.decodeStream(is);
//            is.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return bitmap;
//    }
}
