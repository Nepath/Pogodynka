package com.app.twojapogoda.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.twojapogoda.R
import com.app.twojapogoda.adapters.SearchAdapter.ViewHolder
import com.app.twojapogoda.localDataBase.cities_table.City
import kotlinx.android.synthetic.main.template_list_search.view.*

class SearchAdapter(onRecViewItemClick: (c: String) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    private var listOfCities = ArrayList<City>()
    private var onRecViewItemClick: ((String) -> Unit)? = null

    init {
        this.onRecViewItemClick = onRecViewItemClick
    }

    fun newList(list: ArrayList<City>) {
        listOfCities = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityNameTextView = view.cityNameTextView!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_list_search, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfCities.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = listOfCities[position]
        holder.cityNameTextView.text = pos.cityName

        holder.itemView.setOnClickListener {

            onRecViewItemClick?.let { it1 -> it1(holder.cityNameTextView.text.toString()) }

        }
    }
}
