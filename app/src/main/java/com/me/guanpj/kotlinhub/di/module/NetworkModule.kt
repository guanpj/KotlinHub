package com.me.guanpj.kotlinhub.di.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.me.guanpj.kotlinhub.GithubApplication
import com.me.guanpj.kotlinhub.core.Configs
import com.me.guanpj.kotlinhub.data.remote.api.AuthApi
import com.me.guanpj.kotlinhub.data.remote.compat.enableTls12OnPreLollipop
import com.me.guanpj.kotlinhub.data.remote.interceptors.AcceptInterceptor
import com.me.guanpj.kotlinhub.data.remote.interceptors.AuthInterceptor
import com.me.guanpj.kotlinhub.data.remote.interceptors.CacheInterceptor
import com.me.guanpj.kotlinhub.ext.ensureDir
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private val cacheFile by lazy {
        File(GithubApplication.AppContext.cacheDir, "webServiceApi").apply { ensureDir() }
    }

    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit) : AuthApi = retrofit.create(AuthApi::class.java)

    @Singleton
    @Provides
    fun provideOkhttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .cache(Cache(cacheFile, 1024 * 1024 * 1024))
                .addInterceptor(AcceptInterceptor())
                .addInterceptor(AuthInterceptor())
                .addInterceptor(CacheInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .enableTls12OnPreLollipop()
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .baseUrl(Configs.BASE_URL)
                .build()
    }
}
