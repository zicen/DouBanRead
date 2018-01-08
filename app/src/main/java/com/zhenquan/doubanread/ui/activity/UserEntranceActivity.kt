package com.zhenquan.doubanread.ui.activity

import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.ui.fragment.UserEntranceFragment
import com.zhenquan.doubanread.ui.fragment.UserLoginFragment
import com.zhenquan.doubanread.ui.fragment.UserRegisterFragment
import kotlinx.android.synthetic.main.activity_user_entrance.*

/**
 * Created by 44162 on 2018/1/4.
 * @描述 用户登陆注册入口
 */
class UserEntranceActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_user_entrance

    }

    override fun initView(rootView: View?) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_entrance, UserEntranceFragment()).commit()
    }


    fun goLogin() {
        t_toolbar.title = "登陆"
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right)
                .replace(R.id.fl_entrance, UserLoginFragment())
                .addToBackStack(UserLoginFragment::class.java.simpleName)
                .commit()
    }

    fun goRegister() {
        t_toolbar.title = "注册"
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right)
                .replace(R.id.fl_entrance, UserRegisterFragment())
                .addToBackStack(UserRegisterFragment::class.java.simpleName)
                .commit()
    }


}
