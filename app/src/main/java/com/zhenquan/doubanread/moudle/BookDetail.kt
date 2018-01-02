package com.zhenquan.doubanread.moudle

/**
 * Created by ry on 2018/1/2.
 */

data class BookDetail(
        val count: Int, //1
        val start: Int, //0
        val total: Int, //570
        val books: List<Book>

) {
    override fun toString(): String {
        return "BookDetail(count=$count, start=$start, total=$total, books=$books)"
    }
}

data class Book(
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
        val id: String, //1456692
        val publisher: String, //齐鲁出版社
        val isbn10: String, //7533300815
        val isbn13: String, //9787533300814
        val title: String, //金瓶梅
        val url: String, //https://api.douban.com/v2/book/1456692
        val alt_title: String, //（明）兰陵笑笑生
        val author_intro: String,
        val summary: String, //本书由王汝梅与李昭恂、于凤树校点。
        val series: Series,
        val price: String //268.00元

) {
    override fun toString(): String {
        return "Book(rating=$rating, subtitle='$subtitle', author=$author, pubdate='$pubdate', tags=$tags, origin_title='$origin_title', image='$image', binding='$binding', translator=$translator, catalog='$catalog', pages='$pages', images=$images, alt='$alt', id='$id', publisher='$publisher', isbn10='$isbn10', isbn13='$isbn13', title='$title', url='$url', alt_title='$alt_title', author_intro='$author_intro', summary='$summary', series=$series, price='$price')"
    }
}

data class Images(
        val small: String, //https://img1.doubanio.com/spic/s10069398.jpg
        val large: String, //https://img1.doubanio.com/lpic/s10069398.jpg
        val medium: String //https://img1.doubanio.com/mpic/s10069398.jpg
)

data class Series(
        val id: String, //4279
        val title: String //明代四大奇书
)

data class Rating(
        val max: Int, //10
        val numRaters: Int, //4476
        val average: String, //8.6
        val min: Int //0
)

data class Tag(
        val count: Int, //2187
        val name: String, //金瓶梅
        val title: String //金瓶梅
)
