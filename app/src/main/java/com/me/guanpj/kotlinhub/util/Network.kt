package com.me.guanpj.kotlinhub.util

import com.me.guanpj.kotlinhub.Application
import org.jetbrains.anko.connectivityManager

object Network {
    fun isAvailable(): Boolean = Application.AppContext.connectivityManager
            .activeNetworkInfo?.isAvailable?: false
}