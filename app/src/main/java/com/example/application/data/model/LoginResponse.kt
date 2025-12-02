// Pega exactamente este código en LoginResponse.kt

package com.example.application.data.model

import com.squareup.moshi.Json

// ESTA ES LA ESTRUCTURA CORRECTA QUE COINCIDE CON TU API
// La API devuelve un JSON con "access" y "refresh".
// Esta clase le dice a la app que espere esos campos.
data class LoginResponse(

    @Json(name = "access")
    val access: String,

    @Json(name = "refresh")
    val refresh: String

)
