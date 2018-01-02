package com.zhenquan.doubanread.net

import com.zhenquan.doubanread.moudle.BookDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

/**
 * Created by Administrator on 2018/1/2 0002.
 */
interface RetrofitService {

    @GET("book/search")
    fun getSearchBook(@Query("q") name: String, @Query("tag") tag: String, @Query("start") start: Int, @Query("count") count: Int): Call<BookDetail>
}