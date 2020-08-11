package com.inspirecoding.wheaterapp.addcity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.util.Status.SUCCESS
import com.inspirecoding.wheaterapp.util.Status.ERROR
import com.inspirecoding.wheaterapp.util.Status.LOADING
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

        binding.tietSearch.addTextChangedListener { _searchText ->
            _searchText?.let { __searchText ->
                setupGetCurrentWeatherObserver(__searchText.toString())
            }
        }

        binding.tvCity.setOnClickListener {
            insertCity()
        }
    }

    private fun setupGetCurrentWeatherObserver(searchText: String)
    {
        viewModel.getCurrentWeather(searchText).observe(viewLifecycleOwner) { _resource ->
            when (_resource.status)
            {
                SUCCESS -> {
                    _resource.data?.let { _foundCity ->
//                        val city = "${_foundCity.name}, ${_foundCity.sys.country}"
//                        binding.tvCity.text = city
                    }
                    setProgressBarVisibility(isVisible = false)
                }
                ERROR -> {
                    _resource.message?.let { _message ->
                        setProgressBarVisibility(isVisible = false)
                    }
                }
                LOADING -> {
                    setProgressBarVisibility(isVisible = true)
                }
            }
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
        viewModel.getCurrentWeatherTableSize().observe(viewLifecycleOwner) { _tableSize ->
            viewModel.foundCurrentWeatherOfCity?.let {_foundCurrentWeatherOfCity ->
                _foundCurrentWeatherOfCity.position = _tableSize
                viewModel.insertCity(_foundCurrentWeatherOfCity).observe(viewLifecycleOwner, Observer { _result ->
                    navigateToWeatherFragment()
                })
            }
        }
    }

    private fun navigateToWeatherFragment()
    {
        findNavController().navigate(R.id.action_addCityFragment_to_weatherFragment)
    }
}