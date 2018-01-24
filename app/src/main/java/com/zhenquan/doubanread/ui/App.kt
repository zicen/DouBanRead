package com.zhenquan.doubanread.ui

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.support.annotation.NonNull
import android.support.v7.app.AppCompatDelegate
import android.util.Log
import android.widget.Toast
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.*
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.wanjian.cockroach.Cockroach
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.util.DynamicTimeFormat
import com.zhenquan.doubanread.util.Utils
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import android.database.sqlite.SQLiteDatabase
import com.zhenquan.doubanread.greendao.gen.DaoSession
import com.zhenquan.doubanread.greendao.gen.DaoMaster
import com.zhenquan.doubanread.util.GreenDaoContext


/**
 * Created by Administrator on 2017/12/30 0030.
 */
class App : Application() {
    //static 代码段可以防止内存泄露
    companion object {
        private var instances: App? = null
        fun instance() = instances!!
    }

    override fun onCreate() {
        super.onCreate()
        instances = this

        //永不crash
        initCockroach()
        //设置全局字体
        initFont()
        //工具类初始化
        Utils.init(this@App)
        //数据库初始化
        setDatabase()
    }

    private var mHelper: DaoMaster.DevOpenHelper? = null
    private var db: SQLiteDatabase? = null
    private var mDaoMaster: DaoMaster? = null
    private var mDaoSession: DaoSession? = null
    fun setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = DaoMaster.DevOpenHelper(GreenDaoContext(this), "doubanread.db", null)
        db = mHelper!!.writableDatabase
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = DaoMaster(db)
        mDaoSession = mDaoMaster!!.newSession()

    }

    fun getDaoSession(): DaoSession {
        return mDaoSession!!
    }

    fun getDb(): SQLiteDatabase {
        return db!!
    }

    private fun initCockroach() {
        Cockroach.install { thread, throwable ->
            // handlerException内部建议手动try{  你的异常处理逻辑  }catch(Throwable e){ } ，以防handlerException内部再次抛出异常，导致循环调用handlerException

            //开发时使用Cockroach可能不容易发现bug，所以建议开发阶段在handlerException中用Toast谈个提示框，
            //由于handlerException可能运行在非ui线程中，Toast又需要在主线程，所以new了一个new Handler(Looper.getMainLooper())，
            //所以千万不要在下面的run方法中执行耗时操作，因为run已经运行在了ui线程中。
            //new Handler(Looper.getMainLooper())只是为了能弹出个toast，并无其他用途
            Handler(Looper.getMainLooper()).post(Runnable {
                try {
                    //建议使用下面方式在控制台打印异常，这样就可以在Error级别看到红色log
                    Log.e("AndroidRuntime", "--->CockroachException:$thread<---", throwable)
                    Toast.makeText(this@App, "Exception Happend\n" + thread + "\n" + throwable.toString(), Toast.LENGTH_SHORT).show()
                    //                        throw new RuntimeException("..."+(i++));
                } catch (e: Throwable) {

                }
            })
        }
    }

    private fun initFont() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}