package com.example.lojasocialfirebase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lojasocialfirebase.auth.*
import com.example.lojasocialfirebase.visita.*

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()
    val visitaViewModel = VisitaViewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(authViewModel) {
                if (authViewModel.userType == "adm") {
                    navController.navigate("registerVisita")
                } else {
                    // Notificar que o usuário não tem permissão
                }
            }
        }
        composable("register") {
            RegisterScreen(authViewModel) {
                navController.navigate("login")
            }
        }
        composable("registerVisita") {
            RegisterVisitaScreen(visitaViewModel)
        }
    }
}
