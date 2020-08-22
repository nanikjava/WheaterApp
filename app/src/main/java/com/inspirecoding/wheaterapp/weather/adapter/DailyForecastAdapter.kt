package com.inspirecoding.wheaterapp.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.databinding.ItemDailyforecastBinding
import com.inspirecoding.wheaterapp.model.Daily
import com.inspirecoding.wheaterapp.util.Common
import com.inspirecoding.wheaterapp.util.DateConverters

class DailyForecastAdapter
    constructor(val listOfForecastWeather : List<Daily>
    ) : RecyclerView.Adapter<DailyForecastAdapter.DailyForecastViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder
    {
        val layoutInfalter = LayoutInflater.from(parent.context)
        val binding : ItemDailyforecastBinding = DataBindingUtil.inflate(
            layoutInfalter,
            R.layout.item_dailyforecast,
            parent, false
        )
        return DailyForecastViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfForecastWeather.size

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int)
    {
        holder.bind(listOfForecastWeather[position])
    }


    class DailyForecastViewHolder constructor (val binding: ItemDailyforecastBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind (daily: Daily)
        {
            binding.day = DateConverters.getDateForThreeHoursForecast(daily.dt, binding.root.context).substringBeforeLast("\n")

            binding.morningTemp = daily.temp?.morn
            binding.dayTemp = daily.temp?.day

            val weatherDesc = Common.getWeatherDescription(
                daily.weather?.get(0)?.description, binding.root.context
            )
            binding.ivWeatherIcon.setImageResource(weatherDesc.second)
        }
    }
}