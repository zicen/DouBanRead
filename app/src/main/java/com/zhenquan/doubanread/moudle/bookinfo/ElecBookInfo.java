package com.zhenquan.doubanread.moudle.bookinfo;

/**
 * Created by ry on 2018/1/10.
 */

public class ElecBookInfo extends BookInfo {
    private String price;
    private String year;
    private String publisher;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
