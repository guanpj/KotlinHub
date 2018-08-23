package com.me.guanpj.kotlinhub.di.module

import com.me.guanpj.kotlinhub.di.component.BaseFragmentComponent
import com.me.guanpj.kotlinhub.module.feeds.FeedsFragment
import com.me.guanpj.kotlinhub.module.feeds.FeedsModule
import com.me.guanpj.kotlinhub.module.main.MainFragment
import com.me.guanpj.kotlinhub.module.main.MainFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = [BaseFragmentComponent::class])
abstract class AllFragmentModule {

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun contributesMainFragmentInjector(): MainFragment

    @ContributesAndroidInjector(modules = [FeedsModule::class])
    abstract fun contributesFeedsFragmentInjector(): FeedsFragment
}