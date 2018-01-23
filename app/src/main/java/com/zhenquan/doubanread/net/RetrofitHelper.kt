package com.zhenquan.doubanread.net

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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

    private val retrofitForAliYun by lazy {
        Retrofit.Builder()
                .baseUrl("http://112.74.191.1:9000/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }
    private val retrofitForZhenQuanAliYun by lazy {
        Retrofit.Builder()
                .baseUrl("http://39.107.84.145:8080/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }
    fun getServer(): RetrofitService? {
        return retrofit?.create(RetrofitService::class.java)
    }

    fun getServerForAliYun(): RetrofitService? {
        return retrofitForZhenQuanAliYun?.create(RetrofitService::class.java)
    }



}