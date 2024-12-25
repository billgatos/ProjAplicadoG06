package com.example.lojasocialfirebase.navigation

import PessoaViewModel
import RegisterVisitaScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lojasocialfirebase.auth.*
import com.example.lojasocialfirebase.calendario.CalendarViewModel
import com.example.lojasocialfirebase.dashboard.DashboardScreen
import com.example.lojasocialfirebase.familia.FamiliaViewModel
import com.example.lojasocialfirebase.familia.GestaoFamiliasScreen
import com.example.lojasocialfirebase.familia.RegisterFamiliaScreen
import com.example.lojasocialfirebase.gestaoIcones.RegistrosOptionsScreen
import com.example.lojasocialfirebase.pessoa.GestaoPessoasScreen
import com.example.lojasocialfirebase.pessoa.RegisterPessoaScreen
import com.example.lojasocialfirebase.tesouraria.TransactionsScreen
import com.example.lojasocialfirebase.tesouraria.TreasuryOptionsScreen
import com.example.lojasocialfirebase.tesouraria.TreasuryScreen
import com.example.lojasocialfirebase.tesouraria.TreasuryViewModel
import com.example.lojasocialfirebase.utilizadores.EditUserScreen
import com.example.lojasocialfirebase.utilizadores.GestaoUtilizadoresScreen
import com.example.lojasocialfirebase.utilizadores.ListUsersScreen
import com.example.lojasocialfirebase.utilizadores.RegisterUserScreen
import com.example.lojasocialfirebase.utilizadores.UserManagementScreen
import com.example.lojasocialfirebase.visita.*
import com.example.lojasocialfirebase.voluntario.AdminCalendarScreen
import com.example.lojasocialfirebase.voluntario.GestaoVoluntariosScreen
import com.example.lojasocialfirebase.voluntario.RegisterVoluntarioScreen
import com.example.lojasocialfirebase.voluntario.VoluntarioViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()
    val visitaViewModel = VisitaViewModel()
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
                DashboardScreen(navController)
            }
        }
        // Rota Intermediária: Gestão de Visitas
        composable("gestaoVisitas") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Gestão de Visitas",
                onLogout
            ) { modifier ->
                GestaoVisitasScreen(navController)
            }
        }
        composable("registerVisita") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Utilizador",
                onLogout
            ) { modifier ->
                RegisterVisitaScreen(visitaViewModel, familiaViewModel, voluntarioViewModel)
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
                TreasuryScreen(treasuryViewModel)
            }
        }

        composable("transacoes") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Transações",
                onLogout
            ) { modifier ->
                TransactionsScreen(treasuryViewModel)
            }
        }
        composable("tesourariaOptions") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Tesouraria",
                onLogout
            ) { modifier ->
                TreasuryOptionsScreen(navController)
            }
        }
        composable("registrosOptions") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Gestão de Registros",
                onLogout
            ) { modifier ->
                RegistrosOptionsScreen(navController)
            }
        }
        composable("calendarOptions") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Calendário",
                onLogout
            ) { modifier ->
                AdminCalendarScreen(calendarViewModel)
            }
        }
        composable("gestaoFamilias") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Famílias",
                onLogout
            ) { modifier ->
                GestaoFamiliasScreen(navController = navController)
            }
        }

        composable("gestaoPessoas") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Pessoas",
                onLogout
            ) { modifier ->
                GestaoPessoasScreen(navController = navController)
            }
        }
        composable("gestaoUtilizadores") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Utilizadores",
                onLogout
            ) { modifier ->
                GestaoUtilizadoresScreen(navController = navController)
            }
        }
        composable("registerUser") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Registrar Utilizador",
                onLogout
            ) { modifier ->
                RegisterUserScreen(authViewModel) {
                    // Navegar após o registro
                    navController.navigate("dashboard") {
                        popUpTo("dashboard") { inclusive = true } // Limpa a pilha de navegação
                    }
                }
            }
        }
        composable("listUsers") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Listar Utilizadores",
                onLogout
            ) { modifier ->
                ListUsersScreen(authViewModel) // Criar a tela para listar utilizadores
            }
        }

        composable("editUser") {
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Editar Utilizador",
                onLogout
            ) { modifier ->
                EditUserScreen(authViewModel) // Criar a tela para editar utilizadores
            }
        }

        composable("gestaoVoluntarios"){
            MainScaffold(
                navController,
                authViewModel.currentUserEmail ?: "Voluntários",
                onLogout
            ) { modifier ->
                GestaoVoluntariosScreen(navController = navController)
            }
        }
    }
}


