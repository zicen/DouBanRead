package com.zhenquan.doubanread.ui.fragment

import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseFragment
import com.zhenquan.doubanread.ui.activity.UserEntranceActivity
import kotlinx.android.synthetic.main.fragment_user_entrance.*

/**
 * Created by 44162 on 2018/1/4.
 * @描述
 */
class UserEntranceFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_user_entrance
    }

    override fun initView(rootView: View?) {

    }

    override fun initListener() {
        stv_go_login.setOnClickListener {
            (activity as UserEntranceActivity).goLogin()
        }
        stv_go_register.setOnClickListener {
            (activity as UserEntranceActivity).goRegister()
        }
    }
}