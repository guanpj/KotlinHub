package com.me.guanpj.kotlinhub

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import androidx.multidex.MultiDex
import androidx.fragment.app.Fragment
import com.me.guanpj.kotlinhub.core.AppStatusTracker

private lateinit var INSTANCE: Application

class GithubApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppStatusTracker.init(this)
        INSTANCE = this
    }

    override fun attachBaseContext(base: Context?) {
        MultiDex.install(base)
        super.attachBaseContext(base)
    }

    object AppContext: ContextWrapper(INSTANCE)
}