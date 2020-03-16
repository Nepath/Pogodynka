package com.app.twojapogoda.localDataBase.my_cities_table

import android.annotation.SuppressLint
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_city_table")
data class MyCity(@PrimaryKey var cityName:String ){


    init {
        cityName = cityName.trim().toLowerCase().split(" ").joinToString(" ") {
            it.capitalize()
        }
    }
}