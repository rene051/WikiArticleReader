package com.wiki.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wiki.App
import com.wiki.di.components.AppComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.getApplicationComponent()?.inject(this)
    }

    protected fun getApplicationComponent(): AppComponent? {
        return (application as App).appComponent
    }


    abstract fun initViews()
}