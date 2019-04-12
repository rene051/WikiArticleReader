package com.wiki.view.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
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

    private lateinit var wikiAdapter: WikiArticleAdapter
    private var wikiList = ArrayList<WikiArticleResponseModel.ArticleModel>()
    val slidesSnapHelper = PagerSnapHelper()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainComponent.builder()
            .appComponent(this.getApplicationComponent())
            .mainModule(MainModule(this))
            .build().inject(this)

        initApiCall()
        setAdapter()
    }

    private fun initApiCall() {
        mainPresenter.fetchRandomWiki()
    }

    private fun setAdapter() {
        wikiAdapter = WikiArticleAdapter(this, wikiList)

        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        wikiArticleRecycler.layoutManager = mLayoutManager
        wikiArticleRecycler.adapter = wikiAdapter
        slidesSnapHelper.attachToRecyclerView(wikiArticleRecycler)
    }

    private fun setArticleText() {

    }

    override fun onWikiRandomArticleFetchSuccess(response: WikiArticleResponseModel) {
        wikiList.addAll(response.query!!.pages!!.values)
        wikiAdapter.notifyDataSetChanged()
    }

    override fun onWikiRandomArticleFetchFail() {

    }

    override fun onWikiArticleDetailsFetchSuccess() {

    }

    override fun onWikiArticleDetailsFetchFail() {

    }
}
