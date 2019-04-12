package com.wiki.data.api

import com.wiki.data.models.WikiArticleResponseModel
import io.reactivex.Single

interface NetworkApi {

    fun getWikiRandomArticle() : Single<WikiArticleResponseModel>

}