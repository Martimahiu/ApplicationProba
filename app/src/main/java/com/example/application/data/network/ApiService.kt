package com.example.application.data.network

import com.example.application.data.model.LoginRequest
import com.example.application.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("endpoints/v2/token/")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}