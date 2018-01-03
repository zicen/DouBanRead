package com.yoofn.nbiot.utils

import android.content.Context
import android.support.annotation.DrawableRes
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

/**
 *
 * Created by kalshen on 2017/7/30 0030.
 * @描述
 */
object ToastUtil {

    // 2017/12/1  因为业务需求 将toast内容相同显示的不再提示
    /** Toast对象  */
    private var imageToast: Toast? = null
    private var systemToast: Toast? = null
    /** 第一次时间  */
    private var oneTime: Long = 0
    /** 第二次时间  */
    private var twoTime: Long = 0
    /** 之前显示的内容  */
    private var oldMsg: String? = null


    fun imageToast(context: Context, @DrawableRes imgRes: Int, msg: String? = "auth success") {
        if (imageToast == null) {
            imageToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            imageToast?.let {
                setContent(it, context, imgRes)
                it.show()
                oneTime = System.currentTimeMillis()
            }

        } else {
            twoTime = System.currentTimeMillis()
            if (msg.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    imageToast?.show()
                }
            } else {
                oldMsg = msg
                imageToast?.let {
                    ((it.view as LinearLayout).getChildAt(0) as ImageView).setImageResource(imgRes)
                    it.setText(msg)
                }
                imageToast?.show()
            }
        }

    }

    fun systemToast(context: Context, msg: String) {
        if (systemToast == null) {
            systemToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            systemToast?.let {
                it.show()
                oneTime = System.currentTimeMillis()
            }

        } else {
            twoTime = System.currentTimeMillis()
            if (msg.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    systemToast?.show()
                }
            } else {
                oldMsg = msg
                systemToast?.let {
                    it.setText(msg)
                }
                systemToast?.show()
            }
        }
    }

    private fun setContent(it: Toast, context: Context, imgRes: Int) {
        it.setGravity(Gravity.CENTER, 0, 0)
        (it.view as LinearLayout).run {
            val textView = getChildAt(0)
            (textView as TextView).width = dip2px(context, 400f)
            textView.gravity = Gravity.CENTER
            textView.setPadding(dip2px(context, 20f), dip2px(context, 20f), dip2px(context, 20f), dip2px(context, 20f))
            setPadding(0, dip2px(context, 20f), 0, 0)
            addView(ImageView(context).apply {
                setImageResource(imgRes)
                gravity = Gravity.CENTER
                layoutParams = LinearLayout.LayoutParams(dip2px(context, 30f), dip2px(context, 30f))
            }, 0)
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    private fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }
}