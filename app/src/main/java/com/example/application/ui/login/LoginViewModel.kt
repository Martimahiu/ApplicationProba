package com.example.application.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.data.LoginRepository
import com.example.application.data.model.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(private val loginRepository: LoginRepository = LoginRepository()) : ViewModel() {

    private val _loginResult = MutableStateFlow<Response<LoginResponse>?>(null)
    val loginResult: StateFlow<Response<LoginResponse>?> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val response = loginRepository.login(username, password)
            _loginResult.value = response
        }
    }
}