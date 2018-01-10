package com.zhenquan.doubanread.moudle.bookinfo;

/**
 * Created by ry on 2018/1/10.
 */

public class CommentBookInfo extends BookInfo {
    private String review_bookname;
    private String review_content;
    private String star;

    public String getReview_bookname() {
        return review_bookname;
    }

    public void setReview_bookname(String review_bookname) {
        this.review_bookname = review_bookname;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

}
