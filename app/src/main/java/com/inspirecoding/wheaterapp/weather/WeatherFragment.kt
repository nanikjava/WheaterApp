package com.inspirecoding.wheaterapp.weather

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.inspirecoding.wheaterapp.MainActivity
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.weather.adapter.CurrentWeatherAdapter
import com.inspirecoding.wheaterapp.databinding.WeatherFragmentBinding
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.util.Status
import com.inspirecoding.wheaterapp.util.observeOnce
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        setupListOfSelectedCitiesObserver()
    }

    private fun setupListOfSelectedCitiesObserver()
    {
        weatherViewModel.listOfCurrentWeather().observe(viewLifecycleOwner, Observer { _listOfCities ->
            if (_listOfCities.size == 0)
            {
                navigateToAddCityFragment()
            }
            else
            {
                if(!::currentWeatherAdapter.isInitialized)
                {
                    for (selectedCity in _listOfCities)
                    {
                        getCurrentWeatherObserver(selectedCity)
                    }
                }

                initViewPager(_listOfCities.toMutableList())
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
    }

    private fun getCurrentWeatherObserver(currentWeather: CurrentWeather)
    {
        weatherViewModel.getCurrentWeather(currentWeather).observe(viewLifecycleOwner, Observer { _result ->
            when (_result.status)
            {
                Status.SUCCESS -> {
                    _result.data?.let { _currentWeather ->
                        _currentWeather.position = currentWeather.position
                        if(this::currentWeatherAdapter.isInitialized)
                        {
                            val addedCurrentWeather = currentWeatherAdapter.listOfCurrentWeather.find {
                                it.name == _currentWeather.name
                            }
                            Timber.d("$addedCurrentWeather")
                            if(addedCurrentWeather != null)
                            {
                                currentWeatherAdapter.updateNewCurrentWeather(_currentWeather)
                            }
                            else
                            {
                                currentWeatherAdapter.addNewCurrentWeather(_currentWeather)
                            }
                        }
                    }
                }
                Status.LOADING -> {
                    Timber.d("LOADING")
                }
                Status.ERROR -> {
                    Timber.d("${_result.message}")
                }
            }
        })
    }

    private fun initViewPager(listOfCities: MutableList<CurrentWeather>)
    {
        currentWeatherAdapter = CurrentWeatherAdapter(listOfCities)
        binding.viewPager2.apply {
            adapter = currentWeatherAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position -> }.attach()
    }
    private fun navigateToAddCityFragment()
    {
        findNavController().navigate(R.id.action_weatherFragment_to_addCityFragment)
    }
    private fun navigateToListOfCitiesFragment()
    {
        findNavController().navigate(R.id.action_weatherFragment_to_listOfCitiesFragment)
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
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}