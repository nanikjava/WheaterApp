package com.inspirecoding.wheaterapp.model

import com.inspirecoding.wheaterapp.util.Status
import com.inspirecoding.wheaterapp.util.Status.ERROR
import com.inspirecoding.wheaterapp.util.Status.LOADING
import com.inspirecoding.wheaterapp.util.Status.SUCCESS

data class Resource<out T>(val status: Status, val data: T?, val message: String?)
{
    companion object
    {
        fun <T> success(data: T): Resource<T> = Resource(status = SUCCESS, data = data, message = null)

        fun <T> error(message: String, data: T? = null): Resource<T> = Resource(status = ERROR, data = data, message = message)

        fun <T> loading(data: T? = null): Resource<T> = Resource(status = LOADING, data = data, message = null)
    }
}