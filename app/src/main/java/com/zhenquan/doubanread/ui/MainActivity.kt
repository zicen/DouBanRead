package com.zhenquan.doubanread.ui

import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.view.View
import com.flyco.tablayout.listener.CustomTabEntity
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.ui.group.GroupFragment
import com.zhenquan.doubanread.ui.home.HomeFragment
import com.zhenquan.doubanread.ui.profile.ProfileFragment
import com.zhenquan.doubanread.ui.status.StatusFragment
import com.zhenquan.doubanread.ui.subject.SubjectFragment
import com.zhenquan.player.base.BaseActivity
import com.zhenquan.player.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(rootView: View?) {

    }

    override fun initData() {
        super.initData()
        initAllPage()
    }

    /**
     * 主界面对用功能的fragment
     */
    private val mainFragments: ArrayList<Fragment>
            = arrayListOf(HomeFragment()
            , SubjectFragment()
            , StatusFragment()
            , GroupFragment()
            , ProfileFragment())

    /**
     * 初始化 程序页面
     */
    private fun initAllPage() {
        //首页
        val homeTab = MyTabEntity(getString(R.string.page_home), R.mipmap.ic_tab_home_active, R.mipmap.ic_tab_home_normal)
        //书影音
        val subjectTab = MyTabEntity(getString(R.string.page_subject), R.mipmap.ic_tab_subject_active, R.mipmap.ic_tab_subject_normal)
        //广播
        val statusTab = MyTabEntity(getString(R.string.page_status), R.mipmap.ic_tab_status_active, R.mipmap.ic_tab_status_normal)
        //小组
        val groupTab = MyTabEntity(getString(R.string.page_group), R.mipmap.ic_tab_group_active, R.mipmap.ic_tab_group_normal)
        //我的
        val profileTab = MyTabEntity(getString(R.string.page_profile), R.mipmap.ic_tab_profile_active, R.mipmap.ic_tab_profile_normal)

        ctl_bottom_nvg.setTabData(arrayListOf(homeTab, subjectTab, statusTab, groupTab, profileTab)
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

        /* btn.setOnClickListener {
             simpleRequest()
         }
         btn2.setOnClickListener {
             //以后请求就用这个
             sampleRequest()
         }

 */
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

    /* private fun sampleRequest() {
         requestComposite.add(DataManager().getSearchBooks("金瓶梅", "", 0, 1)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(object : Observer<BookDetail> {
                     override fun onNext(t: BookDetail?) {
                         tv.text = t.toString()
                     }

                     override fun onCompleted() {
                     }

                     override fun onError(e: Throwable?) {
                         e?.printStackTrace()
                     }

                 })

         )


     }

     private fun simpleRequest() {
         var retrofit = Retrofit.Builder()
                 .baseUrl("https://api.douban.com/v2/")
                 .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                 .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava
                 .build()
         val service = retrofit.create(RetrofitService::class.java)
         var observable = service.getSearchBook("金瓶梅", "", 0, 1)
         observable.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(object : Observer<BookDetail> {
                     override fun onNext(t: BookDetail?) {
                         tv.text = t.toString()
                     }

                     override fun onCompleted() {

                     }

                     override fun onError(e: Throwable?) {
                         e?.printStackTrace()
                     }

                 })
     }*/
}
