package com.inspirecoding.wheaterapp.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.databinding.ItemCurrentweatherBinding
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.util.WeatherDescs
import timber.log.Timber

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

//    override fun onViewAttachedToWindow(holder: CurrentWeatherViewHolder)
//    {
//        holder.setIsRecyclable(false)
//        super.onViewAttachedToWindow(holder)
//    }
//    override fun getItemViewType(position: Int): Int
//    {
//        return position
//    }
//    override fun onViewDetachedFromWindow(holder: CurrentWeatherViewHolder)
//    {
//        holder.setIsRecyclable(true)
//        super.onViewDetachedFromWindow(holder)
//    }

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

            when (currentWeather.weather?.get(0)?.description)
            {
                /** Thunderstorm **/
                WeatherDescs.THUNDERSTORM_WITH_LIGHT_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.thunderstorm_with_light_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_thunder_with_rain)
                }
                WeatherDescs.THUNDERSTORM_WITH_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.thunderstorm_with_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_thunder_with_rain)
                }
                WeatherDescs.THUNDERSTORM_WITH_HEAVY_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.thunderstorm_with_heavy_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_thunder_with_rain)
                }
                WeatherDescs.LIGHT_THUNDERSTORM.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.light_thunderstorm)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_thunder_with_rain)
                }
                WeatherDescs.THUNDERSTORM.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.thunderstorm)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_thunderstorm)
                }
                WeatherDescs.HEAVY_THUNDERSTORM.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.heavy_thunderstorm)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_thunderstorm)
                }
                WeatherDescs.RAGGED_THUNDERSTORM.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.ragged_thunderstorm)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_thunderstorm)
                }
                WeatherDescs.THUNDERSTORM_WITH_DRIZZLE.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.thunderstorm_with_light_drizzle)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_thunderstorm)
                }
                WeatherDescs.THUNDERSTORM_WITH_DRIZZLE.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.thunderstorm_with_drizzle)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_thunderstorm)
                }
                WeatherDescs.THUNDERSTORM_WITH_HEAVY_DRIZZLE.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.thunderstorm_with_heavy_drizzle)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_thunderstorm)
                }

                /** Drizzle **/
                WeatherDescs.LIGHT_INTENSITY_DRIZZLE.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.light_intensity_drizzle)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_shower_rain)
                }
                WeatherDescs.DRIZZLE.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.drizzle)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_shower_rain)
                }
                WeatherDescs.HEAVY_INTENSITY_DRIZZLE.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.heavy_intensity_drizzle)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_shower_rain)
                }
                WeatherDescs.LIGHT_INTENSITY_DRIZZLE_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.light_intensity_drizzle_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_shower_rain)
                }
                WeatherDescs.DRIZZLE_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.drizzle_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_shower_rain)
                }
                WeatherDescs.HEAVY_INTENSITY_DRIZZLE_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.heavy_intensity_drizzle_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_shower_rain)
                }
                WeatherDescs.SHOWER_RAIN_AND_DRIZZLE.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.shower_rain_and_drizzle)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_shower_rain)
                }
                WeatherDescs.HEAVY_SHOWER_RAIN_AND_DRIZZLE.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.heavy_shower_rain_and_drizzle)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_shower_rain)
                }
                WeatherDescs.SHOWER_DRIZZLE.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.shower_drizzle)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_shower_rain)
                }

                /** Rain **/
                WeatherDescs.LIGHT_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.light_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_light_rain)
                }
                WeatherDescs.MODERATE_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.moderate_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_light_rain)
                }
                WeatherDescs.HEAVY_INTENSITY_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.heavy_intensity_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_rain)
                }
                WeatherDescs.VERY_HEAVY_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.very_heavy_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_rain)
                }
                WeatherDescs.EXTREME_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.extreme_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_rain)
                }
                WeatherDescs.FREEZING_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.freezing_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_rain)
                }
                WeatherDescs.LIGHT_INTENSITY_SHOWER_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.light_intensity_shower_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_rain)
                }
                WeatherDescs.SHOWER_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.shower_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_rain)
                }
                WeatherDescs.HEAVY_INTENSITY_SHOWER_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.heavy_intensity_shower_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_rain)
                }
                WeatherDescs.RAGGED_SHOWER_RAIN.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.ragged_shower_rain)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_rain)
                }

                /** Snow **/
                WeatherDescs.LIGHT_SNOW.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.light_snow)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_snow)
                }
                WeatherDescs.SNOW.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.snow)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_snow)
                }
                WeatherDescs.HEAVY_SNOW.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.heavy_snow)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_snow)
                }
                WeatherDescs.SLEET.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.sleet)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_snow)
                }
                WeatherDescs.LIGHT_SHOWER_SLEET.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.light_shower_sleet)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_snow)
                }
                WeatherDescs.SHOWER_SLEET.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.shower_sleet)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_snow)
                }
                WeatherDescs.LIGHT_RAIN_AND_SNOW.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.light_rain_and_snow)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_snow)
                }
                WeatherDescs.RAIN_AND_SNOW.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.rain_and_snow)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_snow)
                }
                WeatherDescs.LIGHT_SHOWER_SNOW.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.light_shower_snow)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_snow)
                }
                WeatherDescs.SHOWER_SNOW.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.shower_snow)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_snow)
                }
                WeatherDescs.HEAVY_SHOWER_SNOW.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.heavy_shower_snow)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_snow)
                }

                /** Atmosphere **/
                WeatherDescs.MIST.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.mist)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_mist)
                }
                WeatherDescs.SMOKE.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.smoke)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_mist)
                }
                WeatherDescs.HAZE.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.haze)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_mist)
                }
                WeatherDescs.SAND_DUST_WHIRLS.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.sand_and_dust_whirls)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_mist)
                }
                WeatherDescs.FOG.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.fog)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_mist)
                }
                WeatherDescs.SAND.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.sand)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_mist)
                }
                WeatherDescs.DUST.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.dust)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_mist)
                }
                WeatherDescs.VOLCANIC_ASH.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.volcanic_ash)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_mist)
                }
                WeatherDescs.SQUALLS.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.squalls)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_mist)
                }
                WeatherDescs.TORNADO.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.tornado)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_mist)
                }

                /** Clear **/
                WeatherDescs.CLEAR_SKY.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.clear_sky)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_clear_sky)
                }

                /** Clouds **/
                WeatherDescs.FEW_CLOUDS.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.few_clouds)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_scattered_clouds)
                }
                WeatherDescs.SCATTERED_CLOUDS.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.scattered_clouds)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_scattered_clouds)
                }
                WeatherDescs.BROKEN_CLOUDS.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.broken_clouds)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_broken_clouds)
                }
                WeatherDescs.OVERCAST_CLOUDS.desc -> {
                    binding.weatherDesc = binding.root.context.getString(R.string.overcast_clouds)
                    binding.ivWeatherIcon.setImageResource(R.drawable.ic_broken_clouds)
                }
            }
        }
    }
}