package com.app.twojapogoda.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.app.twojapogoda.ui.current_weather.CurrentWeatherFragment
import com.app.twojapogoda.ui.main.MainFragment
import com.app.twojapogoda.ui.search.SearchFragment

class SlideFragmentPagerAdapter(fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            1 -> MainFragment.newInstance()
            0 -> SearchFragment.newInstance()
            2 -> CurrentWeatherFragment.newInstance()

            else -> MainFragment.newInstance()
        }
    }

    override fun getCount() = 3



}