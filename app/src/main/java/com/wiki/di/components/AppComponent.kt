package com.wiki.di.components

import android.app.Application
import android.content.Context
import com.wiki.di.modules.AppModule
import com.wiki.di.modules.NetModule
import com.wiki.di.modules.ThreadModule
import com.wiki.di.modules.ThreadModule.Companion.OBSERVE_SCHEDULER
import com.wiki.di.modules.ThreadModule.Companion.SUBSCRIBE_SCHEDULER
import com.wiki.view.BaseActivity
import dagger.Component
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ThreadModule::class, NetModule::class])
interface AppComponent {

    fun inject(app: Application)

    fun context(): Context

    @Named(OBSERVE_SCHEDULER)
    fun provideAndroidSchedulersMainThread(): Scheduler

    @Named(SUBSCRIBE_SCHEDULER)
    fun provideSchedulerIo(): Scheduler

    fun inject(baseActivity: BaseActivity)

}