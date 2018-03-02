package com.zhenquan.doubanread.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.base.adapter.BaseRecyclerAdapter
import com.zhenquan.doubanread.base.adapter.SmartViewHolder
import com.zhenquan.doubanread.moudle.*
import com.zhenquan.doubanread.net.RetrofitHelper
import com.zhenquan.doubanread.ui.classfiy.BookDetailActivity
import kotlinx.android.synthetic.main.activity_matches.*
import kotlinx.android.synthetic.main.activity_my_want_change.*
import org.jetbrains.anko.find
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MatchesActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_matches

    }

    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun initView(rootView: View?) {
        initToolBar()
    }

    private fun initToolBar(): String {
        val title = intent.extras.getString("title")
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    val adapter: BaseRecyclerAdapter<MatchesBeanItem> by lazy {
        object : BaseRecyclerAdapter<MatchesBeanItem>(R.layout.item_book_list) {
            override fun onBindViewHolder(holder: SmartViewHolder, model: MatchesBeanItem, position: Int) {
                holder.text(R.id.tv_book_name, model.wantRead.title)
                holder.text(R.id.tv_book_author, model.wantRead.author)
                holder.text(R.id.tv_book_comment, model.wantRead.rating)
                holder.text(R.id.tv_book_jianjie, model.wantRead.summary)
                val image = holder.image(R.id.iv_book_list)
                Picasso.with(holder.itemView.context).load(model.wantRead.image).into(image)
                holder.itemView.setOnClickListener {
                    val intent = Intent()
                    intent.setClass(holder.itemView.context, BookDetailActivity::class.java)
                    intent.putExtra("title", model.wantRead.title)
                    intent.putExtra("id", "" + model.wantRead.id)
                    startActivity(intent)
                }
            }
        }
    }

    override fun initListener() {
        my_matches_recycle.layoutManager = LinearLayoutManager(this)
        my_matches_recycle.adapter = adapter
        getData()
        my_matches_refreshLayout.setOnRefreshListener {
            getData()
        }
    }


    private fun getData() {
        showWaitDialog()
        request(RetrofitHelper
                .getServerForAliYun()?.getMatchesList()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { t: MatchesBean? ->
                    t?.checkSuccess {
                        hideWaitDialog()
                        //成功
                        t.data.let {
                            adapter.refresh(t.data)
                            my_matches_refreshLayout.finishRefresh()
                            my_matches_refreshLayout.resetNoMoreData()
                        }
                    }
                }
        )
    }

}
