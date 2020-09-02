package com.inspirecoding.wheaterapp.weather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.databinding.ItemCurrentweatherBinding
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.util.Common
import com.inspirecoding.wheaterapp.util.DateConverters
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(currentWeatherViewHolder: CurrentWeatherViewHolder, position: Int)
    {
        currentWeatherViewHolder.itemView.setOnTouchListener { view, motionEvent ->
            view.parent.parent.requestDisallowInterceptTouchEvent(true)
            false
        }

        currentWeatherViewHolder.bind(listOfCurrentWeather[position])
    }

    class CurrentWeatherViewHolder constructor(val binding: ItemCurrentweatherBinding) : RecyclerView.ViewHolder(binding.root)
    {
        @SuppressLint("ClickableViewAccessibility")
        fun bind (weather: Pair<CurrentWeather, ForecastWeather>)
        {
            Timber.d("${weather.first}")

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

            // Init hourly forecast
            binding.rvThreehoursForecast.apply {
                weather.second.hourly?.let { _list ->
                    adapter = ThreeHoursForecastAdapter(_list)
                }
            }

            // Init daily forecast
            binding.rvDailyForecast.apply {
                val linearLayoutManager = LinearLayoutManager(binding.root.context)
                layoutManager = linearLayoutManager

                weather.second.daily?.let {  _list ->
                    val newList = _list.drop(1)
                    adapter = DailyForecastAdapter(newList)
                }
            }

            // Stop vertical scroll of ViewPager when vertically scrolling in RV
            binding.rvThreehoursForecast.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action)
                {
                    MotionEvent.ACTION_DOWN -> {
                        view.parent.requestDisallowInterceptTouchEvent(true)
                    }
                    MotionEvent.ACTION_UP -> {
                        view.parent.requestDisallowInterceptTouchEvent(false)
                    }
                }
                view.onTouchEvent(motionEvent)
            }

            binding.tvSunriseData.text = DateConverters.getTime(weather.first.sys.sunrise.toLong())
            binding.tvSunsetData.text = DateConverters.getTime(weather.first.sys.sunset.toLong())

            binding.humidity =   weather.first.main.humidity
            binding.windSpeed =   weather.first.wind.speed
            binding.feelsLike =   weather.second.current.feels_like
        }
    }





















}