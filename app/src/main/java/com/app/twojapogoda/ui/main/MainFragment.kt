package com.app.twojapogoda.ui.main

import android.annotation.SuppressLint
import android.app.IntentService
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.ThemedSpinnerAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.twojapogoda.MainActivity
import com.app.twojapogoda.MainActivityViewModel
import com.app.twojapogoda.R
import com.app.twojapogoda.R.id.container
import com.app.twojapogoda.adapters.MainAdapter
import com.app.twojapogoda.localDataBase.my_cities_table.MyCity
import com.app.twojapogoda.ui.current_weather.CurrentWeatherFragment
import com.app.twojapogoda.ui.search.SearchFragment
import com.app.weatherapiaplication.WeatherApi.DataApi.WeatherApiMain
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_fragment.*
import java.net.URL

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainActivityViewModel = ViewModelProviders.of(activity!!).get(MainActivityViewModel::class.java)
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.main_fragment_RV)
        val adapter = MainAdapter(::onItemClick, requireContext(), ::deleteFromMyCity)
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(context)
        mainActivityViewModel.listofweather.observe(viewLifecycleOwner, Observer {
            adapter.newList(ArrayList(it))
        })

        refreshLayout.setOnRefreshListener {
            mainActivityViewModel.onLoadActivity()
        }
        mainActivityViewModel.refreshisOver.observe(viewLifecycleOwner, Observer {
            if(it==true){
                refreshLayout.isRefreshing=false
                mainActivityViewModel.resetIsRefreshing()
            }
        })
    }
    private fun onItemClick(v:WeatherApiMain){
        mainActivityViewModel.onLoadCurrentWeatherFragment(v)
        activity!!.viewPager.currentItem=2
    }

    private fun deleteFromMyCity(city:MyCity){
        mainActivityViewModel.deleteFromMyCity(city)
    }

}
