package com.zhenquan.doubanread.ui.fragment

import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseFragment
import com.zhenquan.doubanread.moudle.AllOfBean
import com.zhenquan.doubanread.moudle.checkSuccess
import com.zhenquan.doubanread.net.RetrofitHelper
import com.zhenquan.doubanread.util.CheckUtil
import kotlinx.android.synthetic.main.fragment_user_login.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by 44162 on 2018/1/4.
 * @描述 用户登陆
 */
class UserLoginFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_user_login
    }

    override fun initView(rootView: View?) {

    }

    override fun initListener() {

        stv_login.setOnClickListener {

            val username = et_auth_username.text.trim().toString()
            val password = et_auth_password.text.trim().toString()

            request(RetrofitHelper
                    .takeIf {
                        CheckUtil.isNotEmpty(et_auth_username)
                                && CheckUtil.isNotEmpty(et_auth_password)
                                && CheckUtil.isPassword(et_auth_password)
                    }
                    ?.getServerForAliYun()?.authLogin(username, password)
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe { bean: AllOfBean? ->
                        bean?.checkSuccess {
                            imgToast(R.mipmap.ic_success, "登陆成功")
                            activity.finish()
                        }
                    }
            )

        }
    }
}
