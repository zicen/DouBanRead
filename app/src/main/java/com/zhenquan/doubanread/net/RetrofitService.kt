package com.zhenquan.doubanread.net

import com.zhenquan.doubanread.moudle.BookDetail
import com.zhenquan.doubanread.moudle.SearchBookList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by Administrator on 2018/1/2 0002.
 */
interface RetrofitService {

    @GET("book/search")
    fun getSearchBook(@Query("q") name: String, @Query("tag") tag: String, @Query("start") start: Int, @Query("count") count: Int): Observable<SearchBookList>

    //    https://api.douban.com/v2/book/:id
    @GET("book/{id}")
    fun getBookDetail(@Path("id") id: String): Observable<BookDetail>


}