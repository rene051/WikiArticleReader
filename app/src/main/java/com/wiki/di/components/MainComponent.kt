package com.wiki.di.components

import com.wiki.di.scopes.AppScope
import com.wiki.di.modules.MainModule
import com.wiki.view.activities.MainActivity
import dagger.Component

@AppScope
@Component(dependencies = [AppComponent::class], modules = [MainModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}