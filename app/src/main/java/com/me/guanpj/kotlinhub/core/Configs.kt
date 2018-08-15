package com.me.guanpj.kotlinhub.core

import com.me.guanpj.kotlinhub.Application
import com.me.guanpj.kotlinhub.deviceId
import com.me.guanpj.kotlinhub.ext.logger

object Configs {

    object Account {
        val SCOPES = listOf("user", "repo", "notifications", "gist", "admin:org")
        const val clientId = "cccb7d70ba4fe6d4f62d"
        const val clientSecret = "30bea5fc021ed503bef21e23ce8e2b02d588ab6c"
        const val note = "kotliner.cn"
        const val noteUrl = "http://www.kotliner.cn"

        val fingerPrint by lazy {
            (Application.AppContext.deviceId + clientId).also { logger.info("fingerPrint: " + it) }
        }
    }

}