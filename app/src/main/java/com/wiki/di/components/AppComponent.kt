package com.wiki.di.components

import android.app.Application
import com.wiki.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: Application)

}