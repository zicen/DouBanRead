package com.zhenquan.doubanread.ui.fragment

import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseFragment
import com.zhenquan.doubanread.moudle.BasicResponseInfo
import com.zhenquan.doubanread.moudle.checkSuccess
import com.zhenquan.doubanread.net.RetrofitHelper
import com.zhenquan.doubanread.util.CheckUtil
import kotlinx.android.synthetic.main.fragment_user_register.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by 44162 on 2018/1/4.
 * @描述
 */
class UserRegisterFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_user_register
    }

    override fun initView(rootView: View?) {

    }

    override fun initListener() {
        stv_register.setOnClickListener {

            val username = et_auth_username.text.trim().toString()
            val password = et_auth_password.text.trim().toString()
            val password2 = et_auth_password2.text.trim().toString()
            val email = et_auth_email.text.trim().toString()
            val phone = et_auth_phone.text.trim().toString()
            request(RetrofitHelper
                    .takeIf {
                        CheckUtil.isNotEmpty(et_auth_username)
                                && CheckUtil.isNotEmpty(et_auth_password)
                                && CheckUtil.isPassword(et_auth_password)
                                && CheckUtil.isSame(password,password2)
                    }
                    ?.getServerForAliYun()
                    ?.register(getParams(username,password,email,phone))
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe { t: BasicResponseInfo? ->
                        t?.checkSuccess {
                            imgToast(R.mipmap.ic_success, "注册成功")
                            activity.finish()
                        }
                    })
        }
    }



    private fun getParams(username: String, password: String, email: String, phone: String): Map<String, String> {
        var map = HashMap<String,String> ()
        map.put("username", username)
        map.put("password", password)
        map.put("email", email)
        map.put("phone", phone)
        return map
    }


}