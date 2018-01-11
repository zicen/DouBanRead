package com.zhenquan.doubanread.moudle.bookinfo;

/**
 * Created by ry on 2018/1/10.
 */

public class AttentionBookInfo extends BookInfo {
    private String tag;
    private String star;

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
