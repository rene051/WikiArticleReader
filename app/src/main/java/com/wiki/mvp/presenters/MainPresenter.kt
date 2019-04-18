package com.wiki.mvp.presenters

interface MainPresenter {

    fun fetchRandomWiki()

    fun fetchArticleWiki(title: String)
}