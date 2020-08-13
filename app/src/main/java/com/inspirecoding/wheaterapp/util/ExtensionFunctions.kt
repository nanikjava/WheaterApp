package com.inspirecoding.wheaterapp.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
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