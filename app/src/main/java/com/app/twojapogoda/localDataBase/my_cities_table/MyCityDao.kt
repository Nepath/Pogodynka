package com.app.twojapogoda.localDataBase.my_cities_table

import androidx.room.*
import com.app.twojapogoda.localDataBase.cities_table.City

@Dao
interface MyCityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(myCity: MyCity)

    @Delete
    suspend fun delete(myCity: MyCity)

    @Query ("SELECT * FROM my_city_table WHERE cityName LIKE '%' || :c || '%'")
    suspend fun getMyCity(c: String) :List<MyCity>

    @Query ("SELECT * FROM my_city_table")
    suspend fun getAllMyCity() :List<MyCity>

}