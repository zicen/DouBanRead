package com.zhenquan.doubanread.ui.recommendation

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseFragment
import com.zhenquan.doubanread.moudle.bookinfo.*
import com.zhenquan.doubanread.ui.classfiy.BookDetailActivity
import kotlinx.android.synthetic.main.fragment_recommendation.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.onUiThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


/**
 * Created by 44162 on 2018/1/2.
 * @描述 推荐
 */
class RecommendationFragment : BaseFragment() {
    val TAG = "RecommendationFragment"
    //爬取页面https://book.douban.com/
    override fun getLayoutId(): Int {
        return R.layout.fragment_recommendation
    }

    val regexNumber by lazy { Regex("[0-9]+") }
    var alldatalist = ArrayList<BookMultipleItem>()
    var dataList_recommend = ArrayList<BookMultipleItem>()
    var dataList_infomation = ArrayList<BookMultipleItem>()
    var dataList_attention = ArrayList<BookMultipleItem>()
    var dataList_shop = ArrayList<BookMultipleItem>()
    var dataList_elc = ArrayList<BookMultipleItem>()
    var dataList_comment = ArrayList<BookMultipleItem>()
    val multipleAdapter = MultipleItemQuickAdapter(context, alldatalist)
    override fun initView(rootView: View?) {
        val fragment_recommend_recycle = rootView?.find<RecyclerView>(R.id.fragment_recommend_recycle)
        refreshLayout_recommend.setOnRefreshListener({ refreshlayout ->
            initData()
            refreshlayout.finishRefresh()
        })
        val manager = GridLayoutManager(context, 4)
        fragment_recommend_recycle?.layoutManager = manager as RecyclerView.LayoutManager?
        multipleAdapter.setSpanSizeLookup({ _, position -> alldatalist[position].spanSize })
        fragment_recommend_recycle?.adapter = multipleAdapter
    }

    override fun initData() {
        doAsync {
            //后台执行代码
            try {
                //从一个URL加载一个Document对象。
                val doc = Jsoup.connect("https://book.douban.com/").get()
                Log.e(TAG,"https://book.douban.com/")
                //选择“新书速递”所在节点
                setRecommendData(doc, dataList_recommend)

                //图书资讯
                setInfoMationData(doc, dataList_infomation)

                //最受关注图书榜  class带空格的时候这样来做
                setAttentionData(doc, dataList_attention)

                //书店
                setShudianData(doc, dataList_shop)

                //电子书
                setEbookData(doc, dataList_elc)

                //书评
                setReviewBookData(doc, dataList_comment)

            } catch (e: Exception) {
                Log.e("mytag", e.message)
            }
            onUiThread {
                //UI线程
                alldatalist.add(BookMultipleItem(BookMultipleItem.HEAD_BOOK, 4, "新书速递", true))
                alldatalist.addAll(dataList_recommend)
                alldatalist.add(BookMultipleItem(BookMultipleItem.HEAD_BOOK, 4, "图书资讯", false))
                alldatalist.addAll(dataList_infomation)
                alldatalist.add(BookMultipleItem(BookMultipleItem.HEAD_BOOK, 4, "最受关注图书榜", false))
                alldatalist.addAll(dataList_attention)
                alldatalist.add(BookMultipleItem(BookMultipleItem.HEAD_BOOK, 4, "豆瓣书店", false))
                alldatalist.addAll(dataList_shop)
                alldatalist.add(BookMultipleItem(BookMultipleItem.HEAD_BOOK, 4, "电子图书", false))
                alldatalist.addAll(dataList_elc)
                alldatalist.add(BookMultipleItem(BookMultipleItem.HEAD_BOOK, 4, "最受欢迎的书评", false))
                alldatalist.addAll(dataList_comment)
                multipleAdapter.notifyDataSetChanged()

            }

        }
    }

    private fun setReviewBookData(doc: Document, dataList_comment: ArrayList<BookMultipleItem>) {
        val comment = doc.select("div.section")
        val commentBody = comment.select("div.reviews-bd")
        val commentContent = commentBody.select("div.review")
        for (content in commentContent) {
            var book = CommentBookInfo()
            val title = content.select("img.lazy").attr("alt")
            val image = content.select("img.lazy").attr("data-original")
            val detailUrl = content.select("div.review-hd").select("a").attr("href")
            val review_meta = content.select("div.review-meta")
            val author = review_meta.select("a")[0].text()
            val review_bookname = review_meta.select("a")[1].text()
            val star = review_meta.select("span").attr("class")
            val review_content = content.select("div.review-content").text()
            book.author = author
            book.title = title
            book.image = image
            book.detailUrl = detailUrl
            book.review_bookname = review_bookname
            book.star = star
            book.review_content = review_content
            dataList_comment.add(BookMultipleItem(BookMultipleItem.COMMENT_BOOK, 4, book))
        }
        val commentLastContent = commentBody.select("div.review").select(".last")
        var book_last = CommentBookInfo()
        val title = commentLastContent.select("img.lazy").attr("alt")
        val image = commentLastContent.select("img.lazy").attr("data-original")
        val detailUrl = commentLastContent.select("div.review-hd").select("a").attr("href")
        val review_meta = commentLastContent.select("div.review-meta")
        val author = review_meta.select("a")[0].text()
        val review_bookname = review_meta.select("a")[1].text()
        val star = review_meta.select("span").attr("class")
        val review_content = commentLastContent.select("div.review-content").text()
        book_last.author = author
        book_last.title = title
        book_last.image = image
        book_last.detailUrl = detailUrl
        book_last.review_bookname = review_bookname
        book_last.star = star
        book_last.review_content = review_content
        dataList_comment.add(BookMultipleItem(BookMultipleItem.COMMENT_BOOK, 4, book_last))
    }

