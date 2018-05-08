package com.zhenquan.doubanread.net

import android.util.Log
import com.google.gson.GsonBuilder
import com.zhenquan.doubanread.moudle.UserInfo
import com.zhenquan.doubanread.ui.App
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.jsoup.helper.StringUtil
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.Gson




/**
 * Created by Administrator on 2018/1/2 0002.
 */
object RetrofitHelper {
    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.e("RetrofitHelper", "retrofitBack =" + message) })
    }
    private val headInterceptor by lazy {
        Interceptor { chain ->
            if (StringUtil.isBlank(UserInfo.getHeader(App.instance()))) {
                var request = chain?.request()?.newBuilder()
                        ?.build()
                Log.e("RetrofitHelper","UserInfo.getHeader is blank")
                chain?.proceed(request)!!
            } else {
                Log.e("RetrofitHelper","UserInfo.getHeader not blank")
                var request = chain?.request()?.newBuilder()
                        ?.addHeader("cookie", UserInfo.getHeader(App.instance()))
                        ?.build()
                chain?.proceed(request)!!
            }

        }
    }

    //var retrofit: Retrofit? = null
    private val client by lazy {
        OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headInterceptor)
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