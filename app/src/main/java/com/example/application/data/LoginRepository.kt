package com.example.application.data

import com.example.application.data.model.LoginRequest
import com.example.application.data.network.ApiClient

class LoginRepository {
    private val apiService = ApiClient.instance

    suspend fun login(username: String, password: String) = apiService.login(LoginRequest(username, password))
}