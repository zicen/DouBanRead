package com.zhenquan.doubanread.util

import android.app.AlertDialog
import android.content.Context
import com.zhenquan.doubanread.ui.activity.MainActivity
import android.widget.Toast
import android.content.DialogInterface


/**
 * Created by ry on 2018/2/26.
 */
object DialogUtil {
    fun showSureAndCancelDialog(context: Context, message: String, positionClickListener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setPositiveButton("确定", positionClickListener)
        builder.setNegativeButton("取消") { p0, p1 -> p0?.dismiss() }
        val ad = builder.create()
        ad.show()
    }
}