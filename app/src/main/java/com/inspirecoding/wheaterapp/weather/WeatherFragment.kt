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
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.util.Status
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
            if (_listOfCities.isEmpty())
            {
                navigateToAddCityFragment()
            }
            else
            {
                val citiesList = emptyList<Pair<CurrentWeather, ForecastWeather>>()

                for (selectedCity in _listOfCities)
                {
//                        getCurrentWeatherObserver(selectedCity)
                    observeWeather(selectedCity)
                }

                initViewPager(citiesList.toMutableList())
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
    }

    private fun observeWeather(currentWeather: CurrentWeather)
    {
        weatherViewModel.observeWeather(currentWeather).observe(viewLifecycleOwner, Observer { _result ->
            when (_result.status)
            {
                Status.SUCCESS -> {
                    _result.data?.let { _currentWeather ->
                        Timber.d("__observeWeather__ $_currentWeather")
                        if(_currentWeather.first != null && _currentWeather.second != null)
                        {
                            val pair = Pair(_currentWeather.first!!, _currentWeather.second!!)
                            pair.first.position = currentWeather.position
                            if(this::currentWeatherAdapter.isInitialized)
                            {
                                val addedCurrentWeather = currentWeatherAdapter.listOfCurrentWeather.find {
                                    it.first.name == pair.first.name
                                }

                                Timber.d("$addedCurrentWeather")

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
    private fun getCurrentWeatherObserver(currentWeather: CurrentWeather)
    {
//        weatherViewModel.getCurrentWeather(currentWeather).observe(viewLifecycleOwner, Observer { _result ->
//            when (_result.status)
//            {
//                Status.SUCCESS -> {
//                    _result.data?.let { _currentWeather ->
//                        _currentWeather.position = currentWeather.position
//                        if(this::currentWeatherAdapter.isInitialized)
//                        {
//                            val addedCurrentWeather = currentWeatherAdapter.listOfCurrentWeather.find {
//                                it.name == _currentWeather.name
//                            }
//                            if(addedCurrentWeather != null)
//                            {
//                                currentWeatherAdapter.updateNewCurrentWeather(_currentWeather)
//                            }
//                            else
//                            {
//                                currentWeatherAdapter.addNewCurrentWeather(_currentWeather)
//                            }
//                        }
//                    }
//                }
//                Status.LOADING -> {
//                    Timber.d("LOADING")
//                }
//                Status.ERROR -> {
//                    Timber.d("${_result.message}")
//                }
//            }
//        })
    }
    private fun getForecastWeatherObserver(currentWeather: CurrentWeather)
    {
        weatherViewModel.getForecastWeather(currentWeather.name).observe(viewLifecycleOwner, Observer { _result ->
            when (_result.status)
            {
                Status.SUCCESS -> {
                    _result.data?.let { _forecastWeather ->
                            Timber.d("List: ${currentWeather.name}, CurrentWeather: ${_forecastWeather.cityName}")

//                        val addedCurrentWeather = currentWeatherAdapter.listOfCurrentWeather.find {
//                            Timber.d("List: ${it.first.name}, CurrentWeather: ${currentWeather.name}")
//                            it.first.name == currentWeather.name
//                        }
//
//
//                        val pair = Pair(currentWeather, _forecastWeather)
//
//                        if(addedCurrentWeather != null)
//                        {
//                            currentWeatherAdapter.updateNewCurrentWeather(pair)
//                        }
//                        else
//                        {
//                            currentWeatherAdapter.addNewCurrentWeather(pair)
//                        }
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



    private fun initViewPager(listOfCities: MutableList<Pair<CurrentWeather, ForecastWeather>>)
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