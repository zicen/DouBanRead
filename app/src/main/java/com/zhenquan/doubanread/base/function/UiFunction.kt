package com.zhenquan.doubanread.base.function

import android.support.annotation.DrawableRes
import android.view.View
import com.zhenquan.doubanread.util.ToastUtil
import org.jetbrains.anko.AnkoLogger
import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * Created by 44162 on 2018/1/3.
 * @描述 基本页面通用功能
 */
interface UiFunction : AnkoLogger {

    /**
     * 请求注册对象
     */
    val requestComposite: CompositeSubscription

    /**
     * 居中显示 ，并带有图片的toast
     */
    fun imgToast(@DrawableRes imgRes: Int, msg: String) {
        ToastUtil.imageToast(imgRes, msg)
    }

    /**
     * 获取布局id
     */
    fun getLayoutId(): Int

    /**
     * 初始化 布局
     */
    fun initView(rootView: View?)

    /**
     * 单个请求
     */
    fun request(s: Subscription?) {
        s?.let { requestComposite.add(it) }
    }

    fun requests(vararg s: Subscription) {
        //todo
    }


}
