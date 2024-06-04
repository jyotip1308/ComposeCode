package com.composemates.composecode.repository

import android.os.Build
import androidx.annotation.RequiresExtension
import com.composemates.composecode.network.AuthApi
import com.composemates.composecode.network.SafeApiCall
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi) : SafeApiCall
{
    suspend fun login(
    employee_id: String,
        password: String
    ) = safeApiCall {
        api.login(employee_id, password)
    }
}
