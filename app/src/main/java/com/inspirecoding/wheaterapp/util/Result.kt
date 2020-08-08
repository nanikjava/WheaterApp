package com.inspirecoding.dagger_practice_autsofttest_1.utils


sealed class Result<out T>
{
    data class Success<out R>(val data: R) : Result<R>() // Status success and data of the result
    data class Error(val exception: Exception) : Result<Nothing>() // Status Error an error message
    data class Loading(val isLoading: Boolean) : Result<Nothing>() // Status Error an error message

    // string method to display a result for debugging
    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Error[exception=$isLoading]"
        }
    }
}