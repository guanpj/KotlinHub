package com.me.guanpj.kotlinhub.data.remote.api

import com.me.guanpj.kotlinhub.entity.Repository
import com.me.guanpj.kotlinhub.entity.weather.NowWeatherData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    @GET("/v7/weather/now")
    suspend fun getNowWeather(@Query("key") key : String, @Query("location") location: String) : NowWeatherData
}