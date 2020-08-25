package com.inspirecoding.wheaterapp.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.inspirecoding.wheaterapp.databinding.FragmentSettingsBinding
import com.inspirecoding.wheaterapp.util.Common
import com.inspirecoding.wheaterapp.util.SettingsValues
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment()
{
    private lateinit var binding: FragmentSettingsBinding

    private val settingsViewModel by viewModels<SettingsViewModel>()

    override fun onCreateView(layoutInflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = FragmentSettingsBinding.inflate(
            layoutInflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        binding.switchDarkMode.setOnClickListener { _switch ->
            settingsViewModel.setDarkMode(
                (_switch as SwitchCompat).isChecked
            )
        }

        binding.switchUnit.setOnClickListener { _switch ->
            if ((_switch as SwitchCompat).isChecked)
            {
                settingsViewModel.setUnit(
                    Common.METRIC
                )
            }
            else
            {
                settingsViewModel.setUnit(
                    Common.IMPERIAL
                )
            }
        }

        /** Set the statuses of switches **/
        setupUnitObserver()
        setupDarkModeObserver()
    }

    private fun setupUnitObserver()
    {
        SettingsValues.unit.observe(viewLifecycleOwner) { _unit ->
            if (_unit == Common.IMPERIAL)
            {
                binding.switchUnit.isChecked = false
            }
            else if (_unit == Common.METRIC)
            {
                binding.switchUnit.isChecked = true
            }
        }
    }

    private fun setupDarkModeObserver()
    {
        SettingsValues.darkMode.observe(viewLifecycleOwner) { _darkMode ->
            binding.switchDarkMode.isChecked = _darkMode
        }
    }
}