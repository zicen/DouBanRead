package com.zhenquan.doubanread.wight

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.moudle.Book
import org.jetbrains.anko.find


/**
 * Created by ry on 2018/1/23.
 */
class SearchHistoryItem(context: Context, text: String, val deleteClickListener: (View) -> Unit, val itemClickListener: (View) -> Unit) : LinearLayout(context) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.item_search_histoty, this, true)
        view.find<TextView>(R.id.hotel_search_history_text).text = text
        view.find<ImageView>(R.id.hotel_search_history_image).setOnClickListener { deleteClickListener(view) }
        view.setOnClickListener { itemClickListener(view) }
    }
}