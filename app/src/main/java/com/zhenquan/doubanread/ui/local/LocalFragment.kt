package com.zhenquan.doubanread.ui.local

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseFragment
import com.zhenquan.doubanread.base.adapter.BaseRecyclerAdapter
import com.zhenquan.doubanread.base.adapter.SmartViewHolder
import com.zhenquan.doubanread.moudle.Book
import com.zhenquan.doubanread.moudle.LocalBookInfo
import com.zhenquan.doubanread.ui.classfiy.BookDetailActivity
import com.zhenquan.doubanread.ui.classfiy.BookTagListActivity
import com.zhenquan.doubanread.ui.classfiy.ClassfiyAdapter
import org.jetbrains.anko.find
import java.lang.reflect.Array

/**
 * Created by 44162 on 2018/1/3.
 * @描述 本地
 */
class LocalFragment : BaseFragment() {
    val adapter: BaseRecyclerAdapter<LocalBookInfo> by lazy {
        object : BaseRecyclerAdapter<LocalBookInfo>(R.layout.item_local_book_list) {
            override fun onBindViewHolder(holder: SmartViewHolder, model: LocalBookInfo, position: Int) {
                holder.text(R.id.tv_local_book_name, model.name)
                holder.itemView.setOnClickListener {
//                    val intent = Intent()
//                    intent.setClass(holder.itemView.context, BookDetailActivity::class.java)
//                    intent.putExtra("title", model.name)
//                    startActivity(intent)
                }
            }
        }
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_local
    }

    override fun initView(rootView: View?) {
        val fragment_local_recycle = rootView?.find<RecyclerView>(R.id.fragment_local_recycle)
        fragment_local_recycle?.layoutManager = GridLayoutManager(activity, 3)
        fragment_local_recycle?.adapter = adapter
        var  list = ArrayList<LocalBookInfo>()
        var num = 0
        while (num <20){
            list.add(LocalBookInfo("书籍名" + num,""))
            num++
        }
        adapter.refresh(list)
    }
}
