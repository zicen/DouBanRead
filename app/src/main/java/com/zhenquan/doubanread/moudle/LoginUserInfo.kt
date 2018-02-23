package com.zhenquan.doubanread.moudle

import android.content.Context
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.util.ToastUtil


data class LoginUserInfo(
        val status: Int, //0
        val msg: String,
        val data: UserInfo
)

data class UserInfo(
        val id: Int, //12
        val username: String, //aaa
        val email: String, //aaa@163.com
        val phone: String, //null
        val role: Int, //0
        val createTime: Long, //1479048325000
        val updateTime: Long //1479048325000
) {
    companion object {


        /**
         * 获取用户信息
         */
        fun getUserLoginInfo(context: Context): UserInfo {
            val userLogin = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
            var loginInfo = UserInfo(userLogin.getInt("id", -1), userLogin.getString("username", ""),
                    userLogin.getString("email", ""), userLogin.getString("phone", ""), userLogin.getInt("role", -1)
                    , userLogin.getLong("createTime", -1), userLogin.getLong("updateTime", -1))
            return loginInfo

        }

        /**
         * 获取是否登录
         */
        fun getUserIsLogin(context: Context):Boolean{
            val userLogin = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
            return userLogin.getBoolean("isLogin",false)
        }
        /**
         * 保存用户的登录信息
         * @param context  上下文
         * @param login  用户登录信息
         * @param isLogin  是否是登录操作
         */
        fun saveUserLogin(context: Context, login: UserInfo?, isLogin: Boolean) {
            if (login == null) {
                return
            }
            val userInfo = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE).edit()
            userInfo.putInt("id", login.id)
            userInfo.putString("username", login.username)
            userInfo.putString("email", login.email)
            userInfo.putString("phone", login.phone)
            userInfo.putInt("role", login.role)
            userInfo.putLong("createTime", login.createTime)
            userInfo.putLong("updateTime", login.updateTime)
            userInfo.putBoolean("isLogin", isLogin)
            userInfo.commit()
        }
    }
}

/**
 * 检测请求是否 success（该success 并非表示请求成功失败，仅表示后台处理成功）
 */
inline fun LoginUserInfo.checkSuccess(successAction: (bean: LoginUserInfo) -> Unit) {
    if (status == 0) {
        successAction.invoke(this)
    } else {
        ToastUtil.imageToast(R.mipmap.ic_error, if (msg is String) msg.toString() else "errorCode: $status")
    }
}


data class BasicResponseInfo(
        val status: Int, //0
        val msg: String
)

inline fun BasicResponseInfo.checkSuccess(successAction: (bean: BasicResponseInfo) -> Unit) {
    if (status == 0) {
        successAction.invoke(this)
    } else {
        ToastUtil.imageToast(R.mipmap.ic_error, if (msg is String) msg.toString() else "errorCode: $status")
    }
}

