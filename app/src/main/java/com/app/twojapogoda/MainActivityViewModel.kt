package com.app.twojapogoda

import android.app.Application
import androidx.lifecycle.*
import com.app.twojapogoda.localDataBase.cities_table.City
import com.app.twojapogoda.localDataBase.CityRepository
import com.app.twojapogoda.localDataBase.my_cities_table.MyCity
import com.app.weatherapiaplication.WeatherApi.ApiConnection.RestWeather
import com.app.weatherapiaplication.WeatherApi.DataApi.Weather
import com.app.weatherapiaplication.WeatherApi.DataApi.WeatherApiMain
import kotlinx.coroutines.*

class MainActivityViewModel(application: Application):AndroidViewModel(application) {

    private var cityRepository : CityRepository =
        CityRepository(application)

    private val apiConnection: RestWeather = RestWeather()

    var listOfCitiesYouWant: MutableLiveData<List<City>> = MutableLiveData()

    val weather = MutableLiveData<WeatherApiMain>()

    val listofweather = MutableLiveData<List<WeatherApiMain>>()

    var refreshisOver=MutableLiveData<Boolean>()

    fun getCitiesYouWant(c: String) =
        CoroutineScope(Dispatchers.IO).launch {
            val list: ArrayList<City> = ArrayList(cityRepository.getCities(c))
            if (!cityRepository.checkCity(c) && c.isNotEmpty()) {
                list.add(0, City(c))
            }

            listOfCitiesYouWant.postValue(list)
        }

    fun setAndInsertCity(city: String) =
        CoroutineScope(Dispatchers.IO).launch {
            val helperWeatherApiMain: MutableList<WeatherApiMain> = mutableListOf()
            val myCityList = cityRepository.getAllMyCity()
            myCityList.forEach {
                helperWeatherApiMain.add(apiConnection.fetchResponse(it.cityName))
            }
            if(!myCityList.contains(MyCity(city))) {
                helperWeatherApiMain.add(apiConnection.fetchResponse(city))
            }
            listofweather.postValue(helperWeatherApiMain)
            cityRepository.insertMyCity(MyCity(city))
            cityRepository.insert(City(city))
            onLoadSearchFragment()

        }


    fun onLoadActivity()=
        CoroutineScope(Dispatchers.IO).launch {
            onLoadMainFragment()
            onLoadSearchFragment()
            refreshisOver.postValue(true)
        }
    suspend fun onLoadMainFragment(){
        val helperWeatherApiMain: MutableList<WeatherApiMain> = mutableListOf()
        val myCityList = cityRepository.getAllMyCity()
        myCityList.forEach {
            helperWeatherApiMain.add(apiConnection.fetchResponse(it.cityName))
        }
        listofweather.postValue(helperWeatherApiMain)
    }
    suspend fun onLoadSearchFragment(){
        listOfCitiesYouWant.postValue(ArrayList(cityRepository.getCities("")))

    }

    fun onLoadCurrentWeatherFragment(v: WeatherApiMain) =
        CoroutineScope(Dispatchers.IO).launch{
            weather.postValue(v)
    }

    fun deleteFromMyCity(city: MyCity)= CoroutineScope(Dispatchers.IO).launch {
        cityRepository.deleteMyCity(city)
    }

    fun resetIsRefreshing()= CoroutineScope(Dispatchers.IO).launch{
        refreshisOver.postValue(false)
    }


}