package com.inspirecoding.wheaterapp.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.databinding.ItemThreehoursForecastBinding
import com.inspirecoding.wheaterapp.model.Hourly
import com.inspirecoding.wheaterapp.util.Common
import com.inspirecoding.wheaterapp.util.DateConverters

class ThreeHoursForecastAdapter (
    val listOfForecastWeather : List<Hourly>
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
        fun bind (hourly: Hourly)
        {
            binding.tvDate.text = DateConverters.getDateForThreeHoursForecast(hourly.dt, binding.root.context)

            val weatherDesc = Common.getWeatherDescription(
                hourly.weather?.get(0)?.description, binding.root.context
            )
            binding.ivWeatherIcon.setImageResource(weatherDesc.second)

            binding.temp = hourly.temp
        }
    }
}