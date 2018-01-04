package com.zhenquan.doubanread.manager

import android.util.Log
import com.zhenquan.doubanread.moudle.BookDetail
import com.zhenquan.doubanread.net.RetrofitHelper
import com.zhenquan.doubanread.net.RetrofitService
import rx.Observable

/**
 * Created by Administrator on 2018/1/2 0002.
 */
class DataManager {
    val TAG = DataManager::class.java.simpleName
    private val retrofitService by lazy { RetrofitHelper.getServer() }

    fun getSearchBooks(name: String, tag: String, start: Int, count: Int): Observable<BookDetail> {
        Log.e(TAG, "request params:name:$name tag:$tag start:$start count:$count")
        return retrofitService!!.getSearchBook(name, tag, start, count)
    }


}