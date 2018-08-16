package com.me.guanpj.kotlinhub.util

import com.me.guanpj.kotlinhub.GithubApplication
import org.jetbrains.anko.connectivityManager

object NetworkUtil {
    fun isAvailable(): Boolean = GithubApplication.AppContext.connectivityManager
            .activeNetworkInfo?.isAvailable?: false
}