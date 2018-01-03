package com.zhenquan.doubanread.ui

import android.app.Application
import com.zhenquan.doubanread.util.Utils

/**
 * Created by Administrator on 2017/12/30 0030.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //工具类初始化
        Utils.init(this@App)
    }
}