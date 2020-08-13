package com.inspirecoding.wheaterapp.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.databinding.ItemThreehoursForecastBinding
import com.inspirecoding.wheaterapp.model.List
import com.inspirecoding.wheaterapp.util.DateConverters

class ThreeHoursForecastAdapter (
    val listOfForecastWeather : kotlin.collections.List<List>
) : RecyclerView.Adapter<ThreeHoursForecastAdapter.ThreeHoursForecastViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ThreeHoursForecastViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemThreehoursForecastBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_threehours_forecast,
            parent, false
        )
        return ThreeHoursForecastViewHolder(binding)
    }

    override fun getItemCount() = listOfForecastWeather.size

    override fun onBindViewHolder(holder: ThreeHoursForecastViewHolder, position: Int)
    {
        holder.bind(listOfForecastWeather[position])
    }

    inner class ThreeHoursForecastViewHolder constructor(val binding: ItemThreehoursForecastBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind (list: List)
        {
            binding.tvDate.text = DateConverters.getDateForThreeHoursForecast(list.dt.toLong())
        }
    }
}