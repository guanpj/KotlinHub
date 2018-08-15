package com.me.guanpj.kotlinhub.di.module

import com.me.guanpj.kotlinhub.di.component.BaseActivityComponent
import com.me.guanpj.kotlinhub.module.login.LoginActivity
import com.me.guanpj.kotlinhub.module.login.LoginActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = [BaseActivityComponent::class])
abstract class AllActivityModule {

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributesLoginActivityInjector(): LoginActivity

}