package com.zhenquan.doubanread.moudle.bookinfo;

import java.io.Serializable;

/**
 * Created by ry on 2018/1/10.
 */

public class BookInfo implements Serializable {
    private String title;
    private String author;
    private String image;
    private String jianjie;
    private String detailUrl;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }


    @Override
    public String toString() {
        return "RecommendBookInfo{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", image='" + image + '\'' +
                ", jianjie='" + jianjie + '\'' +
                '}';
    }
}
