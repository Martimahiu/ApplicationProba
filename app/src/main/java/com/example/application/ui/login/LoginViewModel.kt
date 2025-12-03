// Asegúrate de que ESTE sea el contenido de tu LoginViewModel.kt

package com.example.application.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.data.model.LoginRequest
import com.example.application.data.model.LoginResponse
import com.example.application.data.model.StatusResponse
import com.example.application.data.network.ApiClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableStateFlow<Response<LoginResponse>?>(null)
    val loginResult: StateFlow<Response<LoginResponse>?> = _loginResult

    private val _statusResult = MutableStateFlow("Aquí aparecerá la respuesta del servidor...")
    val statusResult: StateFlow<String> = _statusResult

    // En LoginViewModel.kt

// ...

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                // Preparamos los dos argumentos que necesita la API
                val serverTokenValue = "server_token_lasm_2025"
                val loginRequestBody = LoginRequest(username, password)
                val loginString = Gson().toJson(loginRequestBody)
                Log.d("Login credentials parameters", loginString )

                // Llamamos a la función de la API pasándole los dos argumentos
                val response = ApiClient.apiService.getToken(
                    serverToken = serverTokenValue,
                    loginRequest = loginString
                )

                _loginResult.value = response
            } catch (e: Exception) {
                // Manejo de errores
            }
        }
    }

// ...


    fun getStatus() {
        viewModelScope.launch {
            _statusResult.value = "Cargando status..."
            try {
                val response = ApiClient.apiService.getStatus()
                if (response.isSuccessful) {
                    val statusData = response.body()
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    _statusResult.value = gson.toJson(statusData)
                } else {
                    _statusResult.value = "Error: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                _statusResult.value = "Excepción de red: ${e.message}"
            }
        }
    }
}
