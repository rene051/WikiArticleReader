package com.wiki.view.activities

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.wiki.R
import com.wiki.data.models.WikiArticleResponseModel
import com.wiki.di.components.DaggerMainComponent
import com.wiki.di.modules.MainModule
import com.wiki.mvp.presenters.MainPresenter
import com.wiki.mvp.views.MainView
import com.wiki.view.BaseActivity
import com.wiki.view.adapters.WikiArticleAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var mainPresenter: MainPresenter

    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var wikiAdapter: WikiArticleAdapter
    private var wikiList = ArrayList<WikiArticleResponseModel.ArticleModel>()
    private val slidesSnapHelper = PagerSnapHelper()
    private var loading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainComponent.builder()
            .appComponent(this.getApplicationComponent())
            .mainModule(MainModule(this))
            .build().inject(this)

        initApiCall()
        setAdapter()
        pagination()
    }

    private fun initApiCall() {
        mainPresenter.fetchRandomWiki()
    }

    private fun setAdapter() {
        wikiAdapter = WikiArticleAdapter(this, wikiList)

        mLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        wikiArticleRecycler.layoutManager = mLayoutManager
        wikiArticleRecycler.adapter = wikiAdapter
        slidesSnapHelper.attachToRecyclerView(wikiArticleRecycler)
    }

    private fun setArticleText() {

    }

    override fun onWikiRandomArticleFetchSuccess(response: WikiArticleResponseModel) {
        loading = false
        wikiList.addAll(response.query!!.pages!!.values)
        wikiAdapter.notifyDataSetChanged()
    }

    override fun onWikiRandomArticleFetchFail() {

    }

    override fun onWikiArticleDetailsFetchSuccess(response: WikiArticleResponseModel) {

    }

    override fun onWikiArticleDetailsFetchFail() {

    }


    private fun pagination() {
        wikiArticleRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val centerView = slidesSnapHelper.findSnapView(mLayoutManager)
                    val pos = mLayoutManager.getPosition(centerView!!)

                    val item = wikiList[pos]

                    mainPresenter.fetchArticleWiki(item.title!!)

                    if (pos == wikiList.size - 1) {
                        if (!loading) {
                            mainPresenter.fetchRandomWiki()
                            loading = true
                        }
                    }
                }
            }
        })
    }
}
