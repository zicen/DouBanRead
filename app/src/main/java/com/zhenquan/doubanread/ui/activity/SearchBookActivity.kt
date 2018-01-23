package com.zhenquan.doubanread.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.wight.SearchHistoryItem
import kotlinx.android.synthetic.main.activity_search_book.*

class SearchBookActivity : BaseActivity() {
    var history_dataSet = HashSet<String>()
    override fun getLayoutId(): Int {
        return R.layout.activity_search_book
    }

    override fun initView(rootView: View?) {
        initHistory()
        tv_search.setOnClickListener {
            //save
            saveHistory()

            initHistory()

        }

    }

    private fun initHistory() {
        val sharedPreferences = getSharedPreferences("search_book_history", Context.MODE_PRIVATE)
        val stringset = sharedPreferences.getStringSet("history_dataSet", null)
        stringset?.let {
            for (item in stringset) {
                flow_history.addView(SearchHistoryItem(this, {
                    history_dataSet.remove(item)
                    flow_history.removeView(it)
                }))
            }

        }
    }

    private fun saveHistory() {
        val searchText = edit_search.text.trim().toString()
        history_dataSet.add(searchText)
        val edit = getSharedPreferences("search_book_history", Context.MODE_PRIVATE).edit()
        edit.putStringSet("history_dataSet", history_dataSet)
        edit.commit()
    }


}
