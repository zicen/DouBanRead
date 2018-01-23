package com.zhenquan.doubanread.wight

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

import com.zhenquan.doubanread.R

/**
 * Created by ry on 2018/1/4.
 */

class TagView : android.support.v7.widget.AppCompatTextView {
    constructor(context: Context) : super(context) {
        init()
    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        this.setTextColor(resources.getColor(R.color.white))
        this.setBackgroundColor(resources.getColor(R.color.book_tag_color))
        this.setPadding(20, 20, 20, 20)
        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(20, 20, 20, 20)
        this.layoutParams = lp
    }

}
