package com.zhenquan.doubanread.ui.activity

import android.app.ProgressDialog
import android.content.res.AssetManager
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.manager.DataManager
import com.zhenquan.doubanread.moudle.Book
import com.zhenquan.doubanread.moudle.BookTag
import com.zhenquan.doubanread.moudle.SearchBookList
import com.zhenquan.doubanread.moudle.TagInfoBean
import com.zhenquan.doubanread.ui.App
import kotlinx.android.synthetic.main.activity_json2_sql.*
import org.jetbrains.anko.toast
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * 保存文件到sql中的程序
 */
class Json2SqlActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_json2_sql
    }

    val pd by lazy { ProgressDialog(this) }

    override fun initView(rootView: View?) {
        val tagDao = App.instance().getDaoSession().tagInfoBeanDao
        /**
         * 获取保存tag信息
         */
        btn_create_sql.setOnClickListener {
            val json = assets.fileAsString("json", "doubanreadtag.json")
            val bookTag = Gson().fromJson(json, BookTag::class.java)
            pd.show()
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
            pd.dismiss()

        }
        btn_clear.setOnClickListener {
            tagDao.deleteAll()
        }
        btn_see_sql.setOnClickListener {
            val loadAll = tagDao.loadAll()
            var sb = StringBuffer()
            for (item in loadAll) {
                sb.append(item.name + " ,")
            }
            val toJson = Gson().toJson(loadAll)
            val file = File(filesDir.absolutePath,"tag.json")
            file.writeText(toJson)
            toast(sb.toString())
        }
        /**
         * 获取保存书籍信息
         */
        btn_getBook.setOnClickListener {
            val loadAll = tagDao.loadAll()
            pd.show()
            getData(loadAll[0].name!!, start, loadAll[0].id!!)
            Log.e(TAG, " 获取保存书籍信息完成" + lists.size)
            pd.dismiss()
        }

    }

    fun AssetManager.fileAsString(subdirectory: String, filename: String): String {
        return open("$subdirectory/$filename").use {
            it.readBytes().toString(Charset.defaultCharset())
        }
    }

    /**
     * 获取当前时间
     */
    fun getNowTime(): String? {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")//等价于now.toLocaleString()
        val curDate = Date(System.currentTimeMillis())//获取当前时间
        val str = formatter.format(curDate)
        Log.e("curtime", "curtime:" + str)
        return str
    }

    var start = 0
    var lists = ArrayList<Book>()
    private fun getData(title: String, start_getdata: Int, categoryid: Long) {
        var hasmore = true
        requestComposite.add(DataManager().getSearchBooks("", title, start_getdata, 500)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<SearchBookList> {

                    override fun onNext(t: SearchBookList?) {
                        t?.books?.let {
                            it.forEach { book ->
                                book.categoryid = categoryid.toInt()
                            }
                            lists.addAll(t.books)
                            Log.e(TAG, "lists size:" + lists.size)
                            if (it.size < 100) {
                                Log.e(TAG, "数据全部加载完毕")
                                hasmore = false
                                return
                            } else {
                                hasmore = true
                                start += 100
                                getData(title, start, categoryid)
                            }
                        }

                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                    }

                })
        )
    }

}
