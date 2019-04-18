package com.wiki

import android.app.Application
import com.wiki.di.components.AppComponent
import com.wiki.di.components.DaggerAppComponent
import com.wiki.di.modules.AppModule

class App : Application() {

    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        instance = this

        this.appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        var instance: App? = null
    }
}