package com.app.weatherapiaplication.WeatherApi.DataApi

data class Wind(val speed:String){
    fun getWindSpeed():String{
        return speed + "m/s"
    }
}