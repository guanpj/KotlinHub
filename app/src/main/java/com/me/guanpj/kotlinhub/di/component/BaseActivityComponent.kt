package com.me.guanpj.kotlinhub.di.component

import com.me.guanpj.kotlinhub.base.activity.BaseMvpActivity
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Subcomponent(modules = [AndroidInjectionModule::class])
interface BaseActivityComponent : AndroidInjector<BaseMvpActivity<*>> {
    @Subcomponent.Builder
    abstract class BaseBuilder : AndroidInjector.Builder<BaseMvpActivity<*>>()
}