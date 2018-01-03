package com.zhenquan.doubanread.ui.classfiy

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseFragment
import com.zhenquan.doubanread.moudle.TagBean
import org.jetbrains.anko.find


/**
 * Created by 44162 on 2018/1/3.
 * @描述 原创
 */
class ClassfiyFragment : BaseFragment() {
    val tags = listOf<TagBean>(
            TagBean("文学", R.mipmap.book_wenxue),
            TagBean("流行", R.mipmap.book_liuxing),
            TagBean("文化", R.mipmap.book_wenhua),
            TagBean("生活", R.mipmap.book_shenghuo),
            TagBean("经管", R.mipmap.book_jingguan),
            TagBean("科技", R.mipmap.book_keji)
    )

    override fun getLayoutId(): Int {
        return R.layout.fragment_classfiy_work
    }

    override fun initView(rootView: View?) {
        val fragment_classfiy_recycle = rootView?.find<RecyclerView>(R.id.fragment_classfiy_recycle)
        fragment_classfiy_recycle?.layoutManager = LinearLayoutManager(activity)
        fragment_classfiy_recycle?.adapter = ClassfiyAdapter(tags){
            val intent = Intent()
            intent.setClass(activity, BookTagListActivity::class.java)
            intent.putExtra("title", it.title)
            startActivity(intent)

        }
    }

    override fun initData() {
    }




}


