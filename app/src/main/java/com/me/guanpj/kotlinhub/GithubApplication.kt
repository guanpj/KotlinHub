package com.me.guanpj.kotlinhub

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.support.multidex.MultiDex
import android.support.v4.app.Fragment
import com.me.guanpj.kotlinhub.core.AppStatusTracker
import com.me.guanpj.kotlinhub.di.component.AppComponent
import com.me.guanpj.kotlinhub.di.component.DaggerAppComponent
import com.me.guanpj.kotlinhub.di.module.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

private lateinit var INSTANCE: Application

class GithubApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                //.appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()
        appComponent.inject(this)
        AppStatusTracker.init(this)
        INSTANCE = this
    }

    override fun attachBaseContext(base: Context?) {
        MultiDex.install(base)
        super.attachBaseContext(base)
    }

    override fun activityInjector(): AndroidInjector<Activity> = mActivityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = mFragmentInjector

    object AppContext: ContextWrapper(INSTANCE)
}