package com.zhenquan.doubanread.ui.recommendation;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.zhenquan.doubanread.moudle.bookinfo.BookInfo;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class BookInfoSection extends SectionEntity<BookInfo> {
    private boolean isMore;
    public BookInfoSection(boolean isHeader, String header, boolean isMroe) {
        super(isHeader, header);
        this.isMore = isMroe;
    }

    public BookInfoSection(BookInfo t) {
        super(t);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean mroe) {
        isMore = mroe;
    }
}
