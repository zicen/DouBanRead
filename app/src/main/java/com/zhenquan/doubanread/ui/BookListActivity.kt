package com.zhenquan.doubanread.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity

class BookListActivity : BaseActivity() {
    override fun initView(rootView: View?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = intent.extras["title"]
        Log.e("111", "title:" + title)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_book_list
    }

}
