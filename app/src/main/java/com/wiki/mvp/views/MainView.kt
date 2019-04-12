package com.wiki.mvp.views

import com.wiki.data.models.WikiArticleResponseModel

interface MainView {

    fun onWikiRandomArticleFetchSuccess(response: WikiArticleResponseModel)

    fun onWikiRandomArticleFetchFail()

    fun onWikiArticleDetailsFetchSuccess(response: WikiArticleResponseModel)

    fun onWikiArticleDetailsFetchFail()
}