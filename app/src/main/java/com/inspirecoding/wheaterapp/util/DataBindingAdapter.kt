package com.inspirecoding.wheaterapp.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("app:hasCityFound")
fun hasCityFound(textView: TextView, hasCityFound: Boolean)
{
    if(hasCityFound)
    {
        textView.visibility = View.VISIBLE
    }
    else
    {
        textView.visibility = View.INVISIBLE
    }
}
@BindingAdapter("app:hasCityFound")
fun hasCityFound(imageView: ImageView, hasCityFound: Boolean)
{
    if(hasCityFound)
    {
        imageView.visibility = View.VISIBLE
    }
    else
    {
        imageView.visibility = View.INVISIBLE
    }
}