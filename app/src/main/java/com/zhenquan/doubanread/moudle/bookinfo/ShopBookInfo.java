package com.zhenquan.doubanread.moudle.bookinfo;

/**
 * Created by ry on 2018/1/10.
 */

public class ShopBookInfo extends BookInfo {
    private String price;
    private String free_delivery;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFree_delivery() {
        return free_delivery;
    }

    public void setFree_delivery(String free_delivery) {
        this.free_delivery = free_delivery;
    }
}
