package com.zhenquan.doubanread

import android.util.Log
import com.google.gson.GsonBuilder
import com.zhenquan.doubanread.R.id.*
import com.zhenquan.doubanread.moudle.BookDetail
import com.zhenquan.doubanread.net.RetrofitService
import com.zhenquan.player.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    override fun initListener() {
        bottomBar.setOnTabSelectListener {
            when (it) {
                tab_tuijian -> getDetailBook()
                tab_yuanchuang -> toast("tab_yuanchuang")
                tab_shudian -> toast("tab_shudian")
                tab_wode -> toast("tab_wode")
                tab_bendi -> toast("tab_bendi")
            }
        }

    }

    private fun getDetailBook() {
        var retrofit = Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()

        var service = retrofit.create(RetrofitService::class.java)
        var call = service.getSearchBook("金瓶梅", "", 0, 1)
        call.enqueue(object : Callback<BookDetail> {
            override fun onResponse(call: Call<BookDetail>?, response: Response<BookDetail>?) {
                Log.e("111", "response:" +  response?.body().toString())
                toast( response?.body().toString())
            }

            override fun onFailure(call: Call<BookDetail>?, t: Throwable?) {
                Log.e("111", "t:" + t.toString())
            }

        })


    }


}
