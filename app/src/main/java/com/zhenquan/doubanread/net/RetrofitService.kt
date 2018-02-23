package com.zhenquan.doubanread.net

import com.zhenquan.doubanread.moudle.*
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

//    @FormUrlEncoded
//    @POST("auth/register")
//    fun authRegister(@Field("username") username: String, @Field("password") password: String): Observable<AllOfBean>
//
//    @FormUrlEncoded
//    @POST("auth/login")
//    fun authLogin(@Field("username") username: String, @Field("password") password: String): Observable<AllOfBean>
//


    @FormUrlEncoded
    @POST("user/login.do")
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<LoginUserInfo>

    @FormUrlEncoded
    @POST("user/logout.do")
    fun logout(): Observable<BasicResponseInfo>

    @FormUrlEncoded
    @POST("user/register.do")
    fun register(@FieldMap params: Map<String, String>): Observable<BasicResponseInfo>

    /**
     * 添加或删除已读
     */
    @FormUrlEncoded
    @POST("haveread/add.do")
    fun addOrRemoveHaveRead(@FieldMap params: Map<String, String>): Observable<BasicResponseInfo>

    /**
     * 添加或删除想读
     */
    @FormUrlEncoded
    @POST("wantread/add.do")
    fun addOrRemoveWantRead(@FieldMap params: Map<String, String>): Observable<BasicResponseInfo>
}