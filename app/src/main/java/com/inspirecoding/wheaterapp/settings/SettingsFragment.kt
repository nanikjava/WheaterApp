package com.inspirecoding.wheaterapp.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment()
{
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(layoutInflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = FragmentSettingsBinding.inflate(
            layoutInflater, container, false
        )

        return binding.root
    }
}