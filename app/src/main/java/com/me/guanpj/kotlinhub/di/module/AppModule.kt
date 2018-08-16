package com.me.guanpj.kotlinhub.di.module

import com.me.guanpj.kotlinhub.GithubApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private val githubApplication: GithubApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext() = githubApplication

}