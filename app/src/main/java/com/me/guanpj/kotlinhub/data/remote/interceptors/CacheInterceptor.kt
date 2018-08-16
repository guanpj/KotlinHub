package com.me.guanpj.kotlinhub.data.remote.interceptors

import com.me.guanpj.kotlinhub.data.remote.FORCE_NETWORK
import com.me.guanpj.kotlinhub.ext.logger
import com.me.guanpj.kotlinhub.ext.no
import com.me.guanpj.kotlinhub.ext.otherwise
import com.me.guanpj.kotlinhub.ext.yes
import com.me.guanpj.kotlinhub.util.NetworkUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Chain): Response {
        var request = chain.request()
        request = NetworkUtil.isAvailable()
                .no {
                    request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build()
                }
                .otherwise {
                    request.url().queryParameter(FORCE_NETWORK)?.toBoolean()?.let {
                        it.yes {
                            //注意 noCache | noStore，前者不会读缓存；后者既不读缓存，也不对响应进行缓存
                            //尽管看上去 noCache 比较符合我们的需求，但服务端仍然可能返回服务端的缓存
                            //request.newBuilder().cacheControl(CacheControl.Builder().noCache().build()).build()
                            request.newBuilder().cacheControl(CacheControl.Builder().maxAge(0, TimeUnit.SECONDS).build()).build()
                        }.otherwise {
                            request
                        }
                    } ?: request
                }

        request = request.newBuilder().url(request.url().newBuilder().removeAllQueryParameters(FORCE_NETWORK).build()).build()
        return chain.proceed(request).also { response ->
            logger.error("Cache: ${response.cacheResponse()}, Network: ${response.networkResponse()}")
        }
    }
}