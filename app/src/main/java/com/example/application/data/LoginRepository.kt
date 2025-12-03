// En el archivo LoginRepository.kt

package com.example.application.data

import com.example.application.data.model.LoginRequest
import com.example.application.data.model.LoginResponse
import com.example.application.data.model.StatusResponse
import com.example.application.data.network.ApiClient
import retrofit2.Response

/**
 * El Repositorio es el único que habla con las fuentes de datos (API, base de datos, etc).
 * El ViewModel hablará con este Repositorio.
 */
class LoginRepository {

    /**
     * Llama a la función de login del ApiService.
     * Es crucial que esta función exista.
     */
    suspend fun login(
        serverToken: String,
        loginRequest: String
    ): Response<LoginResponse> {
        // Llama a la función de la API pasándole los dos argumentos que recibe
        return ApiClient.apiService.getToken(serverToken, loginRequest)
    }

    /**
     * Llama a la función de status del ApiService.
     */
    suspend fun getStatus(): Response<StatusResponse> {
        return ApiClient.apiService.getStatus()
    }
}
