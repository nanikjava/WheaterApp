package com.inspirecoding.wheaterapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.fragmentContainer)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.weatherFragment
            )
        )

        NavigationUI.setupWithNavController(
            toolbar,
            navController,
            appBarConfiguration
        )

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.weatherFragment)
            {
                toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
            }
            if(destination.id == R.id.listOfCitiesFragment
                || destination.id == R.id.addCityFragment
                || destination.id == R.id.settingsFragment)
            {
                toolbar.setNavigationIcon(R.drawable.ic_backarrow_blue_24)
                toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
            }
        }
    }
}