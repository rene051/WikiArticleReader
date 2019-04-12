package com.wiki.mvp.presenters.impl

import android.content.Context
import com.wiki.data.api.NetworkApi
import com.wiki.data.models.WikiArticleResponseModel
import com.wiki.di.modules.ThreadModule
import com.wiki.mvp.interactors.MainInteractor
import com.wiki.mvp.presenters.MainPresenter
import com.wiki.mvp.views.MainView
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Named

class MainPresenterImpl
@Inject
constructor(private val context: Context, private val mainView: MainView,
            private val mainInteractor: MainInteractor) : MainPresenter {

    @Inject
    @field:Named(ThreadModule.SUBSCRIBE_SCHEDULER)
    lateinit var subscribeScheduler: Scheduler

    @Inject
    @field:Named(ThreadModule.OBSERVE_SCHEDULER)
    lateinit var observeScheduler: Scheduler

    @Inject
    lateinit var networkApi: NetworkApi


    override fun fetchRandomWiki() {
        networkApi.getWikiRandomArticle()
            .subscribeOn(subscribeScheduler)
            .observeOn(observeScheduler)
            .subscribe(object : SingleObserver<WikiArticleResponseModel> {
                override fun onSuccess(t: WikiArticleResponseModel) {
                    mainView.onWikiRandomArticleFetchSuccess(t)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    mainView.onWikiRandomArticleFetchFail()
                }
            })
    }
}