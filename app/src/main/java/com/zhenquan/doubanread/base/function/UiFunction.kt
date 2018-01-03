package com.zhenquan.doubanread.base.function

import android.support.annotation.DrawableRes
import android.view.View
import com.zhenquan.doubanread.util.ToastUtil
import com.zhenquan.doubanread.util.Utils
import org.jetbrains.anko.AnkoLogger

/**
 * Created by 44162 on 2018/1/3.
 * @描述 基本页面通用功能
 */
interface UiFunction : AnkoLogger {

    /**
     * 居中显示 ，并带有图片的toast
     */
    fun imgToast(@DrawableRes imgRes: Int, msg: String) {
        ToastUtil.imageToast(Utils.getContext(), imgRes, msg)
    }

    /**
     * 获取布局id
     */
    fun getLayoutId(): Int

    /**
     * 初始化 布局
     */
    fun initView(rootView: View?)


}
