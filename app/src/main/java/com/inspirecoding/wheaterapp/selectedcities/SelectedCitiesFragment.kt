package com.inspirecoding.wheaterapp.selectedcities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.databinding.SelectedCitiesFragmentBinding
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.selectedcities.adapter.SelectedCitiesAdapter
import com.inspirecoding.wheaterapp.util.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SelectedCitiesFragment : Fragment()
{
    private lateinit var binding: SelectedCitiesFragmentBinding
    private lateinit var selectedCitiesAdapter: SelectedCitiesAdapter
    private val viewModel by viewModels<SelectedCitiesViewModel>()

    private val itemTouchHelper_reOrder by lazy {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean
            {
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                updateListPositions(from, to)

                return true
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int)
            {
                val from = viewHolder.adapterPosition

                deleteListPositions(from)

                updateAllCurrentWeather()
            }
            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int)
            {
                super.onSelectedChanged(viewHolder, actionState)

                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG)
                {
                    viewHolder?.itemView?.alpha = 0.5f
                }
            }
            override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder)
            {
                super.clearView(recyclerView, viewHolder)

                updateAllCurrentWeather()

                viewHolder.itemView.alpha = 1.0f
            }
        }

        ItemTouchHelper(simpleItemTouchCallback)
    }

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

        itemTouchHelper_reOrder.attachToRecyclerView(binding.recyclerView)
    }

    private fun updateListPositions(from: Int, to: Int)
    {
        viewModel.moveItem(from, to)
        selectedCitiesAdapter.notifyItemMoved(from, to)
    }
    private fun deleteListPositions(from: Int)
    {
        viewModel.deleteCurrentWeather(from)
        selectedCitiesAdapter.notifyItemRemoved(from)
    }

    private fun updateAllCurrentWeather()
    {
        for (currentWeather in viewModel.listOfSelectedCities)
        {
            viewModel.updateCurrentWeather(currentWeather)
        }
    }

    private fun setupFabClick()
    {
        binding.fab.setOnClickListener {
            navigateToAddCityFragment()
        }
    }

    private fun setupListOfSelectedCitiesObserver()
    {
        viewModel.allSelectedCities.observe(viewLifecycleOwner, Observer {  _listOfCities ->
            if (_listOfCities.size == 0)
            {
                navigateToAddCityFragment()
            }
            else
            {
                initRecyclerView(_listOfCities)
                viewModel.listOfSelectedCities = _listOfCities.toMutableList()
            }
        })
    }

    private fun navigateToAddCityFragment()
    {
        findNavController().navigate(R.id.action_listOfCitiesFragment_to_addCityFragment)
    }

    private fun initRecyclerView(listOfSelectedCities : List<CurrentWeather>)
    {
        selectedCitiesAdapter = SelectedCitiesAdapter(listOfSelectedCities)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = selectedCitiesAdapter
        }
    }
}