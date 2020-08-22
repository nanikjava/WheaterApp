package com.inspirecoding.wheaterapp.util

import android.content.Context
import com.inspirecoding.wheaterapp.R
import timber.log.Timber
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

    fun getDateForThreeHoursForecast(timeStamp: Long, context: Context) : String
    {
        val today = datestampToCalendar(System.currentTimeMillis())
        val dayOfToday = today.get(Calendar.DAY_OF_MONTH)

        val futureDate = datestampToCalendar(timeStamp*1000)
        val dayOfFutureDay = futureDate.get(Calendar.DAY_OF_MONTH)

        return when(dayOfFutureDay-dayOfToday)
        {
            0 ->  getTodayAndDateString(futureDate.time, context)
            1 ->  getTomorrowAndDateString(futureDate.time, context)
            else -> getOtherDaysDateString(futureDate, context)
        }
    }

    fun getTime(timeStamp: Long) : String
    {
        val time = datestampToCalendar(timeStamp*1000)
        return time.time.toLocaleString().substringAfterLast(" ")
    }

    private fun getTodayAndDateString(date: Date, context: Context) : String
    {
        return context.getString(R.string.today___, date.toLocaleString().substringAfterLast(" ").substringBeforeLast(":"))
    }
    private fun getTomorrowAndDateString(date: Date, context: Context): String
    {
        return context.getString(R.string.tomorrow___, date.toLocaleString().substringAfterLast(" ").substringBeforeLast(":"))
    }
    private fun getOtherDaysDateString(calendar: Calendar, context: Context): String
    {
        val res = context.resources
        val daysOfWeek = res.getStringArray(R.array.days_of_week)

        val intOfDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1
        val stringOfDayOfWeek = daysOfWeek[intOfDayOfWeek]

        return "$stringOfDayOfWeek\n" +
                "${calendar.time.toLocaleString().substringAfterLast(" ").substringBeforeLast(":")}"
    }
}