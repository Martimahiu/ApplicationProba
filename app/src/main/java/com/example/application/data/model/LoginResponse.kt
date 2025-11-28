package com.example.application.data.model

import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "refresh") val refresh: String,
    @Json(name = "access") val access: String
)