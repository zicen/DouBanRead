package com.zhenquan.doubanread.moudle

import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id
import java.io.Serializable

/**
 * Created by Administrator on 2018/1/2 0002.
 */

data class SearchBookList(
        val count: Int, //1
        val start: Int, //0
        val total: Int, //570
        val books: List<Book>

) {
    override fun toString(): String {
        return "SearchBookList(count=$count, start=$start, total=$total, books=$books)"
    }
}
data class Book(
        val id: String, //1456692
        var categoryid: Int,
        val rating: Rating,
        val subtitle: String, //张竹坡批评第一奇书
        val author: List<String>,
        val pubdate: String, //1991
        val tags: List<Tag>,
        val origin_title: String, //（明）兰陵笑笑生
        val image: String, //https://img1.doubanio.com/mpic/s10069398.jpg
        val binding: String,
        val translator: List<Any>,
        val catalog: String,
        val pages: String,
        val images: Images,
        val alt: String, //https://book.douban.com/subject/1456692/
        val publisher: String, //齐鲁出版社
        val isbn10: String, //7533300815
        val isbn13: String, //9787533300814
        val title: String, //金瓶梅
        val url: String, //https://api.douban.com/v2/book/1456692
        val alt_title: String, //（明）兰陵笑笑生
        val author_intro: String,
        val summary: String, //本书由王汝梅与李昭恂、于凤树校点。
        val series: Series,
        val price: String, //268.00元
        val create_time: String,
        val update_time: String


) {
    override fun toString(): String {
        return "Book(rating=$rating, subtitle='$subtitle', author=$author, pubdate='$pubdate', tags=$tags, origin_title='$origin_title', image='$image', binding='$binding', translator=$translator, catalog='$catalog', pages='$pages', images=$images, alt='$alt', id='$id', publisher='$publisher', isbn10='$isbn10', isbn13='$isbn13', title='$title', url='$url', alt_title='$alt_title', author_intro='$author_intro', summary='$summary', series=$series, price='$price')"
    }
}

data class Images(
        val small: String, //https://img1.doubanio.com/spic/s10069398.jpg
        val large: String, //https://img1.doubanio.com/lpic/s10069398.jpg
        val medium: String //https://img1.doubanio.com/mpic/s10069398.jpg

) {
    override fun toString(): String {
        return "Images(small='$small', large='$large', medium='$medium')"
    }
}

data class Rating(
        val max: Int, //10
        val numRaters: Int, //4477
        val average: String, //8.6
        val min: Int //0

) {
    override fun toString(): String {
        return "Rating(max=$max, numRaters=$numRaters, average='$average', min=$min)"
    }
}

data class Tag(
        val count: Int, //2189
        val name: String, //金瓶梅
        val title: String //金瓶梅

) {
    override fun toString(): String {
        return "Tag(count=$count, name='$name', title='$title')"
    }
}

data class Series(
        val id: String, //4279
        val title: String //明代四大奇书

) {
    override fun toString(): String {
        return "Series(id='$id', title='$title')"
    }
}