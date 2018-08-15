package com.me.guanpj.kotlinhub.di.module

import com.me.guanpj.kotlinhub.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext() = application

}