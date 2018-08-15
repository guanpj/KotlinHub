package com.me.guanpj.kotlinhub

import android.app.Application
import android.content.ContextWrapper

private lateinit var INSTANCE: Application

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    object AppContext: ContextWrapper(INSTANCE)
}