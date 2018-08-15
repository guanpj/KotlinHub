package com.me.guanpj.kotlinhub.di.component

import com.me.guanpj.kotlinhub.base.BasePresenter
import com.me.guanpj.kotlinhub.base.IMvpView
import com.me.guanpj.kotlinhub.base.activity.BaseMvpActivity
import dagger.Subcomponent
import dagger.android.AndroidInjection
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Subcomponent(modules = [AndroidInjectionModule::class])
interface BaseActivityComponent {
    abstract class BaseBuilder : AndroidInjector.Builder<BaseMvpActivity<>>() {

    }
}