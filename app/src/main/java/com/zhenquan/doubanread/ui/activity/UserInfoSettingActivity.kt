package com.zhenquan.doubanread.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.moudle.*
import com.zhenquan.doubanread.net.RetrofitHelper
import com.zhenquan.doubanread.util.TimeUtil
import com.zhenquan.doubanread.util.ToastUtil
import kotlinx.android.synthetic.main.activity_user_info_setting.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.find
import org.jetbrains.anko.image
import org.jetbrains.anko.imageResource
import org.jsoup.helper.StringUtil
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class UserInfoSettingActivity : BaseActivity() {
    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    var userInfo: UserInfo? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_user_info_setting
    }

    override fun initView(rootView: View?) {
        initToolBar()
        //清除EditText焦点
        clearFoucus()
        //获取个人信息详情
        stv_login.setOnClickListener {
            saveUserInfo()
        }

    }

    private fun clearFoucus() {
        edit_userinfo_email.clearFocus()
        edit_userinfo_cell.clearFocus()
        edit_userinfo_sex.clearFocus()
//        edit_userinfo_birth.clearFocus()
        edit_userinfo_intro.clearFocus()
    }

    private fun saveUserInfo() {
        userInfo?.let {
            var params = HashMap<String, String>()
            if (!StringUtil.isBlank(edit_userinfo_email.text.toString())) {
                params.put("email", edit_userinfo_email.text.toString())
                userInfo!!.email = edit_userinfo_email.text.toString()
            } else {
                params.put("email", userInfo!!.email)
            }
            if (!StringUtil.isBlank(edit_userinfo_cell.text.toString())) {
                params.put("phone", edit_userinfo_cell.text.toString())
                userInfo!!.phone = edit_userinfo_cell.text.toString()
            } else {
                params.put("phone", userInfo!!.phone)
            }
            if (!StringUtil.isBlank(edit_userinfo_intro.text.toString())) {
                params.put("intro", edit_userinfo_intro.text.toString())
                userInfo!!.intro = edit_userinfo_intro.text.toString()
            } else {
                params.put("intro", userInfo!!.intro)
            }
            request(RetrofitHelper
                    .getServerForAliYun()?.updateUserInfo(params)
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe { t: BasicResponseInfo ->
                        t?.checkSuccess {
                            //更新成功
                            EventBus.getDefault().post(userInfo)
                            ToastUtil.imageToast(R.mipmap.ic_success, "更新成功！")
                            finish()
                        }
                    }
            )
        }

    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "个人信息修改"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initData() {
        showWaitDialog()
        request(RetrofitHelper
                .getServerForAliYun()?.getUserInfo()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { t: retrofit2.Response<LoginUserInfo> ->
                    hideWaitDialog()
                    t?.body()?.checkSuccess {
                        //成功
                        userInfo = t.body().data
                        setView(userInfo)
                    }
                }
        )
    }

    private fun setView(userInfo: UserInfo?) {
        edit_userinfo_email.hint = userInfo?.email
        edit_userinfo_cell.hint = userInfo?.phone
        tv_userinfo_username.text = userInfo?.username
        if (userInfo?.sex == 0) {
            edit_userinfo_sex.hint = "男"
            civ_userinfo_avatar.imageResource = R.mipmap.avatar1
        } else {
            edit_userinfo_sex.hint = "女"
            civ_userinfo_avatar.imageResource = R.mipmap.avatar3
        }
//        edit_userinfo_birth.hint = userInfo?.birthday
        edit_userinfo_intro.hint = userInfo?.intro
        tv_userinfo_createtime.text = TimeUtil.getFormatTime(userInfo?.createTime)
        tv_userinfo_updatetime.text = TimeUtil.getFormatTime(userInfo?.updateTime)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
