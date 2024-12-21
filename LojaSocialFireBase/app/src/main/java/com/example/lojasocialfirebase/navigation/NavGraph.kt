package com.example.lojasocialfirebase.navigation

import PessoaViewModel
import RegisterVisitaScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lojasocialfirebase.auth.*
import com.example.lojasocialfirebase.calendario.CalendarViewModel
import com.example.lojasocialfirebase.dashboard.DashboardViewModel
import com.example.lojasocialfirebase.dashboard.DashboardScreen
import com.example.lojasocialfirebase.familia.FamiliaViewModel
import com.example.lojasocialfirebase.familia.RegisterFamiliaScreen
import com.example.lojasocialfirebase.pessoa.RegisterPessoaScreen
import com.example.lojasocialfirebase.tesouraria.TransactionsScreen
import com.example.lojasocialfirebase.tesouraria.TreasuryScreen
import com.example.lojasocialfirebase.tesouraria.TreasuryViewModel
import com.example.lojasocialfirebase.visita.*
import com.example.lojasocialfirebase.voluntario.AdminCalendarScreen
import com.example.lojasocialfirebase.voluntario.RegisterVoluntarioScreen
import com.example.lojasocialfirebase.voluntario.VoluntarioViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()
    val visitaViewModel = VisitaViewModel()
    val dashboardViewModel = DashboardViewModel()
    val familiaViewModel = FamiliaViewModel()
    val pessoaViewModel = PessoaViewModel()
    val voluntarioViewModel = VoluntarioViewModel()
    val calendarViewModel = CalendarViewModel()
    val treasuryViewModel = TreasuryViewModel()

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
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Utilizador",
                onLogout
            ) { modifier ->
                DashboardScreen(navController, dashboardViewModel)
            }
        }
        composable("registerVisita") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Utilizador",
                onLogout
            ) { modifier ->
                RegisterVisitaScreen(visitaViewModel, familiaViewModel)
            }
        }
        composable("registerFamilia") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Utilizador",
                onLogout
            ) { modifier ->
                RegisterFamiliaScreen(familiaViewModel)
            }
        }
        composable("userManagement") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Utilizador",
                onLogout
            ) { modifier ->
                UserManagementScreen(authViewModel)
            }
        }
        composable("registerPessoas") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Utilizador",
                onLogout
            ) { modifier ->
                RegisterPessoaScreen(pessoaViewModel, familiaViewModel)
            }
        }
        composable("registerVoluntario") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Utilizador",
                onLogout
            ) { modifier ->
                RegisterVoluntarioScreen(voluntarioViewModel, pessoaViewModel)
            }
        }
        composable("aprovarCalendario") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Utilizador",
                onLogout
            ) { modifier ->
                AdminCalendarScreen(calendarViewModel)
            }
        }
        composable("tesouraria") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Tesouraria",
                onLogout
            ) { modifier ->
                TreasuryScreen(viewModel = treasuryViewModel)
            }
        }
        composable("transacoes") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Transações",
                onLogout
            ) { modifier ->
                TransactionsScreen(viewModel = treasuryViewModel)
            }
        }

    }
}

