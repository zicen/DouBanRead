package com.zhenquan.doubanread.net

import com.zhenquan.doubanread.moudle.*
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import retrofit2.Call
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
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<retrofit2.Response<LoginUserInfo>>

    @POST("user/get_user_info.do")
    fun getUserInfo(): Observable<retrofit2.Response<LoginUserInfo>>

    @POST("user/logout.do")
    fun logout(): Observable<BasicResponseInfo>

    @FormUrlEncoded
    @POST("user/register.do")
    fun register(@FieldMap params: Map<String, String>): Observable<BasicResponseInfo>

    /**
     * 添加或删除已读
     */
    @POST("haveread/add.do")
    fun addOrRemoveHaveRead(@Body params: RequestBody): Observable<BasicResponseInfo>


    /**
     * 添加或删除想读
     */
    @POST("wantread/add.do")
    fun addOrRemoveWantRead(@Body params: RequestBody): Observable<BasicResponseInfo>

    @FormUrlEncoded
    @POST("haveread/list.do")
    fun getHaveReadList(@Field("pageNum") pageNum: Int,@Field("pageSize") pageSize: Int): Observable<ReadListBean>

    @FormUrlEncoded
    @POST("wantread/list.do")
    fun getWantReadList(@Field("pageNum") pageNum: Int,@Field("pageSize") pageSize: Int): Observable<ReadListBean>


}