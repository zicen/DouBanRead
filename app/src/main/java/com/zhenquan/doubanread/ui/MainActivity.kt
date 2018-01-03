package com.zhenquan.doubanread.ui

import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.view.View
import com.flyco.tablayout.listener.CustomTabEntity
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.base.BaseFragment
import com.zhenquan.doubanread.ui.local.LocalFragment
import com.zhenquan.doubanread.ui.mine.MineFragment
import com.zhenquan.doubanread.ui.original.OriginalWorkFragment
import com.zhenquan.doubanread.ui.recommendation.RecommendationFragment
import com.zhenquan.doubanread.ui.store.StoreFragment
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
            = arrayListOf(RecommendationFragment()
            , OriginalWorkFragment()
            , StoreFragment()
            , MineFragment()
            , LocalFragment())

    /**
     * 初始化 程序页面
     */
    private fun initAllPage() {
        //推荐
        val recommendationTab = MyTabEntity(getString(R.string.page_recommendation), R.mipmap.ic_tab_recommendation_active, R.mipmap.ic_tab_recommendation_normal)
        //原创
        val originalWorkTab = MyTabEntity(getString(R.string.page_original_work), R.mipmap.ic_tab_original_works_active, R.mipmap.ic_tab_original_works_normal)
        //书店
        val statusTab = MyTabEntity(getString(R.string.page_store), R.mipmap.ic_tab_store_active, R.mipmap.ic_tab_store_normal)
        //我的
        val mineTab = MyTabEntity(getString(R.string.page_mine), R.mipmap.ic_tab_mine_active, R.mipmap.ic_tab_mine_normal)
        //本地
        val localTab = MyTabEntity(getString(R.string.page_local), R.mipmap.ic_tab_local_active, R.mipmap.ic_tab_local_normal)

        ctl_bottom_nvg.setTabData(arrayListOf(recommendationTab, originalWorkTab, statusTab, mineTab, localTab)
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
