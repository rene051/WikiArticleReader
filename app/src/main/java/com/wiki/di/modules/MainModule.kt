package com.wiki.di.modules

import com.wiki.di.scopes.AppScope
import com.wiki.mvp.interactors.MainInteractor
import com.wiki.mvp.interactors.impl.MainInteractorImpl
import com.wiki.mvp.presenters.MainPresenter
import com.wiki.mvp.presenters.impl.MainPresenterImpl
import com.wiki.mvp.views.MainView
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val mainView: MainView) {

    @AppScope
    @Provides
    fun providesMainView(): MainView = this.mainView

    @AppScope
    @Provides
    fun providesMainPresenter(presenter: MainPresenterImpl): MainPresenter = presenter

    @AppScope
    @Provides
    fun providesMainInteractor(interactor: MainInteractorImpl): MainInteractor = interactor
}