// Pega este código en MainActivity.kt

package com.example.application

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
    var username by remember { mutableStateOf("test@test.urv.cat") }
    var password by remember { mutableStateOf("Jsp326Tj&") }

    val loginResult by viewModel.loginResult.collectAsState()
    val statusResultText by viewModel.statusResult.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(loginResult) {
        loginResult?.let {
            val message = if (it.isSuccessful) {
                "Login successful: Token de acceso recibido!"
            } else {
                "Login failed: ${it.errorBody()?.string()}"
            }
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { viewModel.login(username, password) }) {
            Text("Login")
        }

        Button(
            onClick = { viewModel.getStatus() },
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Text("Consultar Status del Servidor")
        }

        Text(
            text = statusResultText,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .weight(1f)
                .background(color = Color(0xFFF0F0F0), shape = RoundedCornerShape(4.dp))
                .padding(8.dp)
        )
    }
}
