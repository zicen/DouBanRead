package com.zhenquan.doubanread.ui.classfiy

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.moudle.TagBean
import com.zhenquan.doubanread.moudle.TagItem
import org.jetbrains.anko.find

/**
 * Created by zhenquan on 2018/1/3 0003.
 */
class BookTagListAdapter(val items: List<TagItem>, val itemClickListener: (TagItem) -> Unit) : RecyclerView.Adapter<BookTagListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tag_list_activity, parent, false)
        return ViewHolder(view, itemClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(val view: View, val itemClickListener: (TagItem) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(item: TagItem) {
            view.find<TextView>(R.id.tv_tag_title).text = item.name
            view.setOnClickListener { itemClickListener(item) }
        }
    }
}