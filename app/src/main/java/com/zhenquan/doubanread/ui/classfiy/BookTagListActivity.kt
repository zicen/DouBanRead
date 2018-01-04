package com.zhenquan.doubanread.ui.classfiy

import android.content.Intent
import android.content.res.AssetManager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.google.gson.Gson
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.moudle.BookTag
import kotlinx.android.synthetic.main.activity_book_tag_list.*
import org.jetbrains.anko.find
import java.nio.charset.Charset

class BookTagListActivity : BaseActivity() {
    override fun initView(rootView: View?) {

    }

    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun initListener() {
        super.initListener()
        val title = initToolBar()
        book_tag_list_recycle.layoutManager = GridLayoutManager(this, 2)
        val json = assets.fileAsString("json", "doubanreadtag.json")
        val bookTag = Gson().fromJson(json, BookTag::class.java)
        val filter = bookTag.booktag.filter {
            it.tag_name == title
        }
        book_tag_list_recycle.adapter = BookTagListAdapter(filter) {
            val intent = Intent()
            intent.setClass(BookTagListActivity@this, BookListActivity::class.java)
            intent.putExtra("title", it.name)
            startActivity(intent)
        }

    }

    private fun initToolBar(): String? {
        val title = intent.extras.getString("title")
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return title
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_book_tag_list
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun AssetManager.fileAsString(subdirectory: String, filename: String): String {
        return open("$subdirectory/$filename").use {
            it.readBytes().toString(Charset.defaultCharset())
        }
    }
}
