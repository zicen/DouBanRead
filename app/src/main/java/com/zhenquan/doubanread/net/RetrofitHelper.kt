package com.zhenquan.doubanread.net

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Administrator on 2018/1/2 0002.
 */
object RetrofitHelper {
    //var retrofit: Retrofit? = null
    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    fun getServer(): RetrofitService? {
        return retrofit?.create(RetrofitService::class.java)
    }

}