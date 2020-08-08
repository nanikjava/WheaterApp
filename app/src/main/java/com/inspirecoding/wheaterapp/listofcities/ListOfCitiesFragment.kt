package com.inspirecoding.wheaterapp.listofcities

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inspirecoding.wheaterapp.R

class ListOfCitiesFragment : Fragment()
{
    private lateinit var viewModel: ListOfCitiesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.list_of_cities_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListOfCitiesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}