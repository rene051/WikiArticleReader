package com.wiki.view.activities

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
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
    private lateinit var mainWikiItem: WikiArticleResponseModel.ArticleModel
    private lateinit var alert: AlertDialog
    private lateinit var builder: AlertDialog.Builder
    private var wikiList = ArrayList<WikiArticleResponseModel.ArticleModel>()
    private var wikiArticleList = ArrayList<WikiArticleResponseModel.ArticleModel>()
    private val slidesSnapHelper = PagerSnapHelper()
    private var loading = false
    private var initWikiArticleCallDone = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainComponent.builder()
            .appComponent(this.getApplicationComponent())
            .mainModule(MainModule(this))
            .build().inject(this)

        initViews()
        mainPresenter.fetchRandomWiki()
        setAdapter()
        pagination()
    }

    override fun initViews() {
        builder = AlertDialog.Builder(this)
        builder.setMessage(getString(R.string.internet_error))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.try_again)) { dialog, id ->
                if (initWikiArticleCallDone) {
                    mainPresenter.fetchArticleWiki(mainWikiItem.title!!)
                    if(loading) {
                        mainPresenter.fetchRandomWiki()
                    }
                } else {
                    mainPresenter.fetchRandomWiki()
                }
            }
        alert = builder.create()
    }

    override fun onWikiRandomArticleFetchSuccess(response: WikiArticleResponseModel) {
        loading = false
        wikiList.addAll(response.query!!.pages!!.values)
        wikiAdapter.notifyDataSetChanged()

        fetchFirstArticle()
    }

    override fun onWikiRandomArticleFetchFail() {
        showErrorMessage()
    }

    override fun onWikiArticleDetailsFetchSuccess(response: WikiArticleResponseModel) {
        hideProgressBar()
        wikiArticleList.addAll(response.query!!.pages!!.values)
        setArticleText()
    }

    override fun onWikiArticleDetailsFetchFail() {
        showErrorMessage()
    }

    private fun showErrorMessage() {
        alert.show()
    }

    private fun fetchFirstArticle() {
        if (!initWikiArticleCallDone) {
            showProgressBar()
            mainWikiItem = wikiList[0]
            mainPresenter.fetchArticleWiki(mainWikiItem.title!!)
            initWikiArticleCallDone = true
        }
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
        wikiArticleList.forEach {
            if (it.pageId == mainWikiItem.pageId) {
                Log.e("loadArticle", "load for title: " + mainWikiItem.title)
                wikiArticleDetailText.text = it.extract
                hideProgressBar()
                return
            }
        }
        Log.e("loadArticle", "fetch for title: " + mainWikiItem.title)
        showProgressBar()
        mainPresenter.fetchArticleWiki(mainWikiItem.title!!)
    }

    private fun showProgressBar() {
        wikiArticleDetailText.visibility = View.INVISIBLE
        mainProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        mainProgressBar.visibility = View.GONE
        wikiArticleDetailText.visibility = View.VISIBLE
    }

    private fun pagination() {
        wikiArticleRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val centerView = slidesSnapHelper.findSnapView(mLayoutManager)
                    val pos = mLayoutManager.getPosition(centerView!!)

                    val item = wikiList[pos]

                    mainWikiItem = item
                    setArticleText()

                    Log.e("pagination", "pos: " + (pos + 1) + "/" + wikiList.size)
                    if (pos + 1 >= wikiList.size - 1) {
                        if (!loading) {
                            Log.e("pagination", "called: " + (pos + 1) + "/" + (wikiList.size))
                            mainPresenter.fetchRandomWiki()
                            loading = true
                        }
                    }
                }
            }
        })
    }
}
