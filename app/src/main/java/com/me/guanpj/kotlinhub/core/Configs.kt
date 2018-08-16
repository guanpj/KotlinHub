package com.me.guanpj.kotlinhub.core

import com.me.guanpj.kotlinhub.GithubApplication
import com.me.guanpj.kotlinhub.entity.deviceId
import com.me.guanpj.kotlinhub.ext.logger

object Configs {

    const val BASE_URL = "https://api.github.com"
    const val FORCE_NETWORK = "forceNetwork"

    object Account {
        val SCOPES = listOf("user", "repo", "notifications", "gist", "admin:org")
        const val clientId = "353efa5a6ca04340c2c3"
        const val clientSecret = "fc81fa49604ce6413af7685e0e195ab0ed280219"
        const val note = "kotliner.cn"
        const val noteUrl = "http://www.kotliner.cn"

        val fingerPrint by lazy {
            (GithubApplication.AppContext.deviceId + clientId).also { logger.info("fingerPrint: " + it) }
        }
    }

}