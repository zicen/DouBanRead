package com.zhenquan.doubanread.ui.classfiy

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.moudle.TagBean
import org.jetbrains.anko.find

/**
 * Created by ry on 2018/1/3.
 */
class ClassfiyAdapter(val items: List<TagBean>, val itemClickListener: (TagBean) -> Unit) : RecyclerView.Adapter<ClassfiyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fragment_classfiy, parent, false)
        return ViewHolder(view, itemClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(val view: View, val itemClickListener: (TagBean) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(item: TagBean) {
            view.find<TextView>(R.id.tv_classfiy_title).text = item.title
            view.find<ImageView>(R.id.iv_classfiy).setImageResource(item.image)
            view.setOnClickListener { itemClickListener(item) }
        }
    }

}