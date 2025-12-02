// Pega ESTE CÓDIGO COMPLETO en el nuevo archivo: app/src/main/java/com/example/application/data/model/StatusResponse.kt

package com.example.application.data.model

import com.squareup.moshi.Json

/**
 * Data class que representa la respuesta completa del endpoint /status.
 * Corresponde al JSON que vimos en Postman.
 */
data class StatusResponse(
    @Json(name = "status")
    val status: List<StatusItem>,

    @Json(name = "request_version")
    val requestVersion: String,

    @Json(name = "timestamp")
    val timestamp: String
)

/**
 * Data class que representa cada uno de los objetos dentro de la lista "status".
 */
data class StatusItem(
    @Json(name = "id")
    val id: Int,

    @Json(name = "version")
    val version: String,

    @Json(name = "build")
    val build: Int,

    @Json(name = "last_update")
    val lastUpdate: String
)
