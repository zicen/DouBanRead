package com.zhenquan.doubanread.ui.activity

import android.util.Log
import android.view.View
import com.google.gson.GsonBuilder
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.manager.DataManager
import com.zhenquan.doubanread.moudle.BookDetail
import com.zhenquan.doubanread.net.RetrofitService
import com.zhenquan.player.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        super.initData()
    }

    override fun initListener() {
        btn.setOnClickListener {
            simpleRequest()
        }
        btn2.setOnClickListener {
            //以后请求就用这个
            sampleRequest()
        }


    }

    private fun sampleRequest() {
        requestComposite.add(DataManager().getSearchBooks("金瓶梅", "", 0, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BookDetail> {
                    override fun onNext(t: BookDetail?) {
                        tv.text = t.toString()
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                    }

                })

        )


    }

    private fun simpleRequest() {
        var retrofit = Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava
                .build()
        val service = retrofit.create(RetrofitService::class.java)
        var observable = service.getSearchBook("金瓶梅", "", 0, 1)
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BookDetail> {
                    override fun onNext(t: BookDetail?) {
                        tv.text = t.toString()
                    }

                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                    }

                })
    }
}
