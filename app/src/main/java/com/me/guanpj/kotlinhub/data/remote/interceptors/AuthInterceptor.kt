package com.me.guanpj.kotlinhub.data.remote.interceptors

import android.util.Base64
import com.me.guanpj.kotlinhub.core.AccountManager
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Chain): Response {
        val original = chain.request()
        return chain.proceed(original.newBuilder()
                .apply {
                    when {
                        original.url.pathSegments.contains("authorizations") -> {
                            val userCredentials = AccountManager.userName + ":" + AccountManager.password
                            val auth = "Basic " + String(Base64.encode(userCredentials.toByteArray(), Base64.DEFAULT)).trim()
                            header("Authorization", auth)
                        }
                        AccountManager.isLoggedIn() -> {
                            val auth = "Token " + AccountManager.token
                            header("Authorization", auth)
                        }
                        else -> removeHeader("Authorization")
                    }
                }
                .build())
    }
}