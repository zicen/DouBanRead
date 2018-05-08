package com.zhenquan.doubanread.ui.activity

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import com.flyco.tablayout.listener.CustomTabEntity
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.base.BaseFragment
import com.zhenquan.doubanread.ui.classfiy.ClassfiyFragment
import com.zhenquan.doubanread.ui.local.CircleFragment
import com.zhenquan.doubanread.ui.mine.MineFragment
import com.zhenquan.doubanread.ui.recommendation.RecommendationFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class MainActivity : BaseActivity() {


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(rootView: View?) {
    }

    override fun initData() {
        super.initData()
        //初始化所有的fragment界面
        initAllPage()
        //初始化 toolbar
        initToolBar()
    }

    /**
     * 初始化 并配置 toolbar
     */
    private fun initToolBar() {
        setSupportActionBar(t_toolbar)
        action_search.setOnClickListener {
            startActivity<SearchBookActivity>()
        }
    }

    /**
     * 主界面对用功能的fragment
     */
    private val mainFragments: ArrayList<Fragment>
            = arrayListOf(RecommendationFragment()
            , ClassfiyFragment()
            , MineFragment()
            , CircleFragment())

    /**
     * 初始化 程序页面
     */
    private fun initAllPage() {
        //推荐
        val recommendationTab = MyTabEntity(getString(R.string.page_recommendation), R.mipmap.ic_tab_recommendation_active, R.mipmap.ic_tab_recommendation_normal)
        //分类
        val originalWorkTab = MyTabEntity(getString(R.string.page_classfiy_work), R.mipmap.ic_tab_original_works_active, R.mipmap.ic_tab_original_works_normal)
        //我的
        val mineTab = MyTabEntity(getString(R.string.page_mine), R.mipmap.ic_tab_mine_active, R.mipmap.ic_tab_mine_normal)

        ctl_bottom_nvg.setTabData(arrayListOf(recommendationTab, originalWorkTab, mineTab)
                , this@MainActivity
                , R.id.fl_main_content
                , mainFragments)
    }


    /**
     * 获取主界面所有的fragment
     */
    fun getTabFragments(): ArrayList<Fragment> {
        return mainFragments
    }

    /**
     * 获取主界面 具体对应的fragment
     */
    inline fun <reified T : BaseFragment> getTabFragment(clazz: Class<T>): T? {
        return getTabFragments().find { it.javaClass == clazz }?.let { it as T }
    }


    override fun initListener() {
    }

    private class MyTabEntity(val customTabTitle: String, @DrawableRes val customTabSelectedIcon: Int, @DrawableRes val customTabUnSelectedIcon: Int) : CustomTabEntity {
        override fun getTabTitle(): String {
            return customTabTitle
        }

        override fun getTabSelectedIcon(): Int {
            return customTabSelectedIcon
        }

        override fun getTabUnselectedIcon(): Int {
            return customTabUnSelectedIcon
        }
    }

}
