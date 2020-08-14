package com.inspirecoding.wheaterapp.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.databinding.ItemCurrentweatherBinding
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.util.Common
import com.inspirecoding.wheaterapp.util.DateConverters

class CurrentWeatherAdapter (
    val listOfCurrentWeather: MutableList<CurrentWeather>
) : RecyclerView.Adapter<CurrentWeatherAdapter.CurrentWeatherViewHolder>()
{
    fun addNewCurrentWeather(currentWeather: CurrentWeather)
    {
        listOfCurrentWeather.add(currentWeather)
        notifyItemChanged(0)
    }
    fun updateNewCurrentWeather(currentWeather: CurrentWeather)
    {
        val index = listOfCurrentWeather.indexOfFirst {
            it.name == currentWeather.name
        }
        listOfCurrentWeather[index] = currentWeather
        notifyItemChanged(index)
    }
    fun updateAllItems(newList: MutableList<CurrentWeather>)
    {
        listOfCurrentWeather.clear()
        listOfCurrentWeather.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentWeatherViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemCurrentweatherBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_currentweather,
            parent, false
        )

        return CurrentWeatherViewHolder(binding)
    }

    override fun getItemCount() = listOfCurrentWeather.size

    override fun onBindViewHolder(currentWeatherViewHolder: CurrentWeatherViewHolder, position: Int)
    {
        currentWeatherViewHolder.bind(listOfCurrentWeather[position])
    }


    inner class CurrentWeatherViewHolder constructor(val binding: ItemCurrentweatherBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind (currentWeather: CurrentWeather)
        {
            binding.city = currentWeather.name
            binding.main = currentWeather.main

            currentWeather.dt?.let { _dateTime ->
                binding.date = DateConverters.getFormattedTimeAgo(binding.root.context, _dateTime * 1000)
            }

            val weatherDesc = Common.getWeatherDescription(
                currentWeather.weather?.get(0)?.description, binding.root.context
            )
            binding.weatherDesc = weatherDesc.first
            binding.ivWeatherIcon.setImageResource(weatherDesc.second)

//            binding.rvThreehoursForecast.apply {
//                currentWeather.second.list?.let { _list ->
//                    adapter = ThreeHoursForecastAdapter(_list)
//                }
//            }
        }
    }
}