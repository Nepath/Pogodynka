package com.app.weatherapiaplication.WeatherApi.DataApi

import android.graphics.Color

data class Weather (val main: String, var icon :String){

    fun returnIcon(): String {
        return when(icon){
            "01d" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/sun-symbol-512.png"
            "02d" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/sun-cloud-512.png"
            "03d" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/cloud-512.png"
            "04d" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/cloud-black-512.png"
            "09d" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/rain-128.png"
            "10d" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/rain-128.png"
            "11d" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/storm-2-512.png"
            "13d" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/snow-2-512.png"
            "50d" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/fog-512.png"
            "01n" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/moon-symbol-512.png"
            "02n" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/moon-cloud-128.png"
            "03n" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/cloud-512.png"
            "04n" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/cloud-black-512.png"
            "09n" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/rain-128.png"
            "10n" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/rain-128.png"
            "11n" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/storm-2-512.png"
            "13n" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/snow-2-512.png"
            "50n" ->  "https://cdn3.iconfinder.com/data/icons/meteocons/512/fog-512.png"
            else -> "https://cdn3.iconfinder.com/data/icons/meteocons/512/sun-symbol-512.png"
        }
    }
    fun returnColor(): Int {
        return when(icon){
            "01d" ->  Color.parseColor("#ffcc00")
            "02d" ->  Color.parseColor("#ffcc00")
            "03d" ->  Color.parseColor("#A9A9D9")
            "04d" ->  Color.parseColor("#A9A9D9")
            "09d" ->  Color.parseColor("#A9A9D9")
            "10d" ->  Color.parseColor("#FFB947")
            "11d" ->  Color.parseColor("#D8CFBD")
            "13d" ->  Color.parseColor("#D8CFBD")
            "50d" ->  Color.parseColor("#D8CFBD")
            "01n" ->  Color.parseColor("#00CCFF")
            "02n" ->  Color.parseColor("#00CCFF")
            "03n" ->  Color.parseColor("#A9A9D9")
            "04n" ->  Color.parseColor("#A9A9D9")
            "09n" ->  Color.parseColor("#A9A9D9")
            "10n" ->  Color.parseColor("#00CCFF")
            "11n" ->  Color.parseColor("#D8CFBD")
            "13n" ->  Color.parseColor("#D8CFBD")
            "50n" ->  Color.parseColor("#D8CFBD")
            else ->   Color.parseColor("#FFCC00")
        }
    }
}