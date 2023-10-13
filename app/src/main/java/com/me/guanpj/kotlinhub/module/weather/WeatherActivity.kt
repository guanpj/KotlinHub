package com.me.guanpj.kotlinhub.module.weather

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.module.MainViewModel

class WeatherActivity: AppCompatActivity() {
    val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.status.observe(this) {
            when (it) {
                MainViewModel.PageState.Init -> {
                    Log.e("gpj", "init")
                }
                MainViewModel.PageState.Loading -> {
                    Log.e("gpj", "loading")
                }
                is MainViewModel.PageState.Success -> {
                    Log.e("gpj", it.data.toString())
                }
                is MainViewModel.PageState.Fail -> {
                    Log.e("gpj", it.message)
                }
            }
        }

        mainViewModel.getNowWeather("da03f58fe65945c4b26a91307c41f6c6", "101210101")
    }
}