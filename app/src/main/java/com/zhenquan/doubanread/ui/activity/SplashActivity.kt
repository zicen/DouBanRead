package com.zhenquan.doubanread.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import android.view.animation.AlphaAnimation
import com.zhenquan.doubanread.moudle.LoginUserInfo
import com.zhenquan.doubanread.moudle.UserInfo
import com.zhenquan.doubanread.moudle.checkSuccess
import com.zhenquan.doubanread.net.RetrofitHelper
import com.zhenquan.doubanread.util.CheckUtil
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.fragment_user_login.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import retrofit2.Response
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class SplashActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView(rootView: View?) {
        startAnima(rootView)
        initUserInfo()
    }

    private fun startAnima(rootView: View?) {
        val ani = AlphaAnimation(0.2f, 1f)
        ani.duration = 3000
        rootView?.animation = ani
        ani.start()
    }

    private fun initUserInfo() {
        val userIsLogin = UserInfo.getUserIsLogin(this)
        if (userIsLogin) {
            val userLoginInfo = UserInfo.getUserLoginInfo(this)
            request(RetrofitHelper.takeIf {
                CheckUtil.isNotEmpty(userLoginInfo.username)
                        && CheckUtil.isNotEmpty(userLoginInfo.password)
            }?.getServerForAliYun()?.login(userLoginInfo.username, userLoginInfo.password)
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe { t: Response<LoginUserInfo>? ->
                        val get = t?.headers()?.get("Set-Cookie")
                        val split = get?.split(";")
                        val head = split?.get(0)
                        t?.body()?.checkSuccess {
                            //登录成功,保存登录信息到Sp
                            t.body().data.password = userLoginInfo.password
                            Log.e(TAG,"userinfo:"+t.body().data.toString())
                            UserInfo.saveUserLogin(this, t.body().data, true)
                            if (head != null) {
                                UserInfo.saveHeader(this, head)
                            }
                            EventBus.getDefault().post(t.body().data)
                            startActivity<MainActivity>()
                            this.finish()
                        }
                    }
            )
        } else {
            Log.e(TAG,"user is not login")
            startActivity<UserEntranceActivity>()
            this.finish()
        }
    }


}
