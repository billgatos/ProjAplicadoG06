package com.example.lojasocialbd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.lifecycleScope
import com.example.lojasocialbd.database.AppDatabase
import com.example.lojasocialbd.models.Utilizador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



// Antiga MainActivity

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MainApp()
//        }
//    }
//}

// Nova MainActivity

class MainActivity : ComponentActivity() {
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = AppDatabase.getDatabase(this)
        checkAndInsertDefaultAdmin()

        setContent {
            MainApp()
        }
    }

    private fun checkAndInsertDefaultAdmin() {
        lifecycleScope.launch {
            val utilizadorDao = database.utilizadorDao()
            val count = withContext(Dispatchers.IO) {
                utilizadorDao.countUtilizadores()
            }

            if (count == 0) {
                // Table is empty, insert default admin user
                val adminUser  = Utilizador(
                    username = "admin",
                    password = "admin123",
                    tipo = "ADMIN"
                )
                withContext(Dispatchers.IO) {
                    utilizadorDao.insert(adminUser )
                }
            }
        }
    }
}


@Composable
fun MainApp() {
    // Controla a navegação entre os ecrãs
    var currentScreen by remember { mutableStateOf("SPLASH") }
    var currentScreenType by remember { mutableStateOf("") }

    when (currentScreen) {
        "SPLASH" -> SplashScreen {
            currentScreen = "LOGIN"
        }
        "LOGIN" -> LoginScreen(onLoginSuccess = { tipo ->
            if (tipo == "ADM") {
                currentScreen = "ADMIN_HOME"
                currentScreenType = "ADMIN_HOME"

            } else if (tipo == "USER") {
                currentScreen = "USER_HOME"
                currentScreenType = "USER_HOME"
            }
        })
        "ADMIN_HOME" -> AdminHomeScreen(

            onOptionClick = { option ->
                currentScreen = when (option) {
                    "Utilizadores" -> "CRUD_UTILIZADORES"
                    //navController.navigate(
                    "Tesouraria" -> "CRUD_TESOURARIA"
                    "Visitas" -> "CRUD_VISITAS"
                    "Familia" -> "CRUD_FAMILIA"
                    "Pessoa" -> "CRUD_PESSOA"
                    "Voluntario" -> "CRUD_VOLUNTARIO"
                    "Relatórios" -> "Ecr_RelatorioVisitasScreen"
                    "Agenda" -> "CRUDAgendaScreen"
                    else -> "ADMIN_HOME"
                }
            },
            onLogoutClick = { currentScreen = "LOGIN" }
        )
        "USER_HOME" -> UserHomeScreen(
            onOptionClick = { option ->
                currentScreen = when (option) {
                    "Visitas" -> "CRUD_VISITAS"
                    "Familia" -> "CRUD_FAMILIA"
                    "Pessoa" -> "CRUD_PESSOA"
                    else -> "USER_HOME"
                }
            },
            onLogoutClick = { currentScreen = "LOGIN" }
        )
        "CRUD_UTILIZADORES" -> CRUDUtilizadorScreen(onVoltarClick = { currentScreen = "ADMIN_HOME" })
        "CRUD_TESOURARIA" -> CRUDTesourariaScreen(onVoltarClick = { currentScreen = "ADMIN_HOME" })
        "CRUD_VISITAS" -> CRUDVisitasScreen(onVoltarClick = { currentScreen = if (currentScreenType == "USER_HOME") "USER_HOME" else "ADMIN_HOME" })
        "CRUD_FAMILIA" -> CRUDFamiliaScreen(onVoltarClick = { currentScreen = if (currentScreenType == "USER_HOME") "USER_HOME" else "ADMIN_HOME" })
        "CRUD_PESSOA" -> CRUDPessoaScreen(onVoltarClick = { currentScreen = if (currentScreenType == "USER_HOME") "USER_HOME" else "ADMIN_HOME" })
        "Ecr_RelatorioVisitasScreen" -> RelatorioVisitasScreen(onVoltarClick = { currentScreen = if (currentScreenType == "USER_HOME") "USER_HOME" else "ADMIN_HOME" })
        "CRUD_VOLUNTARIO" -> CRUDVoluntarioScreen(onVoltarClick = { currentScreen = "ADMIN_HOME" })
        "CRUDAgendaScreen" -> CRUDAgendaScreen(onVoltarClick = { currentScreen = "ADMIN_HOME" })

    }
}

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    // Usar Handler para esperar 2 segundos e então chamar a função de callback
    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSplashFinished()
        }, 1500)
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
            //contentScale = ContentScale.Crop,  //corta imagem para caber na tela
            contentScale = ContentScale.Fit,    // ajusta a imagem para caber na tela
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .size(200.dp)
        )

        // Texto sobreposto à imagem
        Text(
            text = "High Tech Social App",
            fontSize = 32.sp,
            color = Color(0xFF00008B), //aZUL eSCURO
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 200.dp)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
  //  LoginScreen()
//}