package com.inspirecoding.wheaterapp.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.google.android.material.tabs.TabLayoutMediator
import com.inspirecoding.wheaterapp.MainActivity
import com.inspirecoding.wheaterapp.weather.adapter.CurrentWeatherAdapter
import com.inspirecoding.wheaterapp.databinding.WeatherFragmentBinding
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class WeatherFragment : Fragment()
{
    private lateinit var binding: WeatherFragmentBinding

    private val weatherViewModel by viewModels<WeatherViewModel>()

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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        setupCurrentWeatherObserver()
    }

    private fun setupCurrentWeatherObserver()
    {
        weatherViewModel.currentWeatherLiveData.observe(viewLifecycleOwner) { _result ->
            when (_result.status)
            {
                Status.SUCCESS -> {
                    Timber.d("${_result.data}")
                    _result.data?.let { _listOfCurrentWeather ->
                        initViewPager(_listOfCurrentWeather)
                    }
                }
                Status.LOADING -> {
                    Timber.d("LOADING")
                }
                Status.ERROR -> {
                    Timber.d("${_result.message}")
                }
            }
        }
    }

    private fun initViewPager(listOfCurrentWeather: List<CurrentWeather>)
    {
        binding.viewPager2.adapter = CurrentWeatherAdapter(listOfCurrentWeather).apply {
            setHasStableIds(true)
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
//            tab.text = listOfCurrentWeather[position].name
        }.attach()
    }
}