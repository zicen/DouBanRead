package com.zhenquan.doubanread.net

import com.zhenquan.doubanread.moudle.AllOfBean
import com.zhenquan.doubanread.moudle.BookDetail
import com.zhenquan.doubanread.moudle.SearchBookList
import retrofit2.http.*
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

    @FormUrlEncoded
    @POST("auth/register")
    fun authRegister(@Field("username") username: String, @Field("password") password: String): Observable<AllOfBean>

    @FormUrlEncoded
    @POST("auth/login")
    fun authLogin(@Field("username") username: String, @Field("password") password: String): Observable<AllOfBean>

}