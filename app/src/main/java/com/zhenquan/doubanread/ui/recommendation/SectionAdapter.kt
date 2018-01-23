package com.zhenquan.doubanread.ui.recommendation

import android.util.Log
import android.view.View
import android.widget.ImageView

import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.squareup.picasso.Picasso
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.moudle.bookinfo.AttentionBookInfo
import com.zhenquan.doubanread.moudle.bookinfo.BookInfo
import com.zhenquan.doubanread.moudle.bookinfo.CommentBookInfo
import com.zhenquan.doubanread.moudle.bookinfo.ElecBookInfo
import com.zhenquan.doubanread.moudle.bookinfo.InformationBookInfo
import com.zhenquan.doubanread.moudle.bookinfo.RecommendBookInfo
import com.zhenquan.doubanread.moudle.bookinfo.ShopBookInfo

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
class SectionAdapter
/**
 * Same as QuickAdapter#QuickAdapter(Context,int) but with
 * some initialization data.
 *
 * @param sectionHeadResId The section head layout id for each item
 * @param layoutResId      The layout resource id of each item.
 * @param data             A new list is created out of this one to avoid mutable list
 */
(layoutResId: Int, sectionHeadResId: Int, data: MutableList<BookInfoSection>) : BaseSectionQuickAdapter<BookInfoSection, BaseViewHolder>(layoutResId, sectionHeadResId, data) {


    override fun convert(helper: BaseViewHolder, item: BookInfoSection) {
        val t = item.t
        val iv_recommend_book_list = helper.itemView.findViewById<View>(R.id.iv_recommend_book_list) as ImageView
        Picasso.with(helper.getConvertView().context).load(t.image).into(iv_recommend_book_list)
        helper.setText(R.id.tv_recommend_book_name, t.title)
    }

    override fun convertHead(helper: BaseViewHolder, item: BookInfoSection) {
        helper.setText(R.id.header, item.header)
        helper.setVisible(R.id.more, item.isMore)
        helper.addOnClickListener(R.id.more)
    }


}
