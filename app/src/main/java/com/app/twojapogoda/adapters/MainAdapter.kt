package com.app.twojapogoda.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.twojapogoda.MainActivity
import com.app.twojapogoda.R
import com.app.twojapogoda.adapters.MainAdapter.ViewHolder
import com.app.twojapogoda.localDataBase.my_cities_table.MyCity
import com.app.weatherapiaplication.WeatherApi.DataApi.WeatherApiMain
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.template_item_city_detail.view.*
import kotlinx.android.synthetic.main.template_list_search.view.*
import java.net.URL

class MainAdapter(onItemClick:(v:WeatherApiMain) -> Unit , val context:Context, deleteMyCity:(v:MyCity) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    private var listOfCitiesWeather = ArrayList<WeatherApiMain>()
    private var onItemClick:((WeatherApiMain)-> Unit)? = null
    private var deleteMyCity:((MyCity)->Unit)? = null

    init {
        this.onItemClick=onItemClick
        this.deleteMyCity=deleteMyCity
    }

    fun newList(list : ArrayList<WeatherApiMain>){
        listOfCitiesWeather = list
        notifyDataSetChanged()
    }
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val cityNameTextView = view.cityTextViewCard!!
        val countryNameTextView = view.countryTextViewCard!!
        val degreeTextView = view.degreeTextViewCard!!
        val timeTextView = view.timeTextViewCard!!
        val layout = view.template_city_card_view!!
        val image = view.imageWeatherCard!!
        val deleteButton= view.deleteButton!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_item_city_detail, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfCitiesWeather.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = listOfCitiesWeather[position]
        holder.cityNameTextView.text= pos.name
        holder.countryNameTextView.text= pos.sys.country
        holder.degreeTextView.text= pos.main.getTempInCelcius()
        holder.timeTextView.text = pos.wind.getWindSpeed()
        holder.layout.setCardBackgroundColor(pos.weather.first().returnColor())

        val url = URL(pos.weather.first().returnIcon())
        Glide.with((context))
            .load(url)
            .into(holder.image)

        holder.deleteButton.setOnClickListener {
            deleteMyCity?.let { it1 -> it1(MyCity(pos.name)) }
            listOfCitiesWeather.remove(pos)
            notifyDataSetChanged()
        }


        holder.itemView.setOnClickListener {
            onItemClick?.let { it1 -> it1(pos) }
        }
    }
}