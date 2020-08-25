package com.inspirecoding.wheaterapp.util

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import java.text.SimpleDateFormat
import java.util.*

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>)
{
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}
fun <T, K, R> LiveData<T>.combineWith(liveData: LiveData<K>, block: (T?, K?) -> R): LiveData<R>
{
    val result = MediatorLiveData<R>()

    result.addSource(this) {
        result.value = block(this.value, liveData.value)
    }

    result.addSource(liveData) {
        result.value = block(this.value, liveData.value)
    }

    return result
}

fun Long.convertToDateString() : String
{
    return Date(this).toLocaleString()
}

fun Calendar.today() : String
{
    return this.time.toLocaleString()
}

/**
 * Reduces drag sensitivity of [ViewPager2] widget
 */
fun ViewPager2.reduceDragSensitivity()
{
    val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
    recyclerViewField.isAccessible = true
    val recyclerView = recyclerViewField.get(this) as RecyclerView

    val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
    touchSlopField.isAccessible = true
    val touchSlop = touchSlopField.get(recyclerView) as Int
    touchSlopField.set(recyclerView, touchSlop*8)       // "8" was obtained experimentally
}




fun View.show()
{
    visibility = View.VISIBLE
}

fun View.hide()
{
    visibility = View.GONE
}