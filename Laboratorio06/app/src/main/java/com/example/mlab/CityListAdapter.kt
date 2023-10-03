package com.example.mlab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class CityListAdapter : RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {

    private val cities: MutableList<String> = mutableListOf()

    fun setData(cityList: JSONArray?) {
        // Limpia la lista actual y agrega las ciudades del JSONArray
        cities.clear()
        if (cityList != null) {
            for (i in 0 until cityList.length()) {
                val cityObject = cityList.optJSONObject(i)
                val cityName = cityObject?.optString("name") ?: ""
                cities.add(cityName)
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val cityName = cities[position]
        holder.bind(cityName)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityNameTextView: TextView = itemView.findViewById(R.id.cityNameTextView)

        fun bind(cityName: String) {
            cityNameTextView.text = cityName
        }
    }
}
