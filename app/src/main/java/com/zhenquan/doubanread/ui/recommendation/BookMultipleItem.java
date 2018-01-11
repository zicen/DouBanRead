package com.zhenquan.doubanread.ui.recommendation;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zhenquan.doubanread.moudle.bookinfo.BookInfo;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class BookMultipleItem implements MultiItemEntity {
    public static final int HEAD_BOOK = 1;
    public static final int RECOMMEND_BOOK = 2;
    public static final int INFORMATION_BOOK = 3;
    public static final int ATTENTION_BOOK = 4;
    public static final int SHOP_BOOK = 5;
    public static final int ELEC_BOOK = 6;
    public static final int COMMENT_BOOK = 7;
    private BookInfo data;
    private int itemType;
    private int spanSize;
    private String content;
    private boolean hasMore;

    /**
     * 头条目用这个
     * @param itemType
     * @param spanSize
     * @param content
     */
    public BookMultipleItem(int itemType, int spanSize, String content,boolean hasMore) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.content = content;
        this.hasMore = hasMore;
    }


    /**
     * body item
     * @param itemType
     * @param spanSize
     * @param data
     */
    public BookMultipleItem(int itemType, int spanSize, BookInfo data) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.data = data;
    }

    public BookMultipleItem(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public BookInfo getData() {
        return data;
    }
    public boolean isHasMore() {
        return hasMore;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
