package com.wiki.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wiki.App
import com.wiki.di.components.AppComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.getApplicationComponent()?.inject(this)
    }

    private fun getApplicationComponent(): AppComponent? {
        return (application as App).appComponent
    }
}