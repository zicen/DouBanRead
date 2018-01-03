package com.zhenquan.doubanread.ui.classfiy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

class BookListActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_book_list
    }

    override fun initView(rootView: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun initListener() {
        val title = initToolBar()
        book_list_recycle.layoutManager = LinearLayoutManager(this)
        if (title != null) {
            getData(title)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initData() {


    }

    private fun BookListActivity.getData(title: String) {
        requestComposite.add(DataManager().getSearchBooks("", title, 0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BookDetail> {
                    override fun onNext(t: BookDetail?) {
                        t?.books?.let {
                            book_list_recycle.adapter = BookListAdapter(it) {
                                toast(it.toString())
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

    private fun initToolBar(): String? {
        val title = intent.extras.getString("title")
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return title
    }
}
