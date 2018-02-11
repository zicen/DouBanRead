package com.zhenquan.doubanread.ui.activity

import android.content.Context
import android.content.Intent
import android.util.Log
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
            //save history
            addHistory(edit_search.text.toString())
            initHistory()
            //request data
            goSearchResult(edit_search.text.toString())
        }

    }

    private fun goSearchResult(title: String) {
        var intent = Intent()
        intent.setClass(SearchBookActivity@ this, SearchResultActivity::class.java)
        intent.putExtra("title", title)
        startActivity(intent)
    }

    private fun initHistory() {
        val sharedPreferences = getSharedPreferences("search_book_history", Context.MODE_PRIVATE)
        var history_dataSet = sharedPreferences.getStringSet("history_dataSet", null)
        flexbox_search_book.removeAllViews()
        history_dataSet?.let { historySet ->
            for (item in historySet) {
                flexbox_search_book.addView(SearchHistoryItem(this, item, {
                    //delete
                    historySet.remove(item)
                    flexbox_search_book.removeView(it)
                    Log.e(TAG, "savesaveHistory:" + historySet.size)
                    saveHistory(historySet)
                }, {
                    //itemclick
                    goSearchResult(item)
                }))
            }

        }
    }

    private fun saveHistory(history_dataSet: Set<String>) {
        val edit = getSharedPreferences("search_book_history", Context.MODE_PRIVATE).edit()
        edit.clear()
        edit.putStringSet("history_dataSet", history_dataSet)
        val commit = edit.commit()
        Log.e(TAG, "isCommit:" + commit)
    }

    private fun addHistory(data: String) {
        val sharedPreferences = getSharedPreferences("search_book_history", Context.MODE_PRIVATE)
        var historyDataSet = sharedPreferences.getStringSet("history_dataSet", HashSet<String>())
        historyDataSet?.add(data)
        saveHistory(historyDataSet)
    }


}
