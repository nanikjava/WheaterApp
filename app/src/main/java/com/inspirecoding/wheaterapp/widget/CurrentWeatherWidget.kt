package com.inspirecoding.wheaterapp.widget

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.inspirecoding.wheaterapp.MainActivity
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.alert.AlertReceiver
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.model.State
import com.inspirecoding.wheaterapp.repository.WeatherRepository
import com.inspirecoding.wheaterapp.repository.WeatherRepositoryImpl
import com.inspirecoding.wheaterapp.repository.local.CurrentWeatherDao
import com.inspirecoding.wheaterapp.util.Common
import com.inspirecoding.wheaterapp.util.DateConverters
import com.inspirecoding.wheaterapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

/**
 * Implementation of App Widget functionality.
 */
@AndroidEntryPoint
class CurrentWeatherWidget : AppWidgetProvider()
{
    @Inject lateinit var weatherDao: CurrentWeatherDao
    @Inject lateinit var weatherRepositoryImpl: WeatherRepositoryImpl

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onEnabled(context: Context)
    {
        super.onEnabled(context)

        startAlarm(context)
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    )
    {
        startService(context)
        collectData(context, weatherRepositoryImpl)
    }

    override fun onDisabled(context: Context)
    {
        super.onDisabled(context)

        stopAlarm(context)
    }
}

private lateinit var alarmManager : AlarmManager

internal fun collectData(context: Context, weatherRepository: WeatherRepository)
{
    val job = SupervisorJob()
    val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    coroutineScope.launch {
        weatherRepository.getFirstCityWeatherSuspend().collect { _currentWeather ->
            when (_currentWeather)
            {
                is State.Success -> {
                    Timber.d("weatherRepository_1 - ${_currentWeather}")

                    val appWidgetManager = AppWidgetManager.getInstance(context)
                    val man = AppWidgetManager.getInstance(context)
                    val ids = man.getAppWidgetIds(
                        ComponentName(
                            context,
                            CurrentWeatherWidget::class.java
                        )
                    )

                    if (_currentWeather != null) {
                        for (appWidgetId in ids) {
                            updateAppWidget(
                                context,
                                appWidgetManager,
                                appWidgetId,
                                _currentWeather.data
                            )
                        }
                    } else {
                        for (appWidgetId in ids) {
                            updateAppWidget(
                                context,
                                appWidgetManager,
                                appWidgetId,
                                _currentWeather
                            )
                        }
                    }
                }
                is State.Loading -> {
                    Timber.d("LOADING")
                }
                is State.Error -> {
                    Timber.d("${_currentWeather.message}")
                }
            }

        }
    }
}
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    currentWeather: CurrentWeather?)
{
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.current_weather_widget)

    currentWeather?.name.let { _city ->
        views.setTextViewText(R.id.tv_widget_city, _city)
    }

    if (currentWeather?.main?.temp != null) {
        val tempString = String.format(
            context.resources.getString(R.string.temperature_with_degree),
            currentWeather.main.temp
        )
        views.setTextViewText(R.id.tv_widget_temp, tempString)
    } else {
        views.setTextViewText(R.id.tv_widget_temp, "")
    }

    val weatherDesc = Common.getWeatherDescription(
        currentWeather?.weather?.get(0)?.description, context
    )
    val dateTime = currentWeather?.dt?.let {
        it * 1000
    }?.let { DateConverters.getFormattedTimeAgo(context, it) }

    if (weatherDesc != null) {
        views.setTextViewText(R.id.tv_widget_weatherMain, weatherDesc.first)
        views.setImageViewResource(R.id.iv_widget_weather_icon, weatherDesc.second)
    } else {
        views.setTextViewText(R.id.tv_widget_weatherMain, "")
    }

    views.setOnClickPendingIntent(
        R.id.widget_layout,
        getPendingIntentActivity(context)
    )

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
@RequiresApi(Build.VERSION_CODES.M)
private fun startAlarm(context: Context)
{
    alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlertReceiver::class.java)
    val timeInterval = 900 * 1_000L
    val pendingIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)

    /**
     * setInexactRepeating() alarm because it is more resource-efficient to use inexact timing,
     * which lets the system bundle alarms from different apps together.
     */
    alarmManager.setInexactRepeating(
        AlarmManager.RTC_WAKEUP,
        Calendar.getInstance().timeInMillis,
        timeInterval,
        pendingIntent
    )
}
private fun stopAlarm(context: Context)
{
    alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlertReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0)
    alarmManager.cancel(pendingIntent)
}
fun startService(context: Context)
{
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        context.startForegroundService(Intent(context, WidgetUpdaterService::class.java))
    } else {
        context.startService(Intent(context, WidgetUpdaterService::class.java))
    }
}