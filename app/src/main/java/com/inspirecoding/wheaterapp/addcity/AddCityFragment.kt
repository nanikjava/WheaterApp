package com.inspirecoding.wheaterapp.addcity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.databinding.AddCityFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCityFragment : Fragment()
{
    private lateinit var binding: AddCityFragmentBinding
    private val viewModel by viewModels<AddCityViewModel>()

    override fun onCreateView(layoutInflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = AddCityFragmentBinding.inflate (
            layoutInflater, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        setupToastObserver()
        setupProgressBarObserver()

        binding.tietSearch.addTextChangedListener { _searchText ->
            _searchText?.let { __searchText ->
                viewModel.getWeather(__searchText.toString())
            }
        }

        binding.tvCity.setOnClickListener {
            insertCity()
        }
    }

    private fun setupToastObserver()
    {
        viewModel.toast.observe(viewLifecycleOwner) { _message ->
            Toast.makeText(context, _message, Toast.LENGTH_SHORT).show()
        }
    }
    private fun setupProgressBarObserver()
    {
        viewModel.isLoading.observe(viewLifecycleOwner) { _isVisible ->
            setProgressBarVisibility(_isVisible)
        }
    }

    private fun setProgressBarVisibility(isVisible: Boolean)
    {
        if (isVisible)
        {
            binding.progressBar.visibility = View.VISIBLE
        }
        else
        {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    private fun insertCity()
    {
        viewModel.getCurrentWeatherTableSize().observe(viewLifecycleOwner) { currentWeatherTableSize ->
            if(viewModel.foundCurrentWeatherOfCity != null && viewModel.foundForecastWeatherOfCity != null)
            {
                viewModel.foundCurrentWeatherOfCity!!.position = currentWeatherTableSize
                viewModel.foundForecastWeatherOfCity!!.cityName = viewModel.foundCurrentWeatherOfCity!!.name

                viewModel.insertWeather().observe(viewLifecycleOwner) { _result ->
                    if(_result.first != null && _result.second != null)
                    {
                        navigateToWeatherFragment()
                    }
                }
            }
        }
    }

    private fun navigateToWeatherFragment()
    {
        findNavController().navigate(R.id.action_addCityFragment_to_weatherFragment)
    }
}