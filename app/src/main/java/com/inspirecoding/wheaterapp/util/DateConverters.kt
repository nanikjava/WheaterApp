package com.inspirecoding.wheaterapp.util

import android.content.Context
import com.inspirecoding.wheaterapp.R
import java.util.*

object DateConverters
{
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply {
            timeInMillis = value
        }

    fun getFormattedTimeAgo(
        context: Context,
        pastTime: Long) : String
    {
        val currentTimeInMillis: Long = System.currentTimeMillis()

        val difference = currentTimeInMillis - pastTime

        return when(difference)
        {
            in 0..9999 -> context.getString(R.string.updated_now)
            in 10000..119999 -> context.getString(R.string.one_minute_ago)
            in 120000..3599999 -> context.getString(R.string.minutes_ago, (difference / 60000).toDouble())
            in 3600000..7199999 -> context.getString(R.string.one_hour_ago)
            in 7200000..86399999 -> context.getString(R.string.hours_ago, (difference / 3600000).toDouble())
            in 86400000..172799999 -> context.getString(R.string.one_day_ago)
            in 172800000..604799999 -> context.getString(R.string.hours_ago, (difference / 86400000).toDouble())
            else -> difference.convertToDateString()
        }
    }

    fun getDateForThreeHoursForecast(timeStamp: Long) : String
    {
        val currentTimeInMillis: Long = System.currentTimeMillis()
        val futureDate = Calendar.getInstance().apply {
            timeInMillis = timeStamp
        }


        val today = Calendar.getInstance().today()

        return datestampToCalendar(timeStamp).time.toLocaleString()
    }
}