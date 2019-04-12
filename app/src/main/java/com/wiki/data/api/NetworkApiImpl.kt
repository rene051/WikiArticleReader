package com.wiki.data.api

import com.wiki.data.models.WikiArticleResponseModel
import com.wiki.utils.AppConstants.Companion.GRN_LIMIT_VALUE
import com.wiki.utils.AppConstants.Companion.GRN_NAME_SPACE_VALUE
import com.wiki.utils.AppConstants.Companion.JSON
import com.wiki.utils.AppConstants.Companion.QUERY
import com.wiki.utils.AppConstants.Companion.RANDOM
import io.reactivex.Single
import javax.inject.Inject

class NetworkApiImpl
@Inject
constructor(private val wikiDatabaseApi: WikiDatabaseApi) : NetworkApi {

    override fun getWikiRandomArticle(): Single<WikiArticleResponseModel> {
        return wikiDatabaseApi.getWikiRandomArticle(JSON, QUERY, RANDOM, GRN_NAME_SPACE_VALUE, GRN_LIMIT_VALUE)
    }
}