package com.zhenquan.player.util

import com.zhenquan.doubanread.ui.group.GroupFragment
import com.zhenquan.doubanread.ui.home.HomeFragment
import com.zhenquan.doubanread.ui.profile.ProfileFragment
import com.zhenquan.doubanread.ui.status.StatusFragment
import com.zhenquan.doubanread.ui.subject.SubjectFragment
import com.zhenquan.player.base.BaseFragment


/**
 * ClassName:FragmentUtil
 * Description:管理fragment的util类
 */
class FragmentUtil private constructor() {
    //私有化构造方法
    val homeFragment by lazy { HomeFragment() }
    val groupFragment by lazy { GroupFragment() }
    val profileFragment by lazy { ProfileFragment() }
    val statusFragment by lazy { StatusFragment() }
    val subjectFragment by lazy { SubjectFragment() }

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