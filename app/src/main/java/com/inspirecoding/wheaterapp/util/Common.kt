package com.inspirecoding.wheaterapp.util

import android.content.Context
import com.inspirecoding.wheaterapp.R
import timber.log.Timber

object Common
{
    const val WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/"
    private const val API_KEY = "5284852183d08ec2ab5bb51447fddf39"

    const val SHARED_PREFERENCES = "shared_preferences"

    const val METRIC = "metric"
    const val IMPERIAL = "imperial"

    val ANIMATION_DURATION = 2000.toLong()

    fun createEndUrl_currentWeather(city: String, unit: String) : String
    {
        return java.lang.String.format (
            "weather?q=%s&units=%s&appid=%s",
            city, unit, API_KEY)
    }
    fun createEndUrl_forecastWeather(latitude: Double, longitude: Double, unit: String) : String
    {
        val stringUrl = java.lang.String.format (
            "onecall?lat=%s&lon=%s&units=%s&appid=%s",
            latitude, longitude, unit, API_KEY)
        Timber.d(stringUrl)
        return stringUrl
    }
    
    fun getWeatherDescription(description: String?, context: Context) : Pair<String , Int>
    {
        var pair: Pair<String, Int> = Pair("", 0)

        when (description)
        {
            /** Thunderstorm **/
            WeatherDescs.THUNDERSTORM_WITH_LIGHT_RAIN.desc -> {
                pair = Pair(
                    context.getString(R.string.thunderstorm_with_light_rain),
                    R.drawable.ic_thunder_with_rain
                )
            }
            WeatherDescs.THUNDERSTORM_WITH_RAIN.desc -> {
                pair = Pair(context.getString(
                    R.string.thunderstorm_with_rain),
                    R.drawable.ic_thunder_with_rain
                )
            }
            WeatherDescs.THUNDERSTORM_WITH_HEAVY_RAIN.desc -> {
                pair = Pair(context.getString(R.string.thunderstorm_with_heavy_rain),
                R.drawable.ic_thunder_with_rain)
            }
            WeatherDescs.LIGHT_THUNDERSTORM.desc -> {
                pair = Pair(context.getString(R.string.light_thunderstorm),
                R.drawable.ic_thunder_with_rain)
            }
            WeatherDescs.THUNDERSTORM.desc -> {
                pair = Pair(context.getString(R.string.thunderstorm),
                R.drawable.ic_thunderstorm)
            }
            WeatherDescs.HEAVY_THUNDERSTORM.desc -> {
                pair = Pair(context.getString(R.string.heavy_thunderstorm),
                R.drawable.ic_thunderstorm)
            }
            WeatherDescs.RAGGED_THUNDERSTORM.desc -> {
                pair = Pair(context.getString(R.string.ragged_thunderstorm),
                R.drawable.ic_thunderstorm)
            }
            WeatherDescs.THUNDERSTORM_WITH_LIGHT_DRIZZLE.desc -> {
                pair = Pair(context.getString(R.string.thunderstorm_with_light_drizzle),
                R.drawable.ic_thunderstorm)
            }
            WeatherDescs.THUNDERSTORM_WITH_DRIZZLE.desc -> {
                pair = Pair(context.getString(R.string.thunderstorm_with_drizzle),
                R.drawable.ic_thunderstorm)
            }
            WeatherDescs.THUNDERSTORM_WITH_HEAVY_DRIZZLE.desc -> {
                pair = Pair(context.getString(R.string.thunderstorm_with_heavy_drizzle),
                R.drawable.ic_thunderstorm)
            }

            /** Drizzle **/
            WeatherDescs.LIGHT_INTENSITY_DRIZZLE.desc -> {
                pair = Pair(context.getString(R.string.light_intensity_drizzle),
                R.drawable.ic_shower_rain)
            }
            WeatherDescs.DRIZZLE.desc -> {
                pair = Pair(context.getString(R.string.drizzle),
                R.drawable.ic_shower_rain)
            }
            WeatherDescs.HEAVY_INTENSITY_DRIZZLE.desc -> {
                pair = Pair(context.getString(R.string.heavy_intensity_drizzle),
                R.drawable.ic_shower_rain)
            }
            WeatherDescs.LIGHT_INTENSITY_DRIZZLE_RAIN.desc -> {
                pair = Pair(context.getString(R.string.light_intensity_drizzle_rain),
                R.drawable.ic_shower_rain)
            }
            WeatherDescs.DRIZZLE_RAIN.desc -> {
                pair = Pair(context.getString(R.string.drizzle_rain),
                R.drawable.ic_shower_rain)
            }
            WeatherDescs.HEAVY_INTENSITY_DRIZZLE_RAIN.desc -> {
                pair = Pair(context.getString(R.string.heavy_intensity_drizzle_rain),
                R.drawable.ic_shower_rain)
            }
            WeatherDescs.SHOWER_RAIN_AND_DRIZZLE.desc -> {
                pair = Pair(context.getString(R.string.shower_rain_and_drizzle),
                R.drawable.ic_shower_rain)
            }
            WeatherDescs.HEAVY_SHOWER_RAIN_AND_DRIZZLE.desc -> {
                pair = Pair(context.getString(R.string.heavy_shower_rain_and_drizzle),
                R.drawable.ic_shower_rain)
            }
            WeatherDescs.SHOWER_DRIZZLE.desc -> {
                pair = Pair(context.getString(R.string.shower_drizzle),
                R.drawable.ic_shower_rain)
            }

            /** Rain **/
            WeatherDescs.LIGHT_RAIN.desc -> {
                pair = Pair(context.getString(R.string.light_rain),
                R.drawable.ic_light_rain)
            }
            WeatherDescs.MODERATE_RAIN.desc -> {
                pair = Pair(context.getString(R.string.moderate_rain),
                R.drawable.ic_light_rain)
            }
            WeatherDescs.HEAVY_INTENSITY_RAIN.desc -> {
                pair = Pair(context.getString(R.string.heavy_intensity_rain),
                R.drawable.ic_rain)
            }
            WeatherDescs.VERY_HEAVY_RAIN.desc -> {
                pair = Pair(context.getString(R.string.very_heavy_rain),
                R.drawable.ic_rain)
            }
            WeatherDescs.EXTREME_RAIN.desc -> {
                pair = Pair(context.getString(R.string.extreme_rain),
                R.drawable.ic_rain)
            }
            WeatherDescs.FREEZING_RAIN.desc -> {
                pair = Pair(context.getString(R.string.freezing_rain),
                R.drawable.ic_rain)
            }
            WeatherDescs.LIGHT_INTENSITY_SHOWER_RAIN.desc -> {
                pair = Pair(context.getString(R.string.light_intensity_shower_rain),
                R.drawable.ic_rain)
            }
            WeatherDescs.SHOWER_RAIN.desc -> {
                pair = Pair(context.getString(R.string.shower_rain),
                R.drawable.ic_rain)
            }
            WeatherDescs.HEAVY_INTENSITY_SHOWER_RAIN.desc -> {
                pair = Pair(context.getString(R.string.heavy_intensity_shower_rain),
                R.drawable.ic_rain)
            }
            WeatherDescs.RAGGED_SHOWER_RAIN.desc -> {
                pair = Pair(context.getString(R.string.ragged_shower_rain),
                R.drawable.ic_rain)
            }

            /** Snow **/
            WeatherDescs.LIGHT_SNOW.desc -> {
                pair = Pair(context.getString(R.string.light_snow),
                R.drawable.ic_snow)
            }
            WeatherDescs.SNOW.desc -> {
                pair = Pair(context.getString(R.string.snow),
                R.drawable.ic_snow)
            }
            WeatherDescs.HEAVY_SNOW.desc -> {
                pair = Pair(context.getString(R.string.heavy_snow),
                R.drawable.ic_snow)
            }
            WeatherDescs.SLEET.desc -> {
                pair = Pair(context.getString(R.string.sleet),
                R.drawable.ic_snow)
            }
            WeatherDescs.LIGHT_SHOWER_SLEET.desc -> {
                pair = Pair(context.getString(R.string.light_shower_sleet),
                R.drawable.ic_snow)
            }
            WeatherDescs.SHOWER_SLEET.desc -> {
                pair = Pair(context.getString(R.string.shower_sleet),
                R.drawable.ic_snow)
            }
            WeatherDescs.LIGHT_RAIN_AND_SNOW.desc -> {
                pair = Pair(context.getString(R.string.light_rain_and_snow),
                R.drawable.ic_snow)
            }
            WeatherDescs.RAIN_AND_SNOW.desc -> {
                pair = Pair(context.getString(R.string.rain_and_snow),
                R.drawable.ic_snow)
            }
            WeatherDescs.LIGHT_SHOWER_SNOW.desc -> {
                pair = Pair(context.getString(R.string.light_shower_snow),
                R.drawable.ic_snow)
            }
            WeatherDescs.SHOWER_SNOW.desc -> {
                pair = Pair(context.getString(R.string.shower_snow),
                R.drawable.ic_snow)
            }
            WeatherDescs.HEAVY_SHOWER_SNOW.desc -> {
                pair = Pair(context.getString(R.string.heavy_shower_snow),
                R.drawable.ic_snow)
            }

            /** Atmosphere **/
            WeatherDescs.MIST.desc -> {
                pair = Pair(context.getString(R.string.mist),
                R.drawable.ic_mist)
            }
            WeatherDescs.SMOKE.desc -> {
                pair = Pair(context.getString(R.string.smoke),
                R.drawable.ic_mist)
            }
            WeatherDescs.HAZE.desc -> {
                pair = Pair(context.getString(R.string.haze),
                R.drawable.ic_mist)
            }
            WeatherDescs.SAND_DUST_WHIRLS.desc -> {
                pair = Pair(context.getString(R.string.sand_and_dust_whirls),
                R.drawable.ic_mist)
            }
            WeatherDescs.FOG.desc -> {
                pair = Pair(context.getString(R.string.fog),
                R.drawable.ic_mist)
            }
            WeatherDescs.SAND.desc -> {
                pair = Pair(context.getString(R.string.sand),
                R.drawable.ic_mist)
            }
            WeatherDescs.DUST.desc -> {
                pair = Pair(context.getString(R.string.dust),
                R.drawable.ic_mist)
            }
            WeatherDescs.VOLCANIC_ASH.desc -> {
                pair = Pair(context.getString(R.string.volcanic_ash),
                R.drawable.ic_mist)
            }
            WeatherDescs.SQUALLS.desc -> {
                pair = Pair(context.getString(R.string.squalls),
                R.drawable.ic_mist)
            }
            WeatherDescs.TORNADO.desc -> {
                pair = Pair(context.getString(R.string.tornado),
                R.drawable.ic_mist)
            }

            /** Clear **/
            WeatherDescs.CLEAR_SKY.desc -> {
                pair = Pair(context.getString(R.string.clear_sky),
                R.drawable.ic_clear_sky)
            }

            /** Clouds **/
            WeatherDescs.FEW_CLOUDS.desc -> {
                pair = Pair(context.getString(R.string.few_clouds),
                R.drawable.ic_scattered_clouds)
            }
            WeatherDescs.SCATTERED_CLOUDS.desc -> {
                pair = Pair(context.getString(R.string.scattered_clouds),
                R.drawable.ic_scattered_clouds)
            }
            WeatherDescs.BROKEN_CLOUDS.desc -> {
                pair = Pair(context.getString(R.string.broken_clouds),
                R.drawable.ic_broken_clouds)
            }
            WeatherDescs.OVERCAST_CLOUDS.desc -> {
                pair = Pair(context.getString(R.string.overcast_clouds),
                R.drawable.ic_broken_clouds)
            }
        }
        return pair
    }
}