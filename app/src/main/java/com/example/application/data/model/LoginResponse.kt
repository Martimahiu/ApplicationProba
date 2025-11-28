package com.example.application.data.model

import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "token") val token: String
)