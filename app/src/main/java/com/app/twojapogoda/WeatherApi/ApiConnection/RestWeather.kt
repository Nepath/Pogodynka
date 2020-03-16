package com.app.weatherapiaplication.WeatherApi.ApiConnection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.weatherapiaplication.WeatherApi.DataApi.WeatherApiMain
import com.app.weatherapiaplication.WeatherApi.InterfaceApi.IWeatherApi
import com.google.gson.GsonBuilder
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class RestWeather {

    private fun retrofit() : IWeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(IWeatherApi::class.java)
    }

    suspend fun fetchResponse(c: String) :WeatherApiMain{
        return retrofit().fetchCity(c)
    }
}