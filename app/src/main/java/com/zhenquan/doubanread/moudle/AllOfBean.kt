package com.zhenquan.doubanread.moudle

import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.util.ToastUtil

/**
 * Created by 44162 on 2018/1/5.
 * @描述
 */

data class AllOfBean(
        val payload: Payload,
        val success: Boolean, //true
        val msg: Any, //null
        val code: Int, //-1
        val timestamp: Int //1515160863
)

/**
 * 检测请求是否 success（该success 并非表示请求成功失败，仅表示后台处理成功）
 */
inline fun AllOfBean.checkSuccess(successAction: (bean: AllOfBean) -> Unit) {
    if (success) {
        successAction.invoke(this)
    } else {
        ToastUtil.imageToast(R.mipmap.ic_error, if (msg is String) msg.toString() else "errorCode: $code")
    }
}

data class Payload(
        val id: String, //e08b8c4368584d34b66d6acc08d32d08
        val username: String, //kalshen
        val password: String //123456
)