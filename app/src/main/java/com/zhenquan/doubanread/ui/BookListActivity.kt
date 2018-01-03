package com.zhenquan.doubanread.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import org.jetbrains.anko.find

class BookListActivity : BaseActivity() {
    override fun initView(rootView: View?) {

    }

    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun initListener() {
        super.initListener()
        val title = intent.extras.getString("title")
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_book_list
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
