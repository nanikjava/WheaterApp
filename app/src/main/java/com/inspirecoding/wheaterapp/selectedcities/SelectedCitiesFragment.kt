package com.inspirecoding.wheaterapp.selectedcities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.databinding.SelectedCitiesFragmentBinding
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.selectedcities.adapter.SelectedCitiesAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SelectedCitiesFragment : Fragment()
{
    private lateinit var binding: SelectedCitiesFragmentBinding
    private val viewModel by viewModels<SelectedCitiesViewModel>()

    override fun onCreateView(layoutInflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = SelectedCitiesFragmentBinding.inflate(
            layoutInflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        setupListOfSelectedCitiesObserver()
        setupFabClick()
    }
    private fun setupFabClick()
    {
        binding.fab.setOnClickListener {
            navigateToAddCityFragment()
        }
    }

    private fun setupListOfSelectedCitiesObserver()
    {
        viewModel.allSelectedCities.observe(viewLifecycleOwner) { _listOfCities ->
            if (_listOfCities.size == 0)
            {
                navigateToAddCityFragment()
            }
            else
            {
                initRecyclerView(_listOfCities)
            }
        }
    }

    private fun navigateToAddCityFragment()
    {
        findNavController().navigate(R.id.action_listOfCitiesFragment_to_addCityFragment)
    }

    private fun initRecyclerView(listOfSelectedCities : List<CurrentWeather>)
    {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SelectedCitiesAdapter(listOfSelectedCities)
        }
    }
}