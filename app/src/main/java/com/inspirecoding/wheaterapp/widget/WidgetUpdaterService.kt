package com.inspirecoding.wheaterapp.widget

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import com.inspirecoding.wheaterapp.repository.WeatherRepositoryImpl
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class WidgetUpdaterService : Service()
{
    @Inject
    lateinit var weatherRepositoryImpl: WeatherRepositoryImpl

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        collectData(this, weatherRepositoryImpl)

        Timber.d("WidgetUpdaterService - onStartCommand")

        return START_STICKY
    }

    override fun onCreate()
    {
        super.onCreate()

        val NOTIFICATION_ID = (System.currentTimeMillis() % 10000).toInt()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            startForeground(NOTIFICATION_ID, Notification.Builder(this).build())
        }
    }

    override fun onBind(intent: Intent): IBinder?
    {
        return null
    }
}
