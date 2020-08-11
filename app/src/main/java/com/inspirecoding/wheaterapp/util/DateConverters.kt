package com.inspirecoding.wheaterapp.util

import java.util.*

object DateConverters
{
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply {
            timeInMillis = value
        }

    fun convertToDateString(timeStamp: Long) : String
    {
        return Date(timeStamp).toLocaleString().substringBeforeLast(" ")
    }
}