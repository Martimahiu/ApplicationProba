package com.example.application.data.model

import com.squareup.moshi.Json

data class LoginRequest(
    val username: String,
    val password: String
)