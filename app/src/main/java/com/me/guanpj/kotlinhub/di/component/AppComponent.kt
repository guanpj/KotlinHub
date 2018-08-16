package com.me.guanpj.kotlinhub.di.component

import com.me.guanpj.kotlinhub.GithubApplication
import com.me.guanpj.kotlinhub.data.remote.api.AuthApi
import com.me.guanpj.kotlinhub.di.module.AllActivityModule
import com.me.guanpj.kotlinhub.di.module.AllFragmentModule
import com.me.guanpj.kotlinhub.di.module.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class
    , AllActivityModule::class, AllFragmentModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(applicataion: GithubApplication)

    val authApi: AuthApi
}