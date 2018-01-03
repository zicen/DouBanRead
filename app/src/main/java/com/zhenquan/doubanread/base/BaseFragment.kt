package com.zhenquan.doubanread.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhenquan.doubanread.base.function.UiFunction
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast
import rx.subscriptions.CompositeSubscription


/**
 * ClassName:BaseFragment
 * Description:所有fragment的基类
 */
abstract class BaseFragment : Fragment(), UiFunction {
    val requestComposite by lazy { CompositeSubscription() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onStop() {
        super.onStop()
        requestComposite.let {
            it.unsubscribe()
        }
    }

    /**
     * fragment初始化
     */
    open protected fun init() {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(getLayoutId(), container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //todo 这里统一处理页面状态
        initView(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()

    }


    /**
     * 数据的初始化
     */
    open protected fun initData() {

    }

    /**
     * adapter listener
     */
    open protected fun initListener() {

    }

    fun myToast(msg: String) {
        context.runOnUiThread { toast(msg) }
    }


}