package com.zhenquan.doubanread.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import org.jetbrains.anko.find

class UserInfoSettingActivity : BaseActivity() {
    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun getLayoutId(): Int {
        return R.layout.activity_user_info_setting
    }

    override fun initView(rootView: View?) {
        initToolBar()
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "个人信息修改"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
