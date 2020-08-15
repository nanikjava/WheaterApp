package com.inspirecoding.wheaterapp.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.databinding.ItemCurrentweatherBinding
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.model.List
import com.inspirecoding.wheaterapp.util.Common
import com.inspirecoding.wheaterapp.util.DateConverters
import com.inspirecoding.wheaterapp.util.Status
import com.inspirecoding.wheaterapp.weather.WeatherViewModel
import timber.log.Timber

class CurrentWeatherAdapter (
    val listOfCurrentWeather: MutableList<Pair<CurrentWeather, ForecastWeather>>
) : RecyclerView.Adapter<CurrentWeatherAdapter.CurrentWeatherViewHolder>()
{
    fun addNewCurrentWeather(weather: Pair<CurrentWeather, ForecastWeather>)
    {
        listOfCurrentWeather.add(weather)
        notifyItemChanged(0)
    }
    fun updateNewCurrentWeather(weather: Pair<CurrentWeather, ForecastWeather>)
    {
        val index = listOfCurrentWeather.indexOfFirst {
            it.first.name == weather.first.name
        }

        listOfCurrentWeather[index] = weather

        notifyItemChanged(index)
    }
    fun updateAllItems(weather: MutableList<Pair<CurrentWeather, ForecastWeather>>)
    {
        listOfCurrentWeather.clear()
        listOfCurrentWeather.addAll(weather)
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
        fun bind (weather: Pair<CurrentWeather, ForecastWeather>)
        {
            binding.city = weather.first.name
            binding.main = weather.first.main

            weather.first.dt?.let { _dateTime ->
                binding.date = DateConverters.getFormattedTimeAgo(binding.root.context, _dateTime * 1000)
            }

            val weatherDesc = Common.getWeatherDescription(
                weather.first.weather?.get(0)?.description, binding.root.context
            )
            binding.weatherDesc = weatherDesc.first
            binding.ivWeatherIcon.setImageResource(weatherDesc.second)

            binding.rvThreehoursForecast.apply {
                weather.second.list?.let { _list ->
                    adapter = ThreeHoursForecastAdapter(_list)
                }
            }
        }

        private fun initRecyclerView(listOfCities: MutableList<List>)
        {
            binding.rvThreehoursForecast.apply {
                adapter = ThreeHoursForecastAdapter(listOfCities)
            }
        }
    }
}