package com.inspirecoding.wheaterapp.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.inspirecoding.wheaterapp.MainActivity
import kotlinx.coroutines.flow.collect
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.repository.local.CurrentWeatherDao
import com.inspirecoding.wheaterapp.util.Common
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Implementation of App Widget functionality.
 */
@AndroidEntryPoint
class CurrentWeatherWidget : AppWidgetProvider()
{
    private val job = SupervisorJob()
    val coroutineScope = CoroutineScope(Dispatchers.IO + job)
    @Inject lateinit var currentWeatherDao: CurrentWeatherDao

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray)
    {
        // Start the service
        val intent = Intent(context.applicationContext, WidgetUpdaterService::class.java)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)
        context.startService(intent)
    }

    override fun onReceive(context: Context, intent: Intent)
    {
        super.onReceive(context, intent)

        coroutineScope.launch {
            currentWeatherDao.getFirstCityWeatherSuspend()?.collect { firstCitiesWeather ->
                val appWidgetManager = AppWidgetManager.getInstance(context)
                val man = AppWidgetManager.getInstance(context)
                val ids = man.getAppWidgetIds(ComponentName(context, CurrentWeatherWidget::class.java))

                if(firstCitiesWeather != null) {
                    val city = firstCitiesWeather.name
                    val temp = firstCitiesWeather.main.temp
                    val weatherDesc = Common.getWeatherDescription(
                        firstCitiesWeather.weather?.get(0)?.description, context
                    )

                    Timber.d("onReceive - $city - $temp - $weatherDesc")

                    for (appWidgetId in ids)
                    {
                        updateAppWidget(
                            context,
                            appWidgetManager,
                            appWidgetId,
                            city, temp, weatherDesc.first, weatherDesc.second)
                    }
                } else {
                    for (appWidgetId in ids)
                    {
                        updateAppWidget(
                            context,
                            appWidgetManager,
                            appWidgetId,
                            context.getString(R.string.no_selected_city), null, null, null)
                    }
                }
            }
        }
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    city: String?, temp: Double?, weatherDesc: String?, weatherImage: Int?)
{
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.current_weather_widget)
    city?.let { _city ->
        views.setTextViewText(R.id.tv_widget_city, _city)
    }

    if (temp != null) {
        val tempString = String.format(context.resources.getString(R.string.temperature_with_degree), temp)
        views.setTextViewText(R.id.tv_widget_temp, tempString)
    } else {
        views.setTextViewText(R.id.tv_widget_temp, "")
    }

    if (weatherDesc != null) {
        views.setTextViewText(R.id.tv_widget_weatherMain, weatherDesc)
    } else {
        views.setTextViewText(R.id.tv_widget_weatherMain, "")
    }

    if (weatherImage != null) {
        views.setImageViewResource(R.id.iv_widget_weather_icon, weatherImage)
    }

    views.setOnClickPendingIntent(R.id.widget_layout,
        getPendingIntentActivity(context))

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
private fun getPendingIntentActivity(context: Context): PendingIntent
{
    // Construct an Intent which is pointing this class.
    val intent = Intent(context, MainActivity::class.java)
    // And this time we are sending a broadcast with getBroadcast
    return PendingIntent.getActivity(context, 0, intent, 0)
}