package com.inspirecoding.wheaterapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.inspirecoding.wheaterapp.databinding.ActivityMainBinding
import com.inspirecoding.wheaterapp.util.NetworkUtils
import com.inspirecoding.wheaterapp.util.doAnimation
import com.inspirecoding.wheaterapp.util.getColorRes
import com.inspirecoding.wheaterapp.util.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_network_status.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        navController = Navigation.findNavController(this, R.id.fragmentContainer)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.weatherFragment
            )
        )

        NavigationUI.setupWithNavController(
            binding.toolbar,
            navController,
            appBarConfiguration
        )

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.weatherFragment)
            {
                binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
            }
            if(destination.id == R.id.listOfCitiesFragment
                || destination.id == R.id.addCityFragment
                || destination.id == R.id.settingsFragment)
            {
                binding.toolbar.setNavigationIcon(R.drawable.ic_backarrow_blue_24)
                binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
            }

            if (destination.id != R.id.splashFragment)
            {
                handleNetworkChanges()
            }
        }
    }

    private fun handleNetworkChanges()
    {
        NetworkUtils.getNetworkLiveData(binding.root.context).observe(this, Observer { isConnected ->
            if (!isConnected)
            {
                binding.networkStatus.apply {
                    tv_networkStatus.text = getString(R.string.text_no_connectivity)

                    networkStatus.apply {
                        show()
                        context?.let {_context ->
                            setBackgroundColor(_context.getColorRes(R.color.colorStatusNotConnected))
                        }
                    }
                }
            }
            else
            {
                binding.networkStatus.apply {
                    tv_networkStatus.text = getString(R.string.text_connectivity)

                    context?.let {_context ->
                        networkStatus.apply {
                            setBackgroundColor(_context.getColorRes(R.color.colorStatusConnected))
                        }
                    }
                    networkStatus.doAnimation()
                }
            }
        })
    }
}