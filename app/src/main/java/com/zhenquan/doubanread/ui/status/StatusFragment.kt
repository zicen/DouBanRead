package com.zhenquan.doubanread.ui.status

import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.player.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_status.*

/**
 * Created by 44162 on 2018/1/3.
 * @描述 广播
 */
class StatusFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_status
    }

    override fun initView(rootView: View?) {
        fragment_staus_refreshLayout?.setOnRefreshListener { refreshlayout ->
            refreshlayout?.finishRefresh(2000/*,false*/)//传入false表示刷新失败
        }
        fragment_staus_refreshLayout?.setOnLoadmoreListener { refreshlayout ->
            refreshlayout.finishLoadmore(2000/*,false*/)//传入false表示加载失败
        }
    }


}