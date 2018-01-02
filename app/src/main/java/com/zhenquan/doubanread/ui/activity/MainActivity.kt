package com.zhenquan.doubanread.ui.activity

import android.util.Log
import com.google.gson.GsonBuilder
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.moudle.BookDetail
import com.zhenquan.doubanread.net.RetrofitService
import com.zhenquan.player.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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
    }

    private fun simpleRequest() {
        var retrofit = Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava
                .build()
        val service = retrofit.create(RetrofitService::class.java)
        var call = service.getSearchBook("金瓶梅", "", 0, 1)
        call.enqueue(object : Callback<BookDetail> {
            override fun onFailure(call: Call<BookDetail>?, t: Throwable?) {
                Log.e("111", "error:" + t.toString())

            }

            override fun onResponse(call: Call<BookDetail>?, response: Response<BookDetail>?) {
                toast(response?.body().toString())
                tv.text = response?.body().toString()
            }

        })
    }
}
