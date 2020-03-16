package com.app.weatherapiaplication.WeatherApi.DataApi

data class Main (val temp:String,
                 val pressure:String,
                 val humidity: String,
                 val temp_min:String,
                 val temp_max: String){
    fun getTempInCelcius():String{
        return (((((temp.toFloat() -273.15)*10).toInt()).toFloat())/10).toString() + "°C"
    }
    fun getTempMinInCelcius():String{
        return (((((temp_min.toFloat() -273.15)*10).toInt()).toFloat())/10).toString() + "°C"
    }
    fun getTempMaxInCelcius():String{
        var helper = (((((temp_max.toFloat() -273.15)*10).toInt()).toFloat())/10).toString()

        return (((((temp_max.toFloat() -273.15)*10).toInt()).toFloat())/10).toString() + "°C"
    }
    fun getHumidityPercent():String{
        return humidity + "%"
    }
    fun getPressurehPa():String{
        return pressure + "hPa"
    }
}