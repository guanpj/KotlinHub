package com.me.guanpj.kotlinhub.module.weather

import android.graphics.pdf.PdfDocument.Page
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.guanpj.kotlinhub.data.remote.api.WeatherApi
import com.me.guanpj.kotlinhub.data.remote.weatherRetrofit
import com.me.guanpj.kotlinhub.entity.weather.AllWeatherData
import com.me.guanpj.kotlinhub.entity.weather.NowWeatherData
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

class WeatherViewModel: ViewModel() {
    var status = MutableLiveData<PageState>(PageState.Init)
    private val weatherApi = weatherRetrofit.create(WeatherApi::class.java)
    fun getNowWeather(key: String, location: String) {
        status.value = PageState.Loading
        viewModelScope.launch {
            status.value = try {
                PageState.Success(weatherApi.getNowWeather(key, location))
            } catch (e: Exception) {
                PageState.Fail(e.localizedMessage ?: "error")
            }
        }
    }

    sealed class PageState {
        object Init : PageState()
        object Loading : PageState()
        class Success(val data: NowWeatherData) : PageState()
        class Fail(val message: String) : PageState()
    }
}