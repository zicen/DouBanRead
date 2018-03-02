package com.zhenquan.doubanread.moudle

import android.os.AsyncTask

/**
 * Created by ry on 2018/1/24.
 */

data class BookInfoBean(
        val id: Long,
        val category_id: Int,
        val name: String?,
        val subtitle: String?,
        val main_image: String?,
        val author: List<String>?,
        val pubdate: String?,
        val tags: List<Tag>?,
        val origin_title: String?
        //    val rating: Rating,
        //    val subtitle: String, //张竹坡批评第一奇书
        //    val author: List<String>,
        //    val pubdate: String, //1991
        //    val tags: List<Tag>,
        //    val origin_title: String, //（明）兰陵笑笑生
        //    val image: String, //https://img1.doubanio.com/mpic/s10069398.jpg
        //    val binding: String,
        //    val translator: List<Any>,
        //    val catalog: String,
        //    val pages: String,
        //    val images: Images,
        //    val alt: String, //https://book.douban.com/subject/1456692/
        //    val id: String, //1456692
        //    val publisher: String, //齐鲁出版社
        //    val isbn10: String, //7533300815
        //    val isbn13: String, //9787533300814
        //    val title: String, //金瓶梅
        //    val url: String, //https://api.douban.com/v2/book/1456692
        //    val alt_title: String, //（明）兰陵笑笑生
        //    val author_intro: String,
        //    val summary: String, //本书由王汝梅与李昭恂、于凤树校点。
        //    val series: Series,
        //    val price: String //268.00元
)
