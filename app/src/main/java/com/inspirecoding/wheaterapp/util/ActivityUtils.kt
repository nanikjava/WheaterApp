package com.inspirecoding.wheaterapp.util

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Returns Color from resource.
 * @param id Color Resource ID
 */
fun Context.getColorRes(@ColorRes id: Int) = ContextCompat.getColor(applicationContext, id)