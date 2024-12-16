package com.example.lojasocialfirebase.navigation

import RegisterVisitaScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lojasocialfirebase.auth.*
import com.example.lojasocialfirebase.dashboard.DashboardViewModel
import com.example.lojasocialfirebase.ui.dashboard.DashboardScreen
import com.example.lojasocialfirebase.visita.*

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()
    val visitaViewModel = VisitaViewModel()
    val dashboardViewModel = DashboardViewModel()

    // Função de logout
    val onLogout = {
        authViewModel.logoutUser()
        navController.navigate("login") {
            popUpTo("login") { inclusive = true }  // Limpa a pilha de navegação
        }
    }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(authViewModel) {
                if (authViewModel.userType == "adm") {
                    navController.navigate("dashboard")
                }
            }
        }
        composable("dashboard") {
            MainScaffold(navController, authViewModel.currentUserEmail ?: "Utilizador", onLogout) { modifier ->
                DashboardScreen(navController, dashboardViewModel)
            }
        }
        composable("registerVisita") {
            MainScaffold(navController, authViewModel.currentUserEmail ?: "Utilizador", onLogout) { modifier ->
                RegisterVisitaScreen(visitaViewModel)
            }
        }
    }
}

