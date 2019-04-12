package com.wiki.data.api

import com.wiki.data.models.WikiArticleResponseModel
import com.wiki.utils.AppConstants.Companion.ACTION
import com.wiki.utils.AppConstants.Companion.API_BASE_URL
import com.wiki.utils.AppConstants.Companion.EX_INTRO
import com.wiki.utils.AppConstants.Companion.EX_PLAIN_TEXT
import com.wiki.utils.AppConstants.Companion.FORMAT
import com.wiki.utils.AppConstants.Companion.GENERATOR
import com.wiki.utils.AppConstants.Companion.GRN_LIMIT
import com.wiki.utils.AppConstants.Companion.GRN_NAME_SPACE
import com.wiki.utils.AppConstants.Companion.PROP
import com.wiki.utils.AppConstants.Companion.TITLES
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

    @GET(API_BASE_URL)
    fun getWikiArticleDetails(@Query(FORMAT) format: String,
                              @Query(ACTION) action: String,
                              @Query(PROP) generator: String,
                              @Query(EX_INTRO) exintro: String,
                              @Query(EX_PLAIN_TEXT) explaintext: String,
                              @Query(TITLES) titles: String) : Single<WikiArticleResponseModel>

}