package com.composemates.composecode.network

import android.os.Build
import androidx.annotation.RequiresExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface SafeApiCall {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T>
    {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(true, null, null)
                    }
                }
            }
        }
    }
}