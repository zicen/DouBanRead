package com.zhenquan.doubanread.ui.classfiy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.manager.DataManager
import com.zhenquan.doubanread.moudle.BookDetail
import kotlinx.android.synthetic.main.activity_book_list.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.zhenquan.doubanread.moudle.Book


class BookListActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_book_list
    }

    override fun initView(rootView: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    var datalist: MutableList<Book> = ArrayList()
    var start = 0
    var count = 20
    override fun initListener() {
        val title = initToolBar()
        book_list_recycle.layoutManager = LinearLayoutManager(this)
        book_list_recycle.adapter = BookListAdapter(datalist) {
            toast(it.toString())
        }
        if (title != null) {
            getData(title, 0, 20)
        }

        book_list_refreshLayout.setOnRefreshListener {
            toast("onRefresh")
            getData(title!!, 0, 20)
        }
        book_list_refreshLayout.setOnLoadmoreListener({ refreshlayout ->
            toast("onLoadmore")
            getData(title!!, start, count)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun BookListActivity.getData(title: String, start_getdata: Int, count_getdata: Int) {
        requestComposite.add(DataManager().getSearchBooks("", title, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BookDetail> {
                    override fun onNext(t: BookDetail?) {
                        t?.books?.let {
                            if (start_getdata == 0) {
                                datalist.clear()
                                datalist.addAll(it)
                                start = 20
                                count = 40
                            }else{
                                datalist.addAll(it)
                                start += 20
                                count += 20
                            }
                            book_list_recycle.adapter.notifyDataSetChanged()
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

