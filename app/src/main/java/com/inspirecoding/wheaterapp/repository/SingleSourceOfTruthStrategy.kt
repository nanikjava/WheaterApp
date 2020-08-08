package com.inspirecoding.wheaterapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.inspirecoding.wheaterapp.util.Status.ERROR
import com.inspirecoding.wheaterapp.util.Status.SUCCESS
import com.inspirecoding.wheaterapp.model.Resource
import kotlinx.coroutines.Dispatchers

/**
 * The database serves as the single source of truth.
 * Therefore UI can receive data updates from database only.
 * Function notify UI about:
 * [Result.Status.SUCCESS] - with data from database
 * [Result.Status.ERROR] - if error has occurred from any source
 * [Result.Status.LOADING]
 */
fun <T, A> resultLiveData(databaseQuery: () -> LiveData<T>,
                          networkCall: suspend () -> Resource<A>,
                          saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading<T>())
            /** Get the data from Room DB **/
            val source = databaseQuery.invoke().map { Resource.success(it) }
            emitSource(source)

            /** Get the data from the network **/
            val responseStatus = networkCall.invoke()

            if (responseStatus.status == SUCCESS)
            {
                saveCallResult(responseStatus.data!!)
            }
            else if (responseStatus.status == ERROR)
            {
                emit(Resource.error<T>(responseStatus.message!!))
                emitSource(source)
            }
        }