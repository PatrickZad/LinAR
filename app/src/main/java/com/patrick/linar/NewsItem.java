package com.patrick.linar;

public class NewsItem {
    private String title;
    private int imgid;
    public NewsItem(String title, int imgid){
        this.title=title;
        this.imgid=imgid;
    }
    public String getTitle(){
        return this.title;
    }
    public int getImgid(){
        return this.imgid;
    }
}
