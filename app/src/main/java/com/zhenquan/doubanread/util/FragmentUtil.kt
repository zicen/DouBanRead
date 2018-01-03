package com.zhenquan.doubanread.util

import com.zhenquan.doubanread.base.BaseFragment
import com.zhenquan.doubanread.ui.mine.MineFragment
import com.zhenquan.doubanread.ui.recommendation.RecommendationFragment
import com.zhenquan.doubanread.ui.local.LocalFragment
import com.zhenquan.doubanread.ui.store.StoreFragment
import com.zhenquan.doubanread.ui.original.OriginalWorkFragment


/**
 * ClassName:FragmentUtil
 * Description:管理fragment的util类
 */
class FragmentUtil private constructor() {
    //私有化构造方法
    val homeFragment by lazy { RecommendationFragment() }
    val groupFragment by lazy { MineFragment() }
    val profileFragment by lazy { LocalFragment() }
    val statusFragment by lazy { StoreFragment() }
    val subjectFragment by lazy { OriginalWorkFragment() }

    companion object {
        val fragmentUtil by lazy { FragmentUtil() }
    }

    /**
     * 根据tabid获取对应的fragment
     */
    fun getFragment(tabId: String): BaseFragment? {
        when (tabId) {
            "home" -> return homeFragment
            "mine" -> return groupFragment
            "profile" -> return profileFragment
            "status" -> return statusFragment
            "subject" -> return subjectFragment
        }
        return null
    }
}