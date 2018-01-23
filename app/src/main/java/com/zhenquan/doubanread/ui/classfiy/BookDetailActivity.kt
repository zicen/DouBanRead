package com.zhenquan.doubanread.ui.classfiy

import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.manager.DataManager
import com.zhenquan.doubanread.moudle.BookDetail
import com.zhenquan.doubanread.moudle.SearchBookList
import com.zhenquan.doubanread.wight.TagView
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.activity_book_list.*
import org.jetbrains.anko.find
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class BookDetailActivity : BaseActivity() {

    var flag_jianjie = true
    var flag_mulu = true

    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun getLayoutId(): Int {
        return R.layout.activity_book_detail
    }

    override fun initView(rootView: View?) {
    }

    override fun initListener() {
        val title = initToolBar()
        val id = intent.getStringExtra("id")
        getData(id)
        tv_book_detail_jianjie.setOnClickListener{
            if (flag_jianjie) {
                flag_jianjie = false
                tv_book_detail_jianjie.ellipsize = null  //展开
                tv_book_detail_jianjie.maxLines = 10000
            }else{
                flag_jianjie = true
                tv_book_detail_jianjie.ellipsize = TextUtils.TruncateAt.END //收缩
                tv_book_detail_jianjie.maxLines = 12
            }
        }
        tv_book_detail_mulu.setOnClickListener {
            if (flag_mulu) {
                flag_mulu = false
                tv_book_detail_mulu.ellipsize = null  //展开
                tv_book_detail_mulu.maxLines = 10000
            }else{
                flag_mulu = true
                tv_book_detail_mulu.ellipsize = TextUtils.TruncateAt.END //收缩
                tv_book_detail_mulu.maxLines = 12
            }
        }

    }

    private fun getData(id: String) {
        requestComposite.add(DataManager().getBookDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BookDetail> {
                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                    }

                    override fun onNext(t: BookDetail?) {
                        t?.let {
                            Picasso.with(this@BookDetailActivity).load(it.image).into(iv_book_detail);
                            tv_book_name.text = it.title
                            tv_book_author.text = it.author[0]
                            tv_book_comment.text = it.rating.average + " " + it.rating.numRaters + "评价"
                            tv_book_detail_jianjie.text = it.summary
                            tv_book_tongji.text = "约" + it.pages
                            tv_book_publisher.text = it.publisher
                            tv_book_detail_mulu.text = it.catalog
                            for (tag in it.tags) {
                                val tagView = TagView(this@BookDetailActivity)
                                tagView.text = tag.name
                                book_main_flowlayout.addView(tagView)
                            }
                        }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
