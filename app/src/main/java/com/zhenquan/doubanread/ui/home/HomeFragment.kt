package com.zhenquan.doubanread.ui.home

import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.ui.MainActivity
import com.zhenquan.doubanread.ui.status.StatusFragment
import com.zhenquan.player.base.BaseFragment

/**
 * Created by 44162 on 2018/1/2.
 * @描述 首页
 */
class HomeFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(rootView: View?) {
        (activity as MainActivity).getTabFragment(StatusFragment::class.java)?.haha()
    }
}