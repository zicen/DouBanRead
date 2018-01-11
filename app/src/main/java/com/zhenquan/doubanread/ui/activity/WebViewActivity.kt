package com.zhenquan.doubanread.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.scwang.smartrefresh.layout.util.DensityUtil
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import kotlinx.android.synthetic.main.activity_web_view.*
import org.jetbrains.anko.find
import java.util.*

class WebViewActivity : BaseActivity() {
    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun getLayoutId(): Int {
        return R.layout.activity_web_view
    }

    override fun initView(rootView: View?) {

    }

    override fun initListener() {
        val title = initToolBar()
        val url = intent.getStringExtra("detailUrl")
        webview_refreshLayout.setOnRefreshListener({
            webView.loadUrl(url)
        })
        webview_refreshLayout.autoRefresh()
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                webview_refreshLayout.finishRefresh()
                view.loadUrl(String.format(Locale.CHINA, "javascript:document.body.style.paddingTop='%fpx'; void 0", DensityUtil.px2dp(webView.paddingTop.toFloat())))
            }
        }
    }


    private fun initToolBar(): String? {
        val title = intent.extras.getString("title")
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
