package com.zhenquan.doubanread.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.squareup.picasso.Picasso
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.base.adapter.BaseRecyclerAdapter
import com.zhenquan.doubanread.base.adapter.SmartViewHolder
import com.zhenquan.doubanread.manager.DataManager
import com.zhenquan.doubanread.moudle.*
import com.zhenquan.doubanread.net.RetrofitHelper
import com.zhenquan.doubanread.ui.classfiy.BookDetailActivity
import com.zhenquan.doubanread.util.ToastUtil
import kotlinx.android.synthetic.main.activity_book_list.*
import kotlinx.android.synthetic.main.activity_my_want_change.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.jetbrains.anko.find
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MyWantChangeActivity : BaseActivity() {
    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun initView(rootView: View?) {
        initToolBar()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_my_want_change
    }

    private fun initToolBar(): String? {
        val title = intent.extras.getString("title")
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return title
    }
    val adapter: BaseRecyclerAdapter<Book> by lazy {
        object : BaseRecyclerAdapter<Book>(R.layout.item_book_list) {
            override fun onBindViewHolder(holder: SmartViewHolder, model: Book, position: Int) {
                holder.text(R.id.tv_book_name, model.title)
                holder.text(R.id.tv_book_author, if(model.author.isNotEmpty()){model.author[0]}else{""})
                holder.text(R.id.tv_book_comment, model.rating.average)
                holder.text(R.id.tv_book_jianjie, model.summary)
                val image = holder.image(R.id.iv_book_list)
                Picasso.with(holder.itemView.context).load(model.image).into(image)
                holder.itemView.setOnClickListener {
                    val intent = Intent()
                    intent.setClass(holder.itemView.context, BookDetailActivity::class.java)
                    Log.e(TAG,"id:"+model.id)
                    intent.putExtra("title", model.title)
                    intent.putExtra("id", model.id)
                    startActivity(intent)
                }
            }
        }
    }
    var start = 0
    override fun initListener() {
        my_want_recycle.layoutManager = LinearLayoutManager(this)
        my_want_recycle.adapter = adapter
        my_want_refreshLayout.setOnRefreshListener {
            initData(0)
        }
        my_want_refreshLayout.setOnLoadmoreListener({ refreshlayout ->

        })
        my_want_refreshLayout.autoRefresh()

    }
    private fun initData(start_getdata:Int) {
        request(RetrofitHelper
                .getServerForAliYun()?.getWantReadList(start_getdata,10)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { t: ReadListBean? ->
                    t?.checkSuccess {
                        //成功
                        ToastUtil.systemToast("success")
                        Log.e(TAG,"t:"+t.data.toString())
                    }
                }
        )
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }



}
