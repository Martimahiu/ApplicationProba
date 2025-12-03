// En ApiService.kt

package com.example.application.data.network

import com.example.application.data.model.LoginRequest // <--- IMPORTANTE AÑADIR ESTE IMPORT
import com.example.application.data.model.LoginResponse
import com.example.application.data.model.StatusResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header // <--- IMPORTANTE AÑADIR ESTE IMPORT
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    /**
     * Endpoint para hacer login.
     * AHORA SABEMOS que requiere AMBAS COSAS: una cabecera Y un cuerpo.
     */
    @POST("endpoints/v2/token/")
    suspend fun getToken(
        @Header("server-token") serverToken: String, // Parámetro 1: La cabecera
        @Body loginRequest: String             // Parámetro 2: El cuerpo
    ): Response<LoginResponse>

    /**
     * Endpoint para obtener el estado del servidor.
     */
    @GET("endpoints/v2/status")
    suspend fun getStatus(): Response<StatusResponse>
}
