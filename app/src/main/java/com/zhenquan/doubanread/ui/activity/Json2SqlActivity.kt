package com.zhenquan.doubanread.ui.activity

import android.content.res.AssetManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.moudle.BookTag
import com.zhenquan.doubanread.moudle.TagInfoBean
import com.zhenquan.doubanread.ui.App
import kotlinx.android.synthetic.main.activity_json2_sql.*
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

class Json2SqlActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_json2_sql
    }

    override fun initView(rootView: View?) {
        val tagDao = App.instance().getDaoSession().tagInfoBeanDao
        btn_create_sql.setOnClickListener {
            val json = assets.fileAsString("json", "doubanreadtag.json")
            val bookTag = Gson().fromJson(json, BookTag::class.java)
            Log.e("111","数据插入开始")
            for (item in bookTag.booktag) {
                var taginfobean = TagInfoBean()
                if (item.tag_name == "文学") {
                    taginfobean.parent_id = 10000
                } else if (item.tag_name == "流行") {
                    taginfobean.parent_id = 10001
                } else if (item.tag_name == "文化") {
                    taginfobean.parent_id = 10002
                } else if (item.tag_name == "生活") {
                    taginfobean.parent_id = 10003
                } else if (item.tag_name == "经管") {
                    taginfobean.parent_id = 10004
                } else if (item.tag_name == "科技") {
                    taginfobean.parent_id = 10005
                } else {
                    taginfobean.parent_id = 0
                }

                taginfobean.name = item.name
                taginfobean.update_time = getNowTime()
                taginfobean.create_time = getNowTime()
                tagDao.insertOrReplace(taginfobean)
            }
            Log.e("111","数据插入完成")

        }
        btn_see_sql.setOnClickListener {
            val loadAll = tagDao.loadAll()
            var sb = StringBuffer()
            for (item in loadAll) {
               sb.append(item.name+" ,")
            }
            tv_sql.text = sb.toString()
        }

    }

    fun AssetManager.fileAsString(subdirectory: String, filename: String): String {
        return open("$subdirectory/$filename").use {
            it.readBytes().toString(Charset.defaultCharset())
        }
    }

    fun getNowTime(): String? {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")//等价于now.toLocaleString()
        val curDate = Date(System.currentTimeMillis())//获取当前时间
        val str = formatter.format(curDate)
        Log.e("curtime", "curtime:" + str)
        return str
    }
}
