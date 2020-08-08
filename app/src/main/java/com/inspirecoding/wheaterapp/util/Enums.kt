package com.inspirecoding.wheaterapp.util

enum class WeatherDescs(val desc: String)
{
    /** Thunderstorm **/
    THUNDERSTORM_WITH_LIGHT_RAIN("thunderstorm with light rain"),
    THUNDERSTORM_WITH_RAIN("thunderstorm with rain"),
    THUNDERSTORM_WITH_HEAVY_RAIN("thunderstorm with heavy rain"),
    LIGHT_THUNDERSTORM("light thunderstorm"),
    THUNDERSTORM("thunderstorm"),
    HEAVY_THUNDERSTORM("heavy thunderstorm"),
    RAGGED_THUNDERSTORM("ragged thunderstorm"),
    THUNDERSTORM_WITH_LIGHT_DRIZZLE("thunderstorm with light drizzle"),
    THUNDERSTORM_WITH_DRIZZLE("thunderstorm with drizzle"),
    THUNDERSTORM_WITH_HEAVY_DRIZZLE("thunderstorm with heavy drizzle"),

    /** Drizzle **/
    LIGHT_INTENSITY_DRIZZLE("light intensity drizzle"),
    DRIZZLE("drizzle"),
    HEAVY_INTENSITY_DRIZZLE("heavy intensity drizzle"),
    LIGHT_INTENSITY_DRIZZLE_RAIN("light intensity drizzle rain"),
    DRIZZLE_RAIN("drizzle rain"),
    HEAVY_INTENSITY_DRIZZLE_RAIN("heavy intensity drizzle rain\t"),
    SHOWER_RAIN_AND_DRIZZLE("shower rain and drizzle"),
    HEAVY_SHOWER_RAIN_AND_DRIZZLE("heavy shower rain and drizzle"),
    SHOWER_DRIZZLE("shower drizzle"),

    /** Rain **/
    LIGHT_RAIN("light rain"),
    MODERATE_RAIN("moderate rain"),
    HEAVY_INTENSITY_RAIN("heavy intensity rain"),
    VERY_HEAVY_RAIN("very heavy rain"),
    EXTREME_RAIN("extreme rain"),
    FREEZING_RAIN("freezing rain"),
    LIGHT_INTENSITY_SHOWER_RAIN("light intensity shower rain"),
    SHOWER_RAIN("shower rain"),
    HEAVY_INTENSITY_SHOWER_RAIN("heavy intensity shower rain"),
    RAGGED_SHOWER_RAIN("ragged shower rain"),

    /** Rain **/
    LIGHT_SNOW("light snow"),
    SNOW("Snow"),
    HEAVY_SNOW("Heavy snow"),
    SLEET("Sleet"),
    LIGHT_SHOWER_SLEET("Light shower sleet"),
    SHOWER_SLEET("Shower sleet"),
    LIGHT_RAIN_AND_SNOW("Light rain and snow"),
    RAIN_AND_SNOW("Rain and snow"),
    LIGHT_SHOWER_SNOW("Light shower snow"),
    SHOWER_SNOW("Shower snow"),
    HEAVY_SHOWER_SNOW("Heavy shower snow"),

    /** Atmosphere **/
    MIST("mist"),
    SMOKE("Smoke"),
    HAZE("Haze"),
    SAND_DUST_WHIRLS("sand/ dust whirls"),
    FOG("fog"),
    SAND("sand"),
    DUST("dust"),
    VOLCANIC_ASH("volcanic ash"),
    SQUALLS("squalls"),
    TORNADO("tornado"),

    /** Clear **/
    CLEAR_SKY("clear sky"),

    /** Clouds **/
    FEW_CLOUDS("few clouds"),
    SCATTERED_CLOUDS("scattered clouds"),
    BROKEN_CLOUDS("broken clouds"),
    OVERCAST_CLOUDS("overcast clouds")
}