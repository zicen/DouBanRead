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

    override fun getLayoutId(): Int {
        return R.layout.activity_search_book
    }

    override fun initView(rootView: View?) {
        initHistory()


        tv_search.setOnClickListener {
            //request data


            //save history
            addHistory(edit_search.text.toString())
            initHistory()
        }

    }

    private fun initHistory() {
        val sharedPreferences = getSharedPreferences("search_book_history", Context.MODE_PRIVATE)
        var history_dataSet = sharedPreferences.getStringSet("history_dataSet", null) as HashSet<String>
        flexbox_search_book.removeAllViews()
        history_dataSet?.let { historySet ->
            for (item in historySet) {
                flexbox_search_book.addView(SearchHistoryItem(this, item, {
                    historySet.remove(item)
                    flexbox_search_book.removeView(it)
                    saveHistory(historySet)
                }))
            }

        }
    }

    private fun saveHistory(history_dataSet: HashSet<String>) {
        val edit = getSharedPreferences("search_book_history", Context.MODE_PRIVATE).edit()
        edit.putStringSet("history_dataSet", history_dataSet)
        edit.commit()
    }

    private fun addHistory(data: String) {
        val sharedPreferences = getSharedPreferences("search_book_history", Context.MODE_PRIVATE)
        var history_dataSet = sharedPreferences.getStringSet("history_dataSet", null) as HashSet<String>
        history_dataSet.add(data)
        saveHistory(history_dataSet)
    }


}
