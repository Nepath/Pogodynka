package com.app.weatherapiaplication.WeatherApi.InterfaceApi

import com.app.weatherapiaplication.WeatherApi.DataApi.WeatherApiMain
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherApi {
    @GET("data/2.5/weather/?appid=73e04a151ff7847e4142f6c5407304fa")
    suspend fun fetchCity(@Query("q") city:String): WeatherApiMain
}