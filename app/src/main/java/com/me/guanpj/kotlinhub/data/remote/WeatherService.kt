package com.me.guanpj.kotlinhub.data.remote;

import android.util.Log
import com.me.guanpj.kotlinhub.data.remote.compat.enableTls12OnPreLollipop
import com.me.guanpj.kotlinhub.data.remote.interceptors.AcceptInterceptor
import com.me.guanpj.kotlinhub.data.remote.interceptors.AuthInterceptor
import com.me.guanpj.kotlinhub.data.remote.interceptors.CacheInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://devapi.qweather.com"

val weatherRetrofit by lazy {
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(HttpLoggingInterceptor {
                    Log.d("OkHttp", it);
                })
                .enableTls12OnPreLollipop()
                .build()
        )
        .baseUrl(BASE_URL)
        .build()
}
