package com.zhenquan.doubanread.ui.mine

import android.content.DialogInterface
import android.util.Log
import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseFragment
import com.zhenquan.doubanread.moudle.BasicResponseInfo
import com.zhenquan.doubanread.moudle.LoginUserInfo
import com.zhenquan.doubanread.moudle.UserInfo
import com.zhenquan.doubanread.moudle.bookinfo.RecommendBookInfo
import com.zhenquan.doubanread.moudle.checkSuccess
import com.zhenquan.doubanread.net.RetrofitHelper
import com.zhenquan.doubanread.ui.activity.*
import com.zhenquan.doubanread.ui.recommendation.BookMultipleItem
import com.zhenquan.doubanread.util.CheckUtil
import com.zhenquan.doubanread.util.DialogUtil
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.fragment_user_login.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.doAsync
import org.jsoup.Jsoup
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.support.v4.startActivity
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by 44162 on 2018/1/3.
 * @描述 我的
 */
class MineFragment : BaseFragment(){


    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView(rootView: View?) {
        EventBus.getDefault().register(this)
        if (UserInfo.getUserIsLogin(context)) {
            val userLoginInfo = UserInfo.getUserLoginInfo(context)
            setLogin(userLoginInfo)
        } else {
            setNotLogin()
        }


    }
    private fun setNotLogin() {
        tv_mine_username.text = "登录/注册"
        tv_mine_intro.text="个性签名"
        UserInfo.clearUserInfo(context)
        ll_mine.setOnClickListener {
            startActivity<UserEntranceActivity>()
        }
        ll_mywantchange.setOnClickListener {  startActivity<UserEntranceActivity>()}
        ll_myrelease.setOnClickListener {  startActivity<UserEntranceActivity>() }
        ll_matches.setOnClickListener {  startActivity<UserEntranceActivity>() }
        ll_exit.setOnClickListener { imgToast(R.mipmap.ic_action_info, "请先登录!") }
    }

    private fun setLogin(userLoginInfo: UserInfo) {
        tv_mine_username.text = userLoginInfo.username
        tv_mine_intro.text = userLoginInfo.intro
        ll_mine.setOnClickListener {
            startActivity<UserInfoSettingActivity>()
        }
        ll_mywantchange.setOnClickListener { startActivity<MyWantChangeActivity>(Pair("title","想换的书")) }
        ll_myrelease.setOnClickListener { startActivity<MyReleaseActivity>(Pair("title","我发布的书")) }
        ll_matches.setOnClickListener { startActivity<MatchesActivity>(Pair("title","匹配列表")) }
        ll_exit.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        //showdialog
        DialogUtil.showSureAndCancelDialog(context,"确定要退出登录吗?",DialogInterface.OnClickListener { _, _ ->
            request(RetrofitHelper
                    .getServerForAliYun()?.logout()
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe { t: BasicResponseInfo? ->
                        t?.checkSuccess {
                            imgToast(R.mipmap.ic_success, "退出登录成功!")
                            setNotLogin()
                        }
                    }
            )
        })

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handleEventBus(userInfo: UserInfo) {
        setLogin(userInfo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }
}