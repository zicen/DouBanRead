package com.zhenquan.doubanread.wight

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.view.LayoutInflater
import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.moudle.Book


/**
 * Created by ry on 2018/1/23.
 */
class SearchHistoryItem(context: Context,val itemClickListener: (View) -> Unit) : LinearLayout(context) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.item_search_histoty, this, true)
        view.setOnClickListener { itemClickListener(view) }
    }
}