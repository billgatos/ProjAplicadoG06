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



// versão com erro no mutable

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            // Estado para armazenar o tipo de utilizador
//            var currentUserType by remember { mutableStateOf<String?>(null) }
//
//            if (currentUserType == "ADM") {
//                // Se for ADM, vai para o ecrã de CRUD
//                CRUDUtilizadorScreen()
//            } else {
//                // Se não for ADM, mostra o ecrã de login
//                LoginScreen(onLoginSuccess = { tipo ->
//                    currentUserType = tipo
//                })
//            }
//        }
//    }
//}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A função remember deve estar dentro do bloco @Composable
            MainScreen()
        }
    }
}

//@Composable
//fun MainScreen() {
//    // O estado `currentUserType` precisa estar dentro de uma função @Composable
//    var currentUserType by remember { mutableStateOf<String?>(null) }
//
//    if (currentUserType == "ADM") {
//        // Ecrã de CRUD
//        CRUDUtilizadorScreen()
//    } else {
//        // Ecrã de login, ao fazer login, atualiza o tipo de utilizador
//        LoginScreen(onLoginSuccess = { tipo ->
//            currentUserType = tipo // Aqui atualiza o estado para "ADM" ou "USER"
//        })
//    }
//}

@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("LOGIN") }

    when (currentScreen) {
        "LOGIN" -> {
            LoginScreen(onLoginSuccess = { tipo ->
                if (tipo == "ADM") currentScreen = "CRUD"
            })
        }
        "CRUD" -> {
            CRUDUtilizadorScreen(onVoltarClick = {
                // Volta para a tela de login ou outra tela
                currentScreen = "LOGIN"
            })
        }
    }
}



//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
  //  LoginScreen()
//}