package com.zhenquan.doubanread.ui.recommendation

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.squareup.picasso.Picasso
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseFragment
import com.zhenquan.doubanread.base.adapter.BaseRecyclerAdapter
import com.zhenquan.doubanread.base.adapter.SmartViewHolder
import com.zhenquan.doubanread.moudle.Book
import com.zhenquan.doubanread.moudle.LocalBookInfo
import com.zhenquan.doubanread.moudle.RecommendBookInfo
import com.zhenquan.doubanread.ui.classfiy.BookDetailActivity
import com.zhenquan.doubanread.ui.classfiy.BookTagListActivity
import com.zhenquan.doubanread.ui.classfiy.ClassfiyAdapter
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.onUiThread
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.support.v4.uiThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

/**
 * Created by 44162 on 2018/1/2.
 * @描述 推荐
 */
class RecommendationFragment : BaseFragment() {
    val adapter: BaseRecyclerAdapter<RecommendBookInfo> by lazy {
        object : BaseRecyclerAdapter<RecommendBookInfo>(R.layout.item_recommend_book_list) {
            override fun onBindViewHolder(holder: SmartViewHolder, model: RecommendBookInfo, position: Int) {
                holder.text(R.id.tv_recommend_book_name, model.title)
                holder.text(R.id.tv_recommend_book_author, model.author)
                Picasso.with(holder.itemView.context).load(model.image).into(holder.image(R.id.iv_recommend_book_list))
                holder.itemView.setOnClickListener {
                    val intent = Intent()
                    intent.setClass(holder.itemView.context, BookDetailActivity::class.java)
                    intent.putExtra("title", model.title)
                    intent.putExtra("id", model.id)
                    startActivity(intent)
                }
            }
        }
    }

    //爬取页面https://book.douban.com/
    override fun getLayoutId(): Int {
        return R.layout.fragment_recommendation
    }

    val regexNumber by lazy { Regex("[0-9]+") }
    override fun initView(rootView: View?) {
        val fragment_recommend_recycle = rootView?.find<RecyclerView>(R.id.fragment_recommend_recycle)
        fragment_recommend_recycle?.layoutManager = GridLayoutManager(activity, 3)
        fragment_recommend_recycle?.adapter = adapter
        var dataList = ArrayList<RecommendBookInfo>()
        doAsync {
            //后台执行代码
            try {
                //从一个URL加载一个Document对象。
                val doc = Jsoup.connect("https://book.douban.com/").get()
                //选择“美食天下”所在节点
                val e = doc.select("div.carousel")
                val lists = e.select("ul")
                for (list in lists) {
                    val lilist = list.select("li")
                    for (li in lilist) {
                        var book = RecommendBookInfo()
                        val convers = li.select("div.cover")
                        val bookauthor = li.select("span.author").text()
                        val publisher = li.select("span.publisher").text()
                        val title = convers.select("a").attr("title")
                        val image = convers.select("img").attr("src")
                        val detailUrl = convers.select("a").attr("href")
                        val jianjie = li.select("p.abstract").text()
                        book.title = title
                        book.image = image
                        book.publisher = publisher
                        book.author = bookauthor
                        book.jianjie = jianjie
                        book.detailUrl = detailUrl
                        val id = regexNumber.find(detailUrl)?.value
                        val toInt = id?.toInt()
                        Log.e("mytag", "id:" + id + "toInt:" + toInt)
                        toInt?.let {
                            book.id = it
                        }
                        dataList.add(book)
                    }

                }
            } catch (e: Exception) {
                Log.e("mytag", e.message)
            }
            onUiThread {
                //UI线程
                adapter.refresh(dataList)
            }

        }

    }
}