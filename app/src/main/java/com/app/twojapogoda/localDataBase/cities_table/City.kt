package com.app.twojapogoda.localDataBase.cities_table

import android.annotation.SuppressLint
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cities_table")
data class City (@PrimaryKey var cityName:String ) {

    init {
        cityName = cityName.trim().toLowerCase().split(" ").joinToString(" ") {
            it.capitalize()
        }
    }
}