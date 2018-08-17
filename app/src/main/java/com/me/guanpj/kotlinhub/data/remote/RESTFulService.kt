package com.me.guanpj.kotlinhub.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.me.guanpj.kotlinhub.GithubApplication
import com.me.guanpj.kotlinhub.data.remote.compat.enableTls12OnPreLollipop
import com.me.guanpj.kotlinhub.data.remote.interceptors.AcceptInterceptor
import com.me.guanpj.kotlinhub.data.remote.interceptors.AuthInterceptor
import com.me.guanpj.kotlinhub.data.remote.interceptors.CacheInterceptor
import com.me.guanpj.kotlinhub.ext.ensureDir
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.MyRxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.github.com"

//通过一个 QueryParameter 让 CacheInterceptor 添加 no-cache
const val FORCE_NETWORK = "forceNetwork"

private val cacheFile by lazy {
    File(GithubApplication.AppContext.cacheDir, "webServiceApi").apply { ensureDir() }
}

val retrofit by lazy {
    Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(MyRxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .cache(Cache(cacheFile, 1024 * 1024 * 1024))
                    .addInterceptor(AcceptInterceptor())
                    .addInterceptor(AuthInterceptor())
                    .addInterceptor(CacheInterceptor())
                    .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
                    .enableTls12OnPreLollipop()
                    .build()
            )
            .baseUrl(BASE_URL)
            .build()
}