package com.example.application

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.application.ui.login.LoginViewModel
import com.example.application.ui.theme.ApplicationTheme

class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = loginViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier, viewModel: LoginViewModel) {
    var username by remember { mutableStateOf("test@test.usv.cat") }
    var password by remember { mutableStateOf("Jsp326TjB") }
    val loginResult by viewModel.loginResult.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(loginResult) {
        loginResult?.let {
            if (it.isSuccessful) {
                Toast.makeText(context, "Login successful: ${it.body()?.access}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Login failed: ${it.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )
        Button(onClick = { viewModel.login(username, password) }) {
            Text("Login")
        }
    }
}
