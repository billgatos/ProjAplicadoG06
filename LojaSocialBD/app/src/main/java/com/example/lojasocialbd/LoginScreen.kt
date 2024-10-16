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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

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

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            //.align(Alignment.Center),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//
//        ) {
//            // Cabeçalho com o logo
//            LogoHeader()
//            Text(text = "Login", style = MaterialTheme.typography.headlineLarge)
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            TextField(
//                value = username,
//                onValueChange = { username = it },
//                label = { Text("Username") },
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            TextField(
//                value = password,
//                onValueChange = { password = it },
//                label = { Text("Password") },
//                visualTransformation = PasswordVisualTransformation(),
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(
//                onClick = {
//                    val userType = viewModel.login(username, password)
//                    if (userType != null) {
//                        onLoginSuccess(userType) // Sucesso no login
//                    } else {
//                        loginError = true // Erro no login
//                    }
//                },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(text = "Login")
//            }
//
//            if (loginError) {
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(
//                    text = "Login falhou. Tente novamente.",
//                    color = MaterialTheme.colorScheme.error
//                )
//            }
//        }
//    }

    //versao 2
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Imagem de fundo
        Image(
            painter = painterResource(id = R.drawable.portrait_transparent_image),
            // Insira o nome correto da sua imagem aqui
            contentDescription = "Loja Social Logo",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )

        // Conteúdo sobreposto
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.Center), // Alinha a coluna ao centro
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Texto sobreposto à imagem
            Text(
                text = "Bem-vindo à High Tech Social App",
                fontSize = 32.sp,
                //color = MaterialTheme.colorScheme.onPrimary,
                color = Color(0xFF00008B), //aZUL eSCURO
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            //Text(text = "Login", style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Utilizador") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
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



}
@Composable
fun LogoHeader() {
    // Exibição da imagem/logo no topo
    Image(
        painter = painterResource(id = R.drawable.portrait_transparent_image),
        // Insira aqui o nome correto da imagem para fundo
        contentDescription = "Loja Social Logo",
        modifier = Modifier

            .height(150.dp) // Altura da imagem ajustável
    )
}