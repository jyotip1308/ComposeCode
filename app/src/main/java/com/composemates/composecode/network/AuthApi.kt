package com.composemates.composecode.network

import com.composemates.composecode.response.login_response
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded
    @POST("/operator/login")
    suspend fun login(
        @Field("employee_code") username: String,
        @Field("password") password: String,
    ): Response<login_response>
}