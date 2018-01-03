package com.zhenquan.doubanread.ui.classfiy

import android.content.res.AssetManager
import android.view.View
import com.google.gson.Gson
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseFragment
import com.zhenquan.doubanread.moudle.BookTag
import org.jetbrains.anko.support.v4.toast
import java.nio.charset.Charset
import com.google.gson.reflect.TypeToken




/**
 * Created by 44162 on 2018/1/3.
 * @描述 原创
 */
class ClassfiyFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_classfiy_work
    }

    override fun initView(rootView: View?) {
        val json = context.assets.fileAsString("json", "doubanreadtag.json")
    }

    fun AssetManager.fileAsString(subdirectory: String, filename: String): String {
        return open("$subdirectory/$filename").use {
            it.readBytes().toString(Charset.defaultCharset())
        }
    }


}


