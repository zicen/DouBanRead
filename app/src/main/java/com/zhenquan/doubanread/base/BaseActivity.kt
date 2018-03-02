package com.zhenquan.doubanread.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.function.UiFunction
import com.zhenquan.doubanread.wight.MyProgressDialog
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import rx.subscriptions.CompositeSubscription


/**
 * ClassName:BaseActivity
 * Description:所有activity的基类
 */
abstract class BaseActivity : AppCompatActivity(), UiFunction {
    val TAG = this.javaClass.simpleName
    override val requestComposite: CompositeSubscription
        get() = CompositeSubscription()
    private var progressDialog: MyProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        //todo 统一处理activity 页面状态
        initView(null)
        initListener()
        initData()
    }

    fun hideWaitDialog() {
        if ( progressDialog != null && isDialogShowing()) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }

    fun showWaitDialog() {
        if (!isDialogShowing()) {
            if (progressDialog == null) {
                progressDialog = MyProgressDialog(this)
            }
            progressDialog?.show()
        }
    }


    fun isDialogShowing(): Boolean {
        return if (progressDialog == null) false else progressDialog!!.isShowing
    }
    /**
     * 初始化数据
     */
    open protected fun initData() {

    }

    /**
     * adapter listener
     */
    open protected fun initListener() {

    }


    override fun onStop() {
        super.onStop()
        requestComposite.let {
            it.unsubscribe()
        }
        hideWaitDialog()
    }


    protected fun myToast(msg: String) {
        runOnUiThread { toast(msg) }
    }

    /**
     * 开启activity并且finish当前界面
     */
    inline fun <reified T : BaseActivity> startActivityAndFinish() {
        startActivity<T>()
        finish()
    }
}