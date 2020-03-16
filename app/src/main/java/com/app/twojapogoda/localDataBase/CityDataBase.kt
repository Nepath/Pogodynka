package com.app.twojapogoda.localDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.twojapogoda.localDataBase.cities_table.City
import com.app.twojapogoda.localDataBase.cities_table.CityDao
import com.app.twojapogoda.localDataBase.my_cities_table.MyCity
import com.app.twojapogoda.localDataBase.my_cities_table.MyCityDao

@Database(entities = [City::class, MyCity::class], version =7, exportSchema = false)
abstract class CityDataBase:RoomDatabase() {

    abstract fun cityDao(): CityDao
    abstract fun myCityDao(): MyCityDao

    companion object{
        private  var instance: CityDataBase?= null

        fun getInstance(context:Context): CityDataBase?{
            if(instance ==null){
                instance = Room.databaseBuilder(
                    context,
                    CityDataBase::class.java,
                    "cityDataBase")
                    .fallbackToDestructiveMigration()
                    .build()

            }
            return instance
        }
        fun deleteInstanceOfDatabase(){
            instance =null
        }
    }
}