package com.zhenquan.doubanread.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.base.adapter.BaseRecyclerAdapter
import com.zhenquan.doubanread.base.adapter.SmartViewHolder
import com.zhenquan.doubanread.moudle.Book
import com.zhenquan.doubanread.moudle.Item7
import com.zhenquan.doubanread.moudle.ReadListBean
import com.zhenquan.doubanread.moudle.checkSuccess
import com.zhenquan.doubanread.net.RetrofitHelper
import com.zhenquan.doubanread.ui.classfiy.BookDetailActivity
import com.zhenquan.doubanread.util.ToastUtil
import kotlinx.android.synthetic.main.activity_my_release.*
import kotlinx.android.synthetic.main.activity_my_want_change.*
import org.jetbrains.anko.find
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MyReleaseActivity : BaseActivity() {
    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun initView(rootView: View?) {
        initToolBar()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_my_release
    }

    private fun initToolBar(): String? {
        val title = intent.extras.getString("title")
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return title
    }
    val adapter: BaseRecyclerAdapter<Item7> by lazy {
        object : BaseRecyclerAdapter<Item7>(R.layout.item_book_list) {
            override fun onBindViewHolder(holder: SmartViewHolder, model: Item7, position: Int) {
                holder.text(R.id.tv_book_name, model.title)
                holder.text(R.id.tv_book_author, model.author)
                holder.text(R.id.tv_book_comment, model.rating)
                holder.text(R.id.tv_book_jianjie, model.summary)
                val image = holder.image(R.id.iv_book_list)
                Picasso.with(holder.itemView.context).load(model.image).into(image)
                holder.itemView.setOnClickListener {
                    val intent = Intent()
                    intent.setClass(holder.itemView.context, BookDetailActivity::class.java)
                    Log.e(TAG, "id" + model.id)
                    intent.putExtra("title", model.title)
                    intent.putExtra("id", ""+model.id)
                    startActivity(intent)
                }
            }
        }
    }
    var start = 1
    override fun initListener() {
        my_release_recycle.layoutManager = LinearLayoutManager(this)
        my_release_recycle.adapter = adapter
        initData(start)
        my_release_refreshLayout.setOnRefreshListener {
            initData(start)
        }
        my_release_refreshLayout.setOnLoadmoreListener({ refreshlayout ->
            getData(start)
        })

    }

    private fun initData(start_getdata: Int) {
        request(RetrofitHelper
                .getServerForAliYun()?.getHaveReadList(start_getdata, 10)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { t: ReadListBean? ->
                    t?.checkSuccess {
                        //成功
                        t.data.list.let {
                            adapter.refresh(t.data.list)
                            my_release_refreshLayout.finishRefresh()
                            my_release_refreshLayout.resetNoMoreData()
                            start = 2
                        }
                    }
                }
        )
    }

    private fun getData(start_getdata: Int) {
        request(RetrofitHelper
                .getServerForAliYun()?.getWantReadList(start_getdata, 10)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { t: ReadListBean? ->
                    t?.checkSuccess {
                        //成功
                        t.data.list.let {
                            adapter.loadmore(it)
                            start += 1
                            if (it.size < 20) {
                                my_release_refreshLayout.finishLoadmoreWithNoMoreData()//将不会再次触发加载更多事件
                            } else {
                                my_release_refreshLayout.finishLoadmore()
                            }
                        }
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
