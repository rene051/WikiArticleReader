package com.wiki.data.api

import com.wiki.data.models.WikiArticleResponseModel
import com.wiki.utils.AppConstants.Companion.ACTION
import com.wiki.utils.AppConstants.Companion.API_BASE_URL
import com.wiki.utils.AppConstants.Companion.FORMAT
import com.wiki.utils.AppConstants.Companion.GENERATOR
import com.wiki.utils.AppConstants.Companion.GRN_LIMIT
import com.wiki.utils.AppConstants.Companion.GRN_NAME_SPACE
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiDatabaseApi {

    @GET(API_BASE_URL)
    fun getWikiRandomArticle(@Query(FORMAT) format: String,
                             @Query(ACTION) action: String,
                             @Query(GENERATOR) generator: String,
                             @Query(GRN_NAME_SPACE) grnnamespace: Int,
                             @Query(GRN_LIMIT) grnlimit: Int) : Single<WikiArticleResponseModel>

}