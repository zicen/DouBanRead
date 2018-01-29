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
import com.zhenquan.doubanread.manager.DataManager
import com.zhenquan.doubanread.moudle.Book
import com.zhenquan.doubanread.moudle.SearchBookList
import com.zhenquan.doubanread.ui.classfiy.BookDetailActivity
import kotlinx.android.synthetic.main.activity_book_list.*
import kotlinx.android.synthetic.main.activity_search_result.*
import org.jetbrains.anko.find
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import android.view.ViewGroup



class SearchResultActivity : BaseActivity() {
    override fun initView(rootView: View?) {
    }

    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    val adapter: BaseRecyclerAdapter<Book> by lazy {
        object : BaseRecyclerAdapter<Book>(R.layout.item_book_list) {
            override fun onBindViewHolder(holder: SmartViewHolder, model: Book, position: Int) {
                holder.text(R.id.tv_book_name, model.title)
                holder.text(R.id.tv_book_author, if(model.author.size >0){model.author[0]}else{""})
                holder.text(R.id.tv_book_comment, model.rating.average)
                holder.text(R.id.tv_book_jianjie, model.summary)
                val image = holder.image(R.id.iv_book_list)
                Picasso.with(holder.itemView.context).load(model.image).into(image)
                holder.itemView.setOnClickListener {
                    val intent = Intent()
                    intent.setClass(holder.itemView.context, BookDetailActivity::class.java)
                    Log.e(TAG, "id:" + model.id)
                    intent.putExtra("title", model.title)
                    intent.putExtra("id", model.id)
                    startActivity(intent)
                }
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search_result
    }

    var start = 0
    override fun initListener() {
        val title = initToolBar()
        recycle_search_result.layoutManager = LinearLayoutManager(this)
        recycle_search_result.adapter = adapter
        initData(title!!, 0)
        refreshLayout_search_result.setOnRefreshListener {
            initData(title!!, 0)
        }
        refreshLayout_search_result.setOnLoadmoreListener({ refreshlayout ->
            getData(title!!)
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getData(title: String) {
        requestComposite.add(DataManager().getSearchBooks(title, "", start, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<SearchBookList> {
                    override fun onNext(t: SearchBookList?) {
                        t?.books?.let {
                            adapter.loadmore(it)
                            start += it.size
                            if (it.size < 20) {
                                Toast.makeText(application, "数据全部加载完毕", Toast.LENGTH_SHORT).show()
                                refreshLayout_search_result.finishLoadmoreWithNoMoreData()//将不会再次触发加载更多事件
                            } else {
                                refreshLayout_search_result.finishLoadmore()
                            }

                        }

                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                    }

                })
        )
    }

    private fun initData(title: String, start_getdata: Int) {
        requestComposite.add(DataManager().getSearchBooks("", title, start_getdata, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<SearchBookList> {
                    override fun onNext(t: SearchBookList?) {
                        t?.books?.let {
                            Log.e("response", "response size:" + it.size)

                            adapter.refresh(it)
                            refreshLayout_search_result.finishRefresh()
                            refreshLayout_search_result.resetNoMoreData()
                            start = it.size
                        }

                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                    }

                })

        )
    }

    private fun initToolBar(): String? {
        val title = intent.extras.getString("title")
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return title
    }

}
