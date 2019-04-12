package com.wiki.view.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper
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

        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
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

    override fun onWikiArticleDetailsFetchSuccess() {

    }

    override fun onWikiArticleDetailsFetchFail() {

    }


    private fun pagination() {

        wikiArticleRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = mLayoutManager.itemCount
                val lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
                val myTotalCount = wikiList.size - 1

                Log.e("pagination", "totalItem: $totalItemCount")
                Log.e("pagination", "lastVisibleItemPosition: $lastVisibleItemPosition")
                Log.e("pagination", "myTotalCount: $myTotalCount")
                Log.e("pagination", " ")

                if (dx > 0) {
                    if ((lastVisibleItemPosition >= myTotalCount) && lastVisibleItemPosition > 0
                        && myTotalCount > 0 && (myTotalCount + 1) <= totalItemCount) {
                        if (!loading) {
                            Log.e("pagination", "-------------------------CALLED----------------------------")
                            Log.e("pagination", "totalItem: $totalItemCount")
                            Log.e("pagination", "lastVisibleItemPosition: $lastVisibleItemPosition")
                            Log.e("pagination", "myTotalCount: $myTotalCount")
                            Log.e("pagination", "-----------------------------------------------------")
                            mainPresenter.fetchRandomWiki()
                            loading = true
                        }
                    }
                }
            }
        })
    }
}
