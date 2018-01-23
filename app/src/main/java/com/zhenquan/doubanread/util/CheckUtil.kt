package com.zhenquan.doubanread.util

import android.graphics.Color
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern


/**
 * Created by 44162 on 2018/1/6.
 * @描述
 */

object CheckUtil {


    /**
     * 判断是否为手机号码
     */
    fun isMobileNO(mobiles: String): Boolean {

        val p = Pattern

                .compile("^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$")

        val m = p.matcher(mobiles)

        return m.matches()

    }


    fun isMobileNO(mobiles: EditText, errorHint: String = "请填写正确的手机号"): Boolean {
        val trim = mobiles.text.toString().trim { it <= ' ' }
        val p = Pattern

                .compile("^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$")

        val m = p.matcher(trim)

        if (m.matches()) {
            return true
        } else {
            mobiles.text.clear()
            mobiles.setHintTextColor(Color.RED)
            mobiles.hint = errorHint
            return false
        }

    }


    /**
     * 判断是否符合密码条件
     * @param password 密码
     */
    fun isPassword(password: String, minLength: Int = 6, maxLength: Int = 16): Boolean {

        val p = Pattern

                .compile("^[a-zA-Z0-9]{$minLength,$maxLength}$")

        val m = p.matcher(password)

        return m.matches()

    }

    /**
     * 判断是否符合密码条件
     * @param editText 输入密码的 EditText
     */

    fun isPassword(editText: EditText, errorHint: String = "密码位6-16位字符", minLength: Int = 6, maxLength: Int = 16): Boolean {

        val p = Pattern

                .compile("^[a-zA-Z0-9]{$minLength,$maxLength}$")

        if (editText.text.toString().trim { it <= ' ' }.length < minLength) {
            Toast.makeText(editText.context, "密码至少6字符以上", Toast.LENGTH_SHORT).show()
            return false
        }

        val m = p.matcher(editText.text.toString().trim { it <= ' ' })

        val matches = m.matches()

        if (!matches) {
            Toast.makeText(editText.context, "密码不能超过16字符", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    fun isPasswordConfirm(password: EditText, passwordConfirm: EditText): Boolean {
        return if (TextUtils.equals(password.text.trim().toString(), passwordConfirm.text.trim().toString())) {
            true
        } else {
            Toast.makeText(password.context, "两次密码不一致", Toast.LENGTH_SHORT).show()
            false
        }
    }

    /*判断是否为邮箱*/
    fun isEmail(email: String): Boolean {

        val str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"

        val p = Pattern.compile(str)

        val m = p.matcher(email)

        return m.matches()

    }

    fun isEmail(email: EditText): Boolean {
        val trim = email.text.toString().trim { it <= ' ' }
        val str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"

        val p = Pattern.compile(str)

        val m = p.matcher(trim)
        if (m.matches()) {
            return true
        } else {
            email.setHintTextColor(Color.RED)
            email.setText("")
            email.hint = "请输入正确的邮箱"
            return false
        }

    }

    fun isSame(str: String,str2:String ):Boolean{
        return str == str2
    }
    /*判断是否全是数字*/
    fun isNumeric(str: String): Boolean {

        val pattern = Pattern.compile("[0-9]*")

        val isNum = pattern.matcher(str)

        if (!isNum.matches()) {

            return false

        }

        return true

    }


    fun isNotEmpty(editText: EditText): Boolean {
        val trim = editText.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(trim)) {
            editText.setHintTextColor(Color.RED)
            editText.hint = "输入不为空,且不含空格"
            return false
        } else {
            return true
        }
    }


}
