package com.app.twojapogoda.localDataBase

import android.annotation.SuppressLint
import android.app.Application
import com.app.twojapogoda.localDataBase.cities_table.City
import com.app.twojapogoda.localDataBase.cities_table.CityDao
import com.app.twojapogoda.localDataBase.my_cities_table.MyCity
import com.app.twojapogoda.localDataBase.my_cities_table.MyCityDao

class CityRepository (application: Application) {

    private var cityDao: CityDao
    private var myCityDao: MyCityDao

    init {
        val database = CityDataBase
            .getInstance(application.applicationContext)
        myCityDao= database!!.myCityDao()
        cityDao = database.cityDao()
    }

    @SuppressLint("DefaultLocale")
    suspend fun insert(city: City) {
        cityDao.insert(city)
    }


    suspend fun getCities(city:String): List<City> {
        return cityDao.getCities(city)
    }

    suspend fun checkCity(city:String):Boolean{

        return cityDao.checkIfCityExists(city.trim().toLowerCase().split(" ").joinToString(" ") {
            it.capitalize()
        }).count()>0
    }

    suspend fun insertMyCity(city: MyCity){
        myCityDao.insert(city)
    }

    suspend fun getMyCity(city: String): List<MyCity>{
        return myCityDao.getMyCity(city)
    }

    suspend fun deleteMyCity(city: MyCity){
        myCityDao.delete(city)
    }

    suspend fun getAllMyCity() : List<MyCity>{
        return myCityDao.getAllMyCity()
    }


}