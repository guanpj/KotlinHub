package com.me.guanpj.kotlinhub.di.component

import com.me.guanpj.kotlinhub.di.module.AllActivityModule
import com.me.guanpj.kotlinhub.di.module.AllFragmentModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class
    , AllActivityModule::class, AllFragmentModule::class])
interface AppComponent {
}