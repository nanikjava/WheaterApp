package com.inspirecoding.wheaterapp.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.inspirecoding.wheaterapp.MainActivity
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.util.SettingsValues
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment()
{
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val splashScreenViewModel by viewModels<SplashScreenViewModel>()

    override fun onStart()
    {
        super.onStart()

        (activity as MainActivity).supportActionBar?.hide()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        getSharedPreferences()

        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }
            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
            }
            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int)
            {
                coroutineScope.launch {
                    delay(1_000)
                    findNavController().navigate(R.id.action_splashFragment_to_weatherFragment)
                }
            }
        })
    }

    private fun getSharedPreferences()
    {
        splashScreenViewModel.getSharedPreferences()
    }
}