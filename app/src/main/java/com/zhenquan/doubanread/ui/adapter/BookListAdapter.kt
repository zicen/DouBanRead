package com.zhenquan.doubanread.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import com.squareup.picasso.Picasso
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.adapter.BaseRecyclerAdapter
import com.zhenquan.doubanread.base.adapter.SmartViewHolder
import com.zhenquan.doubanread.moudle.Book
import com.zhenquan.doubanread.ui.classfiy.BookDetailActivity

/**
 * Created by ry on 2018/2/24.
 */
//class BookListAdapter(context: Context): BaseRecyclerAdapter<Book>(R.layout.item_book_list) {
//    override fun onBindViewHolder(holder: SmartViewHolder, model: Book, position: Int) {
//        holder.text(R.id.tv_book_name, model.title)
//        holder.text(R.id.tv_book_author, if(model.author.isNotEmpty()){model.author[0]}else{""})
//        holder.text(R.id.tv_book_comment, model.rating.average)
//        holder.text(R.id.tv_book_jianjie, model.summary)
//        val image = holder.image(R.id.iv_book_list)
//        Picasso.with(holder.itemView.context).load(model.image).into(image)
//        holder.itemView.setOnClickListener {
//            val intent = Intent()
//            intent.setClass(holder.itemView.context, BookDetailActivity::class.java)
//            Log.e("BookListAdapter","id:"+model.id)
//            intent.putExtra("title", model.title)
//            intent.putExtra("id", model.id)
//            context.startActivity(intent)
//        }
//    }
//}