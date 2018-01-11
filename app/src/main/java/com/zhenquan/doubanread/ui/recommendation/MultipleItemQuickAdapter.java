package com.zhenquan.doubanread.ui.recommendation;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.zhenquan.doubanread.R;
import com.zhenquan.doubanread.moudle.bookinfo.AttentionBookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.CommentBookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.ElecBookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.InformationBookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.RecommendBookInfo;
import com.zhenquan.doubanread.moudle.bookinfo.ShopBookInfo;
import com.zhenquan.doubanread.ui.activity.WebViewActivity;
import com.zhenquan.doubanread.ui.classfiy.BookDetailActivity;

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
    protected void convert(BaseViewHolder helper, final BookMultipleItem item) {
        switch (helper.getItemViewType()) {
            case BookMultipleItem.HEAD_BOOK:
                helper.setText(R.id.header, item.getContent());
                if (item.isHasMore()) {
                    helper.getView(R.id.more).setVisibility(View.VISIBLE);
                    helper.getView(R.id.more).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //todo  go a web
                            switch (item.getContent()) {
                                case "新书速递":
                                    //https://book.douban.com/latest?icn=index-latestbook-all
                                    Intent intent = new Intent(mContext, WebViewActivity.class);
                                    intent.putExtra("title", "新书速递");
                                    intent.putExtra("detailUrl", "https://book.douban.com/latest?icn=index-latestbook-all");
                                    mContext.startActivity(intent);
                                    break;

                            }
                        }
                    });
                }else {
                    helper.getView(R.id.more).setVisibility(View.GONE);
                }
                break;
            case BookMultipleItem.RECOMMEND_BOOK:
                final RecommendBookInfo bookInfo = (RecommendBookInfo) item.getData();
                ImageView iv_recommend_book_list = (ImageView) helper.itemView.findViewById(R.id.iv_recommend_book_list);
                Picasso.with(helper.getConvertView().getContext()).load(bookInfo.getImage()).into(iv_recommend_book_list);
                helper.setText(R.id.tv_recommend_book_name, bookInfo.getTitle());
                helper.setText(R.id.tv_recommend_book_author, bookInfo.getAuthor());
                helper.convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, BookDetailActivity.class);
                        intent.putExtra("title", bookInfo.getTitle());
                        intent.putExtra("id", bookInfo.getId());
                        mContext.startActivity(intent);
                    }
                });
                break;
            case BookMultipleItem.ATTENTION_BOOK:
                final AttentionBookInfo bookInfo_attention = (AttentionBookInfo) item.getData();
                ImageView iv_attention_book_list = (ImageView) helper.itemView.findViewById(R.id.iv_attention_book_list);
                Picasso.with(helper.getConvertView().getContext()).load(bookInfo_attention.getImage()).into(iv_attention_book_list);
                helper.setText(R.id.tv_attention_book_name, bookInfo_attention.getTitle());
                helper.setText(R.id.tv_attention_book_author, bookInfo_attention.getAuthor());
                helper.setText(R.id.tv_attention_book_tags, bookInfo_attention.getTag());
                helper.setText(R.id.tv_attention_book_jianjie, bookInfo_attention.getJianjie());
                helper.setText(R.id.tv_attention_book_star, bookInfo_attention.getStar());
                helper.convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, BookDetailActivity.class);
                        intent.putExtra("title", bookInfo_attention.getTitle());
                        intent.putExtra("id", bookInfo_attention.getId());
                        mContext.startActivity(intent);
                    }
                });
                break;
            case BookMultipleItem.COMMENT_BOOK:
                final CommentBookInfo bookInfo_recommmend = (CommentBookInfo) item.getData();
                ImageView iv_comment_book_list = (ImageView) helper.itemView.findViewById(R.id.iv_comment_book_list);
                Picasso.with(helper.getConvertView().getContext()).load(bookInfo_recommmend.getImage()).into(iv_comment_book_list);
                helper.setText(R.id.tv_comment_book_name, bookInfo_recommmend.getTitle());
                helper.setText(R.id.tv_attention_book_author, bookInfo_recommmend.getAuthor()+" 评论"+bookInfo_recommmend.getReview_bookname()+bookInfo_recommmend.getStar());
                helper.setText(R.id.tv_attention_book_review_content, bookInfo_recommmend.getReview_content());
                helper.convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);
                        intent.putExtra("title", bookInfo_recommmend.getTitle());
                        intent.putExtra("detailUrl", bookInfo_recommmend.getDetailUrl());
                        mContext.startActivity(intent);
                    }
                });
                break;
            case BookMultipleItem.ELEC_BOOK:
                final ElecBookInfo bookInfo_elec = (ElecBookInfo) item.getData();
                ImageView iv_elec_book_list = (ImageView) helper.itemView.findViewById(R.id.iv_elec_book_list);
                Picasso.with(helper.getConvertView().getContext()).load(bookInfo_elec.getImage()).into(iv_elec_book_list);
                helper.setText(R.id.tv_elec_book_name, bookInfo_elec.getTitle());
                helper.setText(R.id.tv_elec_book_price, bookInfo_elec.getPrice());
                helper.convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);
                        intent.putExtra("title", bookInfo_elec.getTitle());
                        intent.putExtra("detailUrl", bookInfo_elec.getDetailUrl());
                        mContext.startActivity(intent);
                    }
                });
                break;
            case BookMultipleItem.INFORMATION_BOOK:
                final InformationBookInfo bookInfo_infomation = (InformationBookInfo) item.getData();
                ImageView iv_infotmation_book_list = (ImageView) helper.itemView.findViewById(R.id.iv_infotmation_book_list);
                Picasso.with(helper.getConvertView().getContext()).load(bookInfo_infomation.getImage()).into(iv_infotmation_book_list);
                helper.setText(R.id.tv_infotmation_book_name, bookInfo_infomation.getTitle());
                helper.setText(R.id.tv_infotmation_book_author, bookInfo_infomation.getAuthor());
                helper.setText(R.id.tv_infotmation_book_jianjie, bookInfo_infomation.getJianjie());
                helper.convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);
                        intent.putExtra("title", bookInfo_infomation.getTitle());
                        intent.putExtra("detailUrl", bookInfo_infomation.getDetailUrl());
                        mContext.startActivity(intent);
                    }
                });
                break;

            case BookMultipleItem.SHOP_BOOK:
                final ShopBookInfo bookInfo_shop = (ShopBookInfo) item.getData();
                ImageView iv_shop_book_list = (ImageView) helper.itemView.findViewById(R.id.iv_shop_book_list);
                Picasso.with(helper.getConvertView().getContext()).load(bookInfo_shop.getImage()).into(iv_shop_book_list);
                helper.setText(R.id.tv_shop_book_name, bookInfo_shop.getTitle());
                helper.setText(R.id.tv_shop_book_price, bookInfo_shop.getPrice());
                helper.convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);
                        intent.putExtra("title", bookInfo_shop.getTitle());
                        intent.putExtra("detailUrl", bookInfo_shop.getDetailUrl());
                        mContext.startActivity(intent);
                    }
                });
                break;


        }
    }

}
