package com.zhenquan.doubanread.ui.recommendation;

import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.zhenquan.doubanread.R;
import com.zhenquan.doubanread.moudle.bookinfo.AttentionBookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.BookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.CommentBookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.ElecBookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.InformationBookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.RecommendBookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.ShopBookInfo;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SectionAdapter extends BaseSectionQuickAdapter<BookInfoSection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SectionAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, BookInfoSection item) {
        BookInfo t = item.t;
        ImageView iv_recommend_book_list = (ImageView)helper.itemView.findViewById(R.id.iv_recommend_book_list);
        Picasso.with(helper.getConvertView().getContext()).load(t.getImage()).into(iv_recommend_book_list);
        helper.setText(R.id.tv_recommend_book_name, t.getTitle());
    }

    @Override
    protected void convertHead(BaseViewHolder helper, BookInfoSection item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
    }


}
