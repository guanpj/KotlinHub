package com.me.guanpj.kotlinhub

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.support.multidex.MultiDex
import com.me.guanpj.kotlinhub.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

private lateinit var INSTANCE: Application

class GithubApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var mAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().build().inject(this)
        INSTANCE = this
    }

    override fun attachBaseContext(base: Context?) {
        MultiDex.install(base)
        super.attachBaseContext(base)
    }

    override fun activityInjector(): AndroidInjector<Activity> = mAndroidInjector

    object AppContext: ContextWrapper(INSTANCE)
}