package com.inspirecoding.wheaterapp.weather

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.inspirecoding.wheaterapp.MainActivity
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.databinding.WeatherFragmentBinding
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.util.Status
import com.inspirecoding.wheaterapp.util.reduceDragSensitivity
import com.inspirecoding.wheaterapp.weather.adapter.CurrentWeatherAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class WeatherFragment : Fragment()
{
    private lateinit var binding: WeatherFragmentBinding
    private val weatherViewModel by viewModels<WeatherViewModel>()
    private lateinit var currentWeatherAdapter: CurrentWeatherAdapter

    override fun onStart()
    {
        super.onStart()

        (activity as MainActivity).supportActionBar?.show()
    }
    override fun onCreateView(layoutInflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = WeatherFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        setupListOfSelectedCitiesObserver()

        binding.viewPager2.reduceDragSensitivity()

        binding.swipeRefreshLayout.setOnRefreshListener {
            setupListOfSelectedCitiesObserver()
        }
    }

    private fun setupListOfSelectedCitiesObserver()
    {
        weatherViewModel.listOfWeather().observe(viewLifecycleOwner, Observer { _listOfCities ->
            if (_listOfCities.isEmpty())
            {
                navigateToAddCityFragment()
            }
            else
            {

                for (selectedCity in _listOfCities)
                {
                    observeWeather(selectedCity.first)

                    // Implementation with Flow
                    weatherViewModel.getWeatherOfCity(selectedCity.first)
                }

                initViewPager(_listOfCities)
            }
        })
    }

    private fun observeWeather(currentWeather: CurrentWeather)
    {
        weatherViewModel.observeWeather(currentWeather).observe(viewLifecycleOwner, Observer { _result ->
            when (_result.status)
            {
                Status.SUCCESS -> {
                    _result.data?.let { _currentWeather ->
                        if(_currentWeather.first != null && _currentWeather.second != null)
                        {
                            val pair = Pair(_currentWeather.first!!, _currentWeather.second!!)
                            pair.first.position = currentWeather.position
                            if(this::currentWeatherAdapter.isInitialized)
                            {
                                val addedCurrentWeather = currentWeatherAdapter.listOfCurrentWeather.find {
                                    it.first.name == pair.first.name
                                }

                                if(addedCurrentWeather != null)
                                {
                                    currentWeatherAdapter.updateNewCurrentWeather(pair)
                                }
                                else
                                {
                                    currentWeatherAdapter.addNewCurrentWeather(pair)
                                }
                            }
                        }
                        setupSpinner(false)
                    }
                }
                Status.LOADING -> {
                    Timber.d("LOADING")
                    setupSpinner(true)
                }
                Status.ERROR -> {
                    Timber.d("${_result.message}")
                    setupSpinner(false)
                }
            }
        })
    }

    private fun initViewPager(listOfCities: MutableList<Pair<CurrentWeather, ForecastWeather>>)
    {
        currentWeatherAdapter = CurrentWeatherAdapter(listOfCities)
        binding.viewPager2.apply {
            adapter = currentWeatherAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = listOfCities[position].first.name
        }.attach()
    }
    private fun navigateToAddCityFragment()
    {
        findNavController().navigate(R.id.action_weatherFragment_to_addCityFragment)
    }
    private fun navigateToListOfCitiesFragment()
    {
        findNavController().navigate(R.id.action_weatherFragment_to_listOfCitiesFragment)
    }
    private fun navigateToSettingsFragment()
    {
        findNavController().navigate(R.id.action_weatherFragment_to_settingsFragment)
    }
    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater)
    {
        menuInflater.inflate(R.menu.menu_cities_settings, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return when(item.itemId)
        {
            R.id.item_cities -> {
                navigateToListOfCitiesFragment()
                return true
            }
            R.id.item_settings -> {
                navigateToSettingsFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun setupSpinner(show: Boolean)
    {
        binding.swipeRefreshLayout.isRefreshing = show
    }
}