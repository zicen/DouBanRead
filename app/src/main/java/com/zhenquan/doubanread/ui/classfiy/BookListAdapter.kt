package com.zhenquan.doubanread.ui.classfiy

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.moudle.Book
import org.jetbrains.anko.find

/**
 * Created by zhenquan on 2018/1/3 0003.
 */
class BookListAdapter(val items: List<Book>, val itemClickListener: (Book) -> Unit) : RecyclerView.Adapter<BookListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book_list, parent, false)
        return ViewHolder(view, itemClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(val view: View, val itemClickListener: (Book) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(item: Book) {
            view.find<TextView>(R.id.tv_book_name).text = item.title
            view.find<TextView>(R.id.tv_book_author).text = item.author[0]
            view.find<TextView>(R.id.tv_book_comment).text = item.rating.average
            view.find<TextView>(R.id.tv_book_jianjie).text = item.summary
            val imageView = view.find<ImageView>(R.id.iv_book_list)
            Picasso.with(view.context).load(item.image).into(imageView);
            view.setOnClickListener { itemClickListener(item) }
        }
    }
}