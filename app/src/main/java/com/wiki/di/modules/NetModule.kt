package com.wiki.di.modules

import android.content.Context
import com.google.gson.Gson
import com.wiki.data.api.NetworkApi
import com.wiki.data.api.NetworkApiImpl
import com.wiki.data.api.WikiDatabaseApi
import com.wiki.data.api.WikiDatabaseApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideHttpCache(application: Context): Cache = Cache(application.cacheDir, (10 * 1024 * 1024).toLong())

    @Provides
    @Singleton
    fun provideOkhttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder().readTimeout(10000, TimeUnit.MILLISECONDS).connectTimeout(15000, TimeUnit.MILLISECONDS)
        client.cache(cache)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkApi(wikiDatabaseApi: WikiDatabaseApi): NetworkApi = NetworkApiImpl(wikiDatabaseApi)

    @Provides
    @Singleton
    fun provideWikiDatabaseApi(retrofit: Retrofit): WikiDatabaseApi = retrofit.create<WikiDatabaseApi>(WikiDatabaseApi::class.java)
}