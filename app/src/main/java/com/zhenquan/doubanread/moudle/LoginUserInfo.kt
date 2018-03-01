package com.zhenquan.doubanread.moudle

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
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
        var password: String, //aaa
        var email: String, //aaa@163.com
        var phone: String, //null
        var role: Int, //0
        val createTime: Long, //1479048325000
        val updateTime: Long, //1479048325000

        var question: String,
        var answer: String,
        var sex: Int,
        var intro: String,
        var birthday: String,
        var avatar: String
) {
    companion object {


        /**
         * 获取用户信息
         */
        fun getUserLoginInfo(context: Context): UserInfo {
            val userLogin = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE)
            var loginInfo = UserInfo(userLogin.getInt("id", -1), userLogin.getString("username", ""),userLogin.getString("password","")
                    ,userLogin.getString("email", ""), userLogin.getString("phone", ""), userLogin.getInt("role", -1)
                    , userLogin.getLong("createTime", -1), userLogin.getLong("updateTime", -1), userLogin.getString("question", "")
                    , userLogin.getString("answer", ""), userLogin.getInt("sex", 0), userLogin.getString("intro", "")
                    , userLogin.getString("birthday", ""), userLogin.getString("avatar", ""))
            return loginInfo

        }

        /**
         * 获取是否登录
         */
        fun getUserIsLogin(context: Context): Boolean {
            val userLogin = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
            return userLogin.getBoolean("isLogin", false)
        }

        /**
         * 清除用户信息和登录信息
         */
        fun clearUserInfo(context: Context) {
            val userLogin = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE).edit()
            val head = context.getSharedPreferences("Header", Context.MODE_PRIVATE).edit()
            head.putString("JSESSIONID", "")
            userLogin.clear()
            head.clear()
        }

        /**
         * 保存用户的登录信息
         * @param context  上下文
         * @param login  用户登录信息
         * @param isLogin  是否是登录操作
         */
        fun saveUserLogin(context: Context, login: UserInfo?,isLogin: Boolean) {
            if (login == null) {
                return
            }
            val userInfo = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE).edit()
            userInfo.putInt("id", login.id)
            userInfo.putString("username", login.username)
            userInfo.putString("password", login.password)
            userInfo.putString("email", login.email)
            userInfo.putString("phone", login.phone)
            userInfo.putInt("role", login.role)
            userInfo.putLong("createTime", login.createTime)
            userInfo.putLong("updateTime", login.updateTime)
            userInfo.putInt("sex", login.sex)
            userInfo.putString("question", login.question)
            userInfo.putString("answer", login.answer)
            userInfo.putString("intro", login.intro)
            userInfo.putString("birthday", login.birthday)
            userInfo.putString("avatar", login.avatar)
            userInfo.putBoolean("isLogin", isLogin)
            userInfo.commit()
        }

        fun saveHeader(context: Context, header: String?) {
            val head = context.getSharedPreferences("Header", Context.MODE_PRIVATE).edit()
            head.putString("JSESSIONID", header)
            head.commit()
        }

        fun getHeader(context: Context): String {
            val head = context.getSharedPreferences("Header", Context.MODE_PRIVATE)
            return head.getString("JSESSIONID", "")
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

data class BasicResponseInfo2(
        val status: Int, //0
        val data: String
)

inline fun BasicResponseInfo.checkSuccess(successAction: (bean: BasicResponseInfo) -> Unit) {
    if (status == 0) {
        successAction.invoke(this)
    } else {
        ToastUtil.imageToast(R.mipmap.ic_error, if (msg is String) msg.toString() else "errorCode: $status")
    }
}

inline fun BasicResponseInfo2.checkSuccess(successAction: (bean: BasicResponseInfo2) -> Unit) {
    if (status == 0) {
        successAction.invoke(this)
    } else {
        ToastUtil.imageToast(R.mipmap.ic_error, if (data is String) data.toString() else "errorCode: $status")
    }
}

