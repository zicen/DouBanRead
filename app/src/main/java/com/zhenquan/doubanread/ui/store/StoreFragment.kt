package com.zhenquan.doubanread.ui.store

import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_store.*

/**
 * Created by 44162 on 2018/1/3.
 * @描述 书店
 */
class StoreFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_store
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