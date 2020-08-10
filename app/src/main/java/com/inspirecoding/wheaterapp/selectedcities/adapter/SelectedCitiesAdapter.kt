package com.inspirecoding.wheaterapp.selectedcities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.databinding.ItemCitiesListBinding
import com.inspirecoding.wheaterapp.model.CurrentWeather

class SelectedCitiesAdapter (
    private val listOfSelectedCities: List<CurrentWeather>
) : RecyclerView.Adapter<SelectedCitiesAdapter.SelectedCitiesViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedCitiesViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemCitiesListBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_cities_list,
            parent, false
        )

        return SelectedCitiesViewHolder(binding)
    }

    override fun getItemCount() = listOfSelectedCities.size

    override fun onBindViewHolder(selectedCitiesViewHolder: SelectedCitiesViewHolder, position: Int)
    {
        selectedCitiesViewHolder.bind(listOfSelectedCities[position])
    }

    inner class SelectedCitiesViewHolder constructor(val binding: ItemCitiesListBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind (currentWeather: CurrentWeather)
        {
            binding.city = currentWeather.name
        }
    }
}