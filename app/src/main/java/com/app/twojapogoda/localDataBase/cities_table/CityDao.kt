package com.app.twojapogoda.localDataBase.cities_table

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.*
import androidx.room.Query
import com.app.twojapogoda.localDataBase.cities_table.City

@Dao
interface CityDao {

    @Insert (onConflict = IGNORE)
    suspend fun insert(city: City)

    @Query("SELECT * FROM cities_table WHERE cityName LIKE '%' || :c || '%' LIMIT 10")
    suspend fun getCities(c: String): List<City>

    @Query("SELECT * FROM cities_table WHERE cityName = :c")
    suspend fun checkIfCityExists(c: String): List<City>

}