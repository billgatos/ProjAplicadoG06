package com.example.lojasocialbd

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.lojasocialbd.ui.theme.LojaSocialBDTheme

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            LojaSocialBDTheme {
//                LoginScreen(onLoginSuccess = { userType ->
//                    // Ação após login bem-sucedido
//                })
//            }
//        }
//    }
//}

@Composable
fun LoginScreen(onLoginSuccess: (String) -> Unit, viewModel: LoginViewModel = viewModel()) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cabeçalho com o logo
        LogoHeader()
        Text(text = "Login", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val userType = viewModel.login(username, password)
                if (userType != null) {
                    onLoginSuccess(userType) // Sucesso no login
                } else {
                    loginError = true // Erro no login
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login")
        }

        if (loginError) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Login falhou. Tente novamente.", color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
fun LogoHeader() {
    // Exibição da imagem/logo no topo
    Image(
        painter = painterResource(id = R.drawable.portrait_transparent_image),
        // Insira aqui o nome correto da imagem para fundo
        contentDescription = "Loja Social Logo",
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp) // Altura da imagem ajustável
    )
}