package com.zhenquan.doubanread.net

import android.content.Context
import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Math.log
import java.util.concurrent.TimeUnit


/**
 * Created by Administrator on 2018/1/2 0002.
 */
object RetrofitHelper {
    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.e("RetrofitHelper", "retrofitBack =" + message) })
    }
    //var retrofit: Retrofit? = null
    private val client by lazy {
        OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    fun getServer(): RetrofitService? {
        return retrofit?.create(RetrofitService::class.java)
    }

}