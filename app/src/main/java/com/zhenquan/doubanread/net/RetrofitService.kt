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
    /**
     * 豆瓣书籍搜索
     */
    @GET("book/search")
    fun getSearchBook(@Query("q") name: String, @Query("tag") tag: String, @Query("start") start: Int, @Query("count") count: Int): Observable<SearchBookList>

    /**
     * 获取书籍详情
     */
    @GET("book/{id}")
    fun getBookDetail(@Path("id") id: String): Observable<BookDetail>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login.do")
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<retrofit2.Response<LoginUserInfo>>

    /**
     * 获取用户信息
     */
    @POST("user/get_user_info.do")
    fun getUserInfo(): Observable<retrofit2.Response<LoginUserInfo>>

    /**
     * 更新用户信息
     */
    @FormUrlEncoded
    @POST("user/update_information.do")
    fun updateUserInfo(@FieldMap params: Map<String, String>): Observable<BasicResponseInfo>

    /**
     * 登出
     */
    @POST("user/logout.do")
    fun logout(): Observable<BasicResponseInfo>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/register.do")
    fun register(@FieldMap params: Map<String, String>): Observable<BasicResponseInfo>

    /**
     * 添加或删除已读
     */
    @FormUrlEncoded
    @POST("haveread/add.do")
    fun addOrRemoveHaveRead(@FieldMap params: Map<String, String>): Observable<BasicResponseInfo2>


    /**
     * 添加或删除想读
     */
    @FormUrlEncoded
    @POST("wantread/add.do")
    fun addOrRemoveWantRead(@FieldMap params: Map<String, String>): Observable<BasicResponseInfo2>


    /**
     * 获取已读列表
     */
    @FormUrlEncoded
    @POST("haveread/list.do")
    fun getHaveReadList(@Field("pageNum") pageNum: Int, @Field("pageSize") pageSize: Int): Observable<ReadListBean>

    /**
     * 获取想读列表
     */
    @FormUrlEncoded
    @POST("wantread/list.do")
    fun getWantReadList(@Field("pageNum") pageNum: Int, @Field("pageSize") pageSize: Int): Observable<ReadListBean>

    /**
     * 获取匹配成功的列表
     */
    @POST("wantread/matchlist.do")
    fun getMatchesList(): Observable<MatchesBean>

}