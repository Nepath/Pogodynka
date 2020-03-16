package com.app.weatherapiaplication.WeatherApi.DataApi

data class WeatherApiMain(val main: Main, val sys:Sys, val weather: List<Weather>, val wind: Wind, val timezone:String, val name: String)
