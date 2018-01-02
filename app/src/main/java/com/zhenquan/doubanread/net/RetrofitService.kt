package com.zhenquan.doubanread.net

import com.zhenquan.doubanread.moudle.BookDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ry on 2018/1/2.
 */
interface RetrofitService {
    @GET("book/search")
    abstract fun getSearchBook(@Query("q") name: String, @Query("tag") tag: String, @Query("start") start: Int, @Query("count") count: Int): Call<BookDetail>
}