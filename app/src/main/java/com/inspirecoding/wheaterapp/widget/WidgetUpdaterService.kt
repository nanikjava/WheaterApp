package com.inspirecoding.wheaterapp.widget

import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.IBinder
import com.inspirecoding.wheaterapp.util.Common

class WidgetUpdaterService : Service()
{
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val allWidgetIds = intent?.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS)
        val city = intent?.getStringExtra(Common.CITY)
        val temp = intent?.getStringExtra(Common.TEMP)
        val weatherDesc = intent?.getStringExtra(Common.WEATHER_DESC)

        if(allWidgetIds != null)
        {
            for (appWidgetId in allWidgetIds)
            {
//                updateAppWidget(
////                    this,
////                    appWidgetManager,
////                    appWidgetId,
////                    city, temp, weatherDesc)
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder?
    {
        return null
    }
}
