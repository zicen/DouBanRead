package com.zhenquan.doubanread.moudle
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


/**
 * Created by ry on 2018/1/4.
 */


data class BookDetail(
		val rating: Rating,
		val subtitle: String,
		val author: List<String>,
		val pubdate: String, //2014-5
		val tags: List<Tag>,
		val origin_title: String, //ナミヤ雑貨店の奇蹟
		val image: String, //https://img3.doubanio.com/mpic/s27264181.jpg
		val binding: String, //精装
		val translator: List<String>,
		val catalog: String, //第一章 回答在牛奶箱里
		val ebook_url: String, //https://read.douban.com/ebook/36103930/
		val pages: String, //291
		val images: Images,
		val alt: String, //https://book.douban.com/subject/25862578/
		val id: String, //25862578
		val publisher: String, //南海出版公司
		val isbn10: String, //7544270874
		val isbn13: String, //9787544270878
		val title: String, //解忧杂货店
		val url: String, //https://api.douban.com/v2/book/25862578
		val alt_title: String, //ナミヤ雑貨店の奇蹟
		val author_intro: String, //东野圭吾
		val summary: String, //现代人内心流失的东西，这家杂货店能帮你找回——
		val ebook_price: String, //13.00
		val series: Series,
		val price: String //39.50元
)

