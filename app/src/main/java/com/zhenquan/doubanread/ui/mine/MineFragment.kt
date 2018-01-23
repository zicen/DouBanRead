package com.zhenquan.doubanread.ui.mine

import android.util.Log
import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseFragment
import com.zhenquan.doubanread.moudle.UserInfo
import com.zhenquan.doubanread.moudle.bookinfo.RecommendBookInfo
import com.zhenquan.doubanread.ui.recommendation.BookMultipleItem
import kotlinx.android.synthetic.main.fragment_mine.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.doAsync
import org.jsoup.Jsoup
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe



/**
 * Created by 44162 on 2018/1/3.
 * @描述 我的
 */
class MineFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView(rootView: View?) {
        EventBus.getDefault().register(this)
        if (UserInfo.getUserIsLogin(context)) {
            val userLoginInfo = UserInfo.getUserLoginInfo(context)
            tv_mine_username.text = userLoginInfo.username
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handleEventBus(userInfo: UserInfo) {
        tv_mine_username.text = userInfo.username
    }
    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().register(this)
    }
}