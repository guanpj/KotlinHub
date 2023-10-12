package com.me.guanpj.kotlinhub.module

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.guanpj.kotlinhub.data.remote.api.WeatherApi
import com.me.guanpj.kotlinhub.data.remote.weatherRetrofit
import com.me.guanpj.kotlinhub.entity.weather.AllWeatherData
import com.me.guanpj.kotlinhub.entity.weather.NowWeatherData
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

class MainViewModel: ViewModel() {
    var status = MutableLiveData<PageState>(PageState.Init)
    private val weatherApi = weatherRetrofit.create(WeatherApi::class.java)
    fun getNowWeather(key: String, location: String) {
        viewModelScope.launch {
            weatherApi.getNowWeather(key, location)
        }
    }

    sealed class PageState {
        object Init : PageState()
        object Loading : PageState()
        class Success(val data: NowWeatherData) : PageState()
        class Fail(val message: String) : PageState()
    }
}