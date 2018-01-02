package com.zhenquan.player.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import rx.subscriptions.CompositeSubscription


/**
 * ClassName:BaseActivity
 * Description:所有activity的基类
 */
abstract class BaseActivity: AppCompatActivity(),AnkoLogger {
     val requestComposite by lazy { CompositeSubscription() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initListener()
        initData()

    }

    /**
     * 初始化数据
     */
    open protected fun initData() {

    }

    /**
     * adapter listener
     */
    open protected fun initListener() {

    }

    override fun onStop() {
        super.onStop()
        requestComposite.let {
            it.unsubscribe()
        }
    }

    /**
     * 获取布局id
     */
    abstract fun getLayoutId(): Int

    protected  fun myToast(msg:String){
        runOnUiThread { toast(msg) }
    }

    /**
     * 开启activity并且finish当前界面
     */
    inline fun <reified T: BaseActivity> startActivityAndFinish(){
        startActivity<T>()
        finish()
    }
}