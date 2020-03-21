package com.coronavirus.android.data;

import android.graphics.Bitmap;

import java.io.Serializable;

public class News implements Serializable {
    private String title;
    private Bitmap bitmap;
    private String imageUrl;
    private String contentUrl;
    private String newsContent;

    public News(String title,Bitmap bitmap){
        this.title=title;
        this.bitmap=bitmap;
    }

    public News(String title,String imageUrl){
        this.title=title;
        this.imageUrl = imageUrl;
    }

    public News(){}


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
}
