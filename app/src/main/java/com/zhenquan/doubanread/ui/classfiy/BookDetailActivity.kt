package com.zhenquan.doubanread.ui.classfiy

import android.content.Context
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.manager.DataManager
import com.zhenquan.doubanread.moudle.*
import com.zhenquan.doubanread.net.RetrofitHelper
import com.zhenquan.doubanread.util.CheckUtil
import com.zhenquan.doubanread.util.ToastUtil
import com.zhenquan.doubanread.wight.TagView
import kotlinx.android.synthetic.main.activity_book_detail.*
import org.jetbrains.anko.find
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import kotlin.collections.HashMap

class BookDetailActivity : BaseActivity() {

    var flag_jianjie = true
    var flag_mulu = true
    lateinit var bookDetail: BookDetail
    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun getLayoutId(): Int {
        return R.layout.activity_book_detail
    }

    override fun initView(rootView: View?) {
        if (!UserInfo.getUserIsLogin(this)) {
            ToastUtil.systemToast("请先登录!")
            finish()
        }

    }

    override fun initListener() {
        val title = initToolBar()
        val id = intent.getStringExtra("id")
        getData(id)
        tv_book_detail_jianjie.setOnClickListener {
            if (flag_jianjie) {
                flag_jianjie = false
                tv_book_detail_jianjie.ellipsize = null  //展开
                tv_book_detail_jianjie.maxLines = 10000
            } else {
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
            } else {
                flag_mulu = true
                tv_book_detail_mulu.ellipsize = TextUtils.TruncateAt.END //收缩
                tv_book_detail_mulu.maxLines = 12
            }
        }
        btn_i_have_read.setOnClickListener {
            //读过
            haveRead()
        }
        btn_i_want_read.setOnClickListener {
            //想读
            wantRead()
        }

    }

    private fun getData(id: String) {
        showWaitDialog()
        requestComposite.add(DataManager().getBookDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BookDetail> {
                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                        hideWaitDialog()
                    }

                    override fun onNext(t: BookDetail?) {
                        hideWaitDialog()
                        t?.let {
                            bookDetail = it
                            Picasso.with(this@BookDetailActivity).load(it.image).into(iv_book_detail);
                            tv_book_name.text = it.title
                            tv_book_author.text = it.author[0]
                            tv_book_comment.text = it.rating.average + " " + it.rating.numRaters + "评价"
                            tv_book_detail_jianjie.text = it.summary
                            tv_book_tongji.text = "约" + it.pages + "页"
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

    private fun haveRead() {
        bookDetail.let {
            var params = getParams(it)
            request(RetrofitHelper
                    .getServerForAliYun()?.addOrRemoveHaveRead(params)
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe { t: BasicResponseInfo2? ->
                        t?.checkSuccess {
                            //成功
                            ToastUtil.imageToast(R.mipmap.ic_success, t.data)
                        }
                    }
            )

        }
    }

    private fun wantRead() {
        bookDetail.let {
            var params = getParams(it)
            request(RetrofitHelper
                    .getServerForAliYun()?.addOrRemoveWantRead(params)
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe { t: BasicResponseInfo2? ->
                        t?.checkSuccess {
                            //成功
                            ToastUtil.imageToast(R.mipmap.ic_success, t.data)
                        }
                    }
            )

        }
    }


    private fun getParams(it: BookDetail): HashMap<String, String> {
        val params = HashMap<String, String>()
        params.put("id", it.id)
        params.put("title", it.title)
        params.put("rating", it.rating.average)
        if (it.author.isNotEmpty()) {
            params.put("author", it.author[0])
        }
//        params.put("pubdate", it.pubdate)
//        params.put("origin_title", it.origin_title)
        params.put("image", it.image)
//        params.put("binding", it.binding)
//        if (it.translator.isNotEmpty()) {
//            params.put("translator", it.translator[0])
//        }
//        params.put("catelog", it.catalog)
//        params.put("pages", it.pages)
        params.put("image_large", it.images.large)
//        params.put("publisher", it.publisher)
//        params.put("isbn10", it.isbn10)
//        params.put("isbn13", it.isbn13)
//        params.put("author_intro", it.author_intro)
        params.put("summary", it.summary)
//        params.put("price", it.price)
//        params.put("categotyid", it.catalog)
//        val userLoginInfo = UserInfo.getUserLoginInfo(this)
//        params.put("userId", "" + userLoginInfo.id)
        return params
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
