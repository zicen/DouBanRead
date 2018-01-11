package com.zhenquan.doubanread.ui.recommendation;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.zhenquan.doubanread.R;
import com.zhenquan.doubanread.moudle.bookinfo.AttentionBookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.CommentBookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.ElecBookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.InformationBookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.RecommendBookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.ShopBookInfo;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 * modify by AllenCoder
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<BookMultipleItem, BaseViewHolder> {

    public MultipleItemQuickAdapter(Context context, List data) {
        super(data);
        addItemType(BookMultipleItem.HEAD_BOOK, R.layout.def_section_head);
        addItemType(BookMultipleItem.ATTENTION_BOOK, R.layout.item_attention_book_list);
        addItemType(BookMultipleItem.COMMENT_BOOK, R.layout.item_comment_book_list);
        addItemType(BookMultipleItem.ELEC_BOOK, R.layout.item_elec_book_list);
        addItemType(BookMultipleItem.INFORMATION_BOOK, R.layout.item_information_book_list);
        addItemType(BookMultipleItem.RECOMMEND_BOOK, R.layout.item_recommend_book_list);
        addItemType(BookMultipleItem.SHOP_BOOK, R.layout.item_shop_book_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookMultipleItem item) {
        switch (helper.getItemViewType()) {
            case BookMultipleItem.HEAD_BOOK:
                helper.setText(R.id.header, item.getContent());
                if (item.isHasMore()) {
                    helper.getView(R.id.more).setVisibility(View.VISIBLE);
                }else {
                    helper.getView(R.id.more).setVisibility(View.GONE);
                }
                break;
            case BookMultipleItem.RECOMMEND_BOOK:
                RecommendBookInfo bookInfo = (RecommendBookInfo) item.getData();
                ImageView iv_recommend_book_list = (ImageView) helper.itemView.findViewById(R.id.iv_recommend_book_list);
                Picasso.with(helper.getConvertView().getContext()).load(bookInfo.getImage()).into(iv_recommend_book_list);
                helper.setText(R.id.tv_recommend_book_name, bookInfo.getTitle());
                helper.setText(R.id.tv_recommend_book_author, bookInfo.getTitle());
                break;
            case BookMultipleItem.ATTENTION_BOOK:
                AttentionBookInfo bookInfo_attention = (AttentionBookInfo) item.getData();
                ImageView iv_attention_book_list = (ImageView) helper.itemView.findViewById(R.id.iv_attention_book_list);
                Picasso.with(helper.getConvertView().getContext()).load(bookInfo_attention.getImage()).into(iv_attention_book_list);
                helper.setText(R.id.tv_attention_book_name, bookInfo_attention.getTitle());
                helper.setText(R.id.tv_attention_book_author, bookInfo_attention.getAuthor());
                helper.setText(R.id.tv_attention_book_tags, bookInfo_attention.getTag());
                helper.setText(R.id.tv_attention_book_jianjie, bookInfo_attention.getJianjie());
                helper.setText(R.id.tv_attention_book_star, bookInfo_attention.getStar());
                break;
            case BookMultipleItem.COMMENT_BOOK:
                CommentBookInfo bookInfo_recommmend = (CommentBookInfo) item.getData();
                ImageView iv_comment_book_list = (ImageView) helper.itemView.findViewById(R.id.iv_comment_book_list);
                Picasso.with(helper.getConvertView().getContext()).load(bookInfo_recommmend.getImage()).into(iv_comment_book_list);
                helper.setText(R.id.tv_comment_book_name, bookInfo_recommmend.getTitle());
                helper.setText(R.id.tv_attention_book_author, bookInfo_recommmend.getAuthor()+" 评论"+bookInfo_recommmend.getReview_bookname()+bookInfo_recommmend.getStar());
                helper.setText(R.id.tv_attention_book_review_content, bookInfo_recommmend.getReview_content());
                break;
            case BookMultipleItem.ELEC_BOOK:
                ElecBookInfo bookInfo_elec = (ElecBookInfo) item.getData();
                ImageView iv_elec_book_list = (ImageView) helper.itemView.findViewById(R.id.iv_elec_book_list);
                Picasso.with(helper.getConvertView().getContext()).load(bookInfo_elec.getImage()).into(iv_elec_book_list);
                helper.setText(R.id.tv_elec_book_name, bookInfo_elec.getTitle());
                helper.setText(R.id.tv_elec_book_price, bookInfo_elec.getPrice());
                break;
            case BookMultipleItem.INFORMATION_BOOK:
                InformationBookInfo bookInfo_infomation = (InformationBookInfo) item.getData();
                ImageView iv_infotmation_book_list = (ImageView) helper.itemView.findViewById(R.id.iv_infotmation_book_list);
                Picasso.with(helper.getConvertView().getContext()).load(bookInfo_infomation.getImage()).into(iv_infotmation_book_list);
                helper.setText(R.id.tv_infotmation_book_name, bookInfo_infomation.getTitle());
                helper.setText(R.id.tv_infotmation_book_jianjie, bookInfo_infomation.getJianjie());
                break;

            case BookMultipleItem.SHOP_BOOK:
                ShopBookInfo bookInfo_shop = (ShopBookInfo) item.getData();
                ImageView iv_shop_book_list = (ImageView) helper.itemView.findViewById(R.id.iv_shop_book_list);
                Picasso.with(helper.getConvertView().getContext()).load(bookInfo_shop.getImage()).into(iv_shop_book_list);
                helper.setText(R.id.tv_shop_book_name, bookInfo_shop.getTitle());
                helper.setText(R.id.tv_shop_book_price, bookInfo_shop.getPrice());
                break;


        }
    }

}
