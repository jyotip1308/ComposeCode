package com.composemates.composecode.repository

import android.os.Build
import androidx.annotation.RequiresExtension
import com.composemates.composecode.network.AuthApi
import com.composemates.composecode.network.SafeApiCall
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi) : SafeApiCall
{

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun login(
        mobileNumber: String,
        password: String
    ) = safeApiCall {

        api.login(mobileNumber, password)
    }
}
