package com.zhenquan.doubanread.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.moudle.LoginUserInfo
import com.zhenquan.doubanread.moudle.ReadListBean
import com.zhenquan.doubanread.moudle.checkSuccess
import com.zhenquan.doubanread.net.RetrofitHelper
import com.zhenquan.doubanread.util.ToastUtil
import org.jetbrains.anko.find
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class UserInfoSettingActivity : BaseActivity() {
    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun getLayoutId(): Int {
        return R.layout.activity_user_info_setting
    }

    override fun initView(rootView: View?) {
        initToolBar()
//        获取个人信息详情
        //todo


    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "个人信息修改"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun initData() {
        request(RetrofitHelper
                .getServerForAliYun()?.getUserInfo()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { t:retrofit2.Response<LoginUserInfo>->
                    t?.body()?.checkSuccess {
                        //成功

                    }
                }
        )
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
