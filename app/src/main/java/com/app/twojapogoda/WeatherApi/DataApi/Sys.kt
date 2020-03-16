package com.app.weatherapiaplication.WeatherApi.DataApi

import android.icu.text.SimpleDateFormat
import android.util.Log
import java.time.format.DateTimeFormatter
import java.util.*

data class Sys(val country: String, val sunrise:String, val sunset:String){

    fun getSunriseFormat():String{
        val current = Date(sunrise.toLong() * 1000)
        val format = SimpleDateFormat("HH:mm:ss")
        return format.format(current)
    }
    fun getSunsetFormat():String{

        val current = Date(sunset.toLong() * 1000)
        val format = SimpleDateFormat("HH:mm:ss")
        return format.format(current)
    }
}