package com.me.guanpj.kotlinhub.di.component

import com.me.guanpj.kotlinhub.base.fragment.BaseMvpFragment
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Subcomponent(modules = [AndroidInjectionModule::class])
interface BaseFragmentComponent : AndroidInjector<BaseMvpFragment<*>> {
    @Subcomponent.Builder
    abstract class BaseBuilder : AndroidInjector.Builder<BaseMvpFragment<*>>()
}