    private fun setEbookData(doc: Document, dataList_elc: ArrayList<BookMultipleItem>) {
        val ebook = doc.select("div.section").select(".ebook-area")
        val ebookBody = ebook.select("div.bd")
        val ebookContent = ebookBody.select("li")
        for (content in ebookContent) {
            var book = ElecBookInfo()
            val cover = content.select("div.cover")
            val title = cover.select("img").attr("alt")
            val image = cover.select("img").attr("src")
            val detailUrl = cover.select("a").attr("href")
            val price = content.select("div.price").text()
            val author = content.select("span.author").text()
            val year = content.select("span.year").text()
            val publisher = content.select("span.publisher").text()
            val jianjie = content.select("p.abstract").text()
            book.title = title
            book.image = image
            book.author = author
            book.price = tag
            book.jianjie = jianjie
            book.publisher = publisher
            book.year = year
            book.price = price
            book.detailUrl = detailUrl
            dataList_elc.add(BookMultipleItem(BookMultipleItem.ELEC_BOOK, 1, book))
        }
    }

    private fun setRecommendData(doc: Document, dataList_recommend: ArrayList<BookMultipleItem>) {
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
                book.id = regexNumber.find(detailUrl)?.value
                dataList_recommend.add(BookMultipleItem(BookMultipleItem.RECOMMEND_BOOK, 1, book))
            }

        }
    }

    private fun setInfoMationData(doc: Document, dataList_infomation: ArrayList<BookMultipleItem>) {
        val tushuzixun = doc.select("div.slide-block")
        val tushuzixunContent = tushuzixun.select("li")
        for (content in tushuzixunContent) {
            var book = InformationBookInfo()
            val title = content.select("span.title").text()
            val meta = content.select("span.meta").text()
            val abstract = content.select("p.abstract").text()
            val image = content.select("div.cover").attr("style")
            val detailUrl = content.select("a").attr("href")
            val imageurl = image.replace("background-image: url(", "").replace(")", "")
            book.title = title
            book.image = imageurl
            book.author = meta
            book.jianjie = abstract
            book.detailUrl = detailUrl
            book.id = regexNumber.find(detailUrl)?.value
            dataList_infomation.add(BookMultipleItem(BookMultipleItem.INFORMATION_BOOK, 4, book))
        }
    }

    private fun setAttentionData(doc: Document, dataList_attention: ArrayList<BookMultipleItem>) {
        val guanzhu = doc.select("div.section").select(".popular-books")
        val guanzhuBody = guanzhu.select("div.bd")
        val guanzhuContent = guanzhuBody.select("li")
        for (content in guanzhuContent) {
            var book = AttentionBookInfo()
            val cover = content.select("div.cover")
            val title = cover.select("img").attr("alt")
            val image = cover.select("img").attr("src")
            val detailUrl = cover.select("a").attr("href")
            val author = content.select("p.author").text()
            val star = content.select("span.average-rating").text()
            val tag = content.select("p.book-list-classification").text()
            val reviews = content.select("p.reviews").text()
            book.title = title
            book.image = image
            book.author = author
            book.tag = tag
            book.jianjie = reviews
            book.star = star
            book.detailUrl = detailUrl
            book.id = regexNumber.find(detailUrl)?.value
            dataList_attention.add(BookMultipleItem(BookMultipleItem.ATTENTION_BOOK, 2, book))
        }
    }

    private fun setShudianData(doc: Document, dataList_shop: ArrayList<BookMultipleItem>) {
        val shudian = doc.select("div.section").select(".market-books")
        val shudianBody = shudian.select("div.bd")
        val shudianTop = shudianBody.select("div.top")
        var bookTop = ShopBookInfo()
        val cover = shudianTop.select("div.cover")
        val image = cover.select("div.pic").attr("style").replace("background-image: url(", "").replace(")", "")
        val detailUrl = cover.select("a").attr("href")
        val title = shudianTop.select("p.title").text()
        val price = shudianTop.select("span.price").text()
        val free_delivery = shudianTop.select("span.free_delivery").text()
        val jianjie = shudianTop.select("p.desc").select(".indent-paragraph").text()
        bookTop.image = image
        bookTop.title = title
        bookTop.price = price
        bookTop.free_delivery = free_delivery
        bookTop.jianjie = jianjie
        bookTop.detailUrl = detailUrl
        dataList_shop.add(BookMultipleItem(BookMultipleItem.SHOP_BOOK, 1, bookTop))
        val shudianContent = shudianBody.select("li")
        for (content in shudianContent) {
            var book = ShopBookInfo()
            val cover = content.select("div.cover")
            val image = cover.select("img").attr("src")
            val detailUrl = cover.select("a").attr("href")
            val title = content.select("img").attr("alt")
            val price = content.select("div.price").text()
            book.title = title
            book.image = image
            book.price = price
            book.detailUrl = detailUrl
            dataList_shop.add(BookMultipleItem(BookMultipleItem.SHOP_BOOK, 1, book))
        }
    }
}

