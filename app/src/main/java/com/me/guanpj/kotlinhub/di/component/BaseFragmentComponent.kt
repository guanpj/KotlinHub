package com.me.guanpj.kotlinhub.di.component

import com.me.guanpj.kotlinhub.base.BasePresenter
import com.me.guanpj.kotlinhub.base.IMvpView
import com.me.guanpj.kotlinhub.base.activity.BaseMvpActivity
import com.me.guanpj.kotlinhub.base.fragment.BaseMvpFragment
import dagger.Subcomponent
import dagger.android.AndroidInjection
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Subcomponent(modules = [AndroidInjectionModule::class])
interface BaseFragmentComponent {
    @Subcomponent.Builder
    abstract class BaseBuilder : AndroidInjector.Builder<BaseMvpFragment<*>>()
}