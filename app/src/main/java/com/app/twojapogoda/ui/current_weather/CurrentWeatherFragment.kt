package com.app.twojapogoda.ui.current_weather

import android.content.Context
import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.AttributeSet
import android.view.*
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.app.twojapogoda.MainActivityViewModel

import com.app.twojapogoda.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.android.synthetic.main.main_activity.*
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherFragmentViewModel
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(false)
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherFragmentViewModel::class.java)
        mainActivityViewModel = ViewModelProviders.of(activity!!).get(MainActivityViewModel::class.java)

        val glide = Glide.with(requireContext())
        glide.load(URL("https://cdn3.iconfinder.com/data/icons/wpzoom-developer-icon-set/500/128-512.png")).into(imageWeatherHumidity)
        glide.load(URL("https://cdn0.iconfinder.com/data/icons/good-weather-1/96/weather_icons-65-512.png")).into(imageWeatherMaxTemp)
        glide.load(URL("https://cdn0.iconfinder.com/data/icons/good-weather-1/96/weather_icons-63-512.png")).into(imageWeatherMinTemp)
        glide.load(URL("https://cdn2.iconfinder.com/data/icons/miscellaneous-12/24/miscellaneous-25-512.png")).into(imageWeatherPressure)
        glide.load(URL("https://cdn3.iconfinder.com/data/icons/meteocons/512/wind-symbol-512.png")).into(imageWeatherWind)
        glide.load(URL("https://cdn2.iconfinder.com/data/icons/freecns-cumulus/16/519660-164_QuestionMark-512.png")).into(imageWeatherMainIcon)

        mainActivityViewModel.weather.observe(viewLifecycleOwner, Observer {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd MM yyyy")
            val formatted = current.format(formatter)
            curr_frag_layout.setBackgroundColor(it.weather.first().returnColor())
            glide.load(URL(it.weather.first().returnIcon())).into(imageWeatherMainIcon)
            textViewPressure.text=it.main.getPressurehPa()
            textViewHumidity.text=it.main.getHumidityPercent()
            textViewTempMax.text=it.main.getTempMaxInCelcius()
            textViewTempMin.text=it.main.getTempMinInCelcius()
            nameOfYourCityCurrentFrag.text=it.name
            textViewTimeSunRise.text=it.sys.getSunsetFormat()
            textViewTimeSunSet.text=it.sys.getSunriseFormat()
            textViewWind.text=it.wind.getWindSpeed()
            textViewDate.text=formatted
        })
        mainActivityViewModel.listofweather.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()) {
                mainActivityViewModel.onLoadCurrentWeatherFragment(it.first())
            }
        })


    }



}
