package com.me.guanpj.kotlinhub.data.remote;

import com.me.guanpj.kotlinhub.GithubApplication
import com.me.guanpj.kotlinhub.data.remote.compat.enableTls12OnPreLollipop
import com.me.guanpj.kotlinhub.data.remote.interceptors.AcceptInterceptor
import com.me.guanpj.kotlinhub.data.remote.interceptors.AuthInterceptor
import com.me.guanpj.kotlinhub.data.remote.interceptors.CacheInterceptor
import com.me.guanpj.kotlinhub.ext.ensureDir
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://devapi.qweather.com"

val weatherRetrofit1 by lazy {
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(AcceptInterceptor())
                .addInterceptor(AuthInterceptor())
                .addInterceptor(CacheInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .enableTls12OnPreLollipop()
                .build()
        )
        .baseUrl(BASE_URL)
        .build()
}
