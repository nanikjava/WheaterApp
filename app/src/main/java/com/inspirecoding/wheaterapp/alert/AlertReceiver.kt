package com.inspirecoding.wheaterapp.alert

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.inspirecoding.wheaterapp.widget.CurrentWeatherWidget
import com.inspirecoding.wheaterapp.widget.startService
import timber.log.Timber

class AlertReceiver : BroadcastReceiver()
{
    override fun onReceive(context: Context, intent: Intent?)
    {
        refreshTodayLabel(context)
        Timber.d("AlertReceiver - onReceive")
        startService(context)
    }

    fun refreshTodayLabel(context: Context)
    {
        // 1
        val man = AppWidgetManager.getInstance(context)
        // 2
        val ids = man.getAppWidgetIds(ComponentName(context, CurrentWeatherWidget::class.java))
        // 3
        val updateIntent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
        // 4
        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        // 5
        context.sendBroadcast(updateIntent)
    }
}