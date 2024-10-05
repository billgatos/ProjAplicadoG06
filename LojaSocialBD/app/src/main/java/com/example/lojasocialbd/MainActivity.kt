package com.example.lojasocialbd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lojasocialbd.ui.theme.LojaSocialBDTheme
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.lojasocialbd.LoginScreen
import com.example.lojasocialbd.CRUDUtilizadorScreen

import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf("SPLASH") }

    // Timer para exibir a splash screen por 2 segundos
    if (currentScreen == "SPLASH") {
        SplashScreen {
            currentScreen = "LOGIN"
        }
    } else if (currentScreen == "LOGIN") {
        LoginScreen(onLoginSuccess = { tipo ->
            if (tipo == "ADM") {
                currentScreen = "ADMIN_HOME"
            } else if (tipo == "USER") {
                currentScreen = "USER_HOME"
            }
        })
    } else if (currentScreen == "ADMIN_HOME") {
        AdminHomeScreen(onOptionClick = { option ->
            when (option) {
                "Utilizadores" -> currentScreen = "CRUD"
                // Implementar lógica para outros casos
            }
        }, onLogoutClick = {
            currentScreen = "LOGIN"
        })
    } else if (currentScreen == "USER_HOME") {
        UserHomeScreen(onOptionClick = { option ->
            when (option) {
                "Visitas" -> { /* Lógica para Visitas */ }
                "Familia" -> { /* Lógica para Família */ }
                "Pessoa" -> { /* Lógica para Pessoa */ }
            }
        }, onLogoutClick = {
            currentScreen = "LOGIN"
        })
    } else if (currentScreen == "CRUD") {
        CRUDUtilizadorScreen(onVoltarClick = { currentScreen = "ADMIN_HOME" })
    }
}
@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    // Usar Handler para esperar 2 segundos e então chamar a função de callback
    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSplashFinished()
        }, 2000)
    }

    // Exibição da SplashScreen com uma imagem de fundo
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Imagem de fundo
        Image(
            painter = painterResource(id = R.drawable.splash_background),
            // Certifique-se de ter a imagem no diretório res/drawable
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Texto sobreposto à imagem
        Text(
            text = "Bem-vindo ao App",
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
  //  LoginScreen()
//}