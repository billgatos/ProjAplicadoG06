package com.example.lojasocialfirebase.navigation

import PessoaViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lojasocialfirebase.auth.*
import com.example.lojasocialfirebase.calendario.CalendarViewModel
import com.example.lojasocialfirebase.calendario.VolunteerCalendarScreen
import com.example.lojasocialfirebase.dashboard.DashboardScreen
import com.example.lojasocialfirebase.dashboard.UserDashboardScreen
import com.example.lojasocialfirebase.familia.*
import com.example.lojasocialfirebase.gestaoIcones.RegistrosOptionsScreen
import com.example.lojasocialfirebase.gestaoIcones.UserOptionsScreen
import com.example.lojasocialfirebase.pessoa.*
import com.example.lojasocialfirebase.tesouraria.*
import com.example.lojasocialfirebase.utilizadores.*
import com.example.lojasocialfirebase.visita.*
import com.example.lojasocialfirebase.voluntario.*

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
    var voluntarios by remember { mutableStateOf<List<Voluntario>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    val onLogout = {
        authViewModel.logoutUser()
        navController.navigate("login") {
            popUpTo("login") { inclusive = true }
        }
    }

    NavHost(navController = navController, startDestination = "login") {
        // Login e Dashboard
        composable("login") {
            LoginScreen(authViewModel) {
                when (authViewModel.userType) {
                    "adm" -> navController.navigate("dashboard")
                    "user" -> navController.navigate("userDashboard")
                    else -> navController.navigate("Utilizador ou password incorretos, tente novamente.")
                }
            }
        }
        composable("dashboard") {
            MainScaffold(navController, authViewModel.currentUserEmail ?: "Utilizador", onLogout) {
                DashboardScreen(navController)
            }
        }
        composable("userDashboard") {
            MainScaffold(navController, authViewModel.currentUserEmail ?: "Utilizador", onLogout) {
                UserDashboardScreen(navController)
            }
        }

        composable("userOptionRegister") {
            MainScaffold(navController, authViewModel.currentUserEmail ?: "Utilizador", onLogout) {
                UserOptionsScreen(navController)
            }
        }

        // Subgráfico: Gestão de Registros
        navigation(startDestination = "registrosOptions", route = "registrosNavGraph") {
            composable("registrosOptions") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Gestão de Registos", onLogout) {
                    RegistrosOptionsScreen(navController)
                }
            }
            composable("gestaoVisitas") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Gestão de Visitas", onLogout) {
                    GestaoVisitasScreen(navController)
                }
            }
            composable("gestaoFamilias") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Gestão de Famílias", onLogout) {
                    GestaoFamiliasScreen(navController)
                }
            }
            composable("gestaoPessoas") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Gestão de Pessoas", onLogout) {
                    GestaoPessoasScreen(navController)
                }
            }
            composable("gestaoUtilizadores") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Gestão de Utilizadores", onLogout) {
                    GestaoUtilizadoresScreen(navController)
                }
            }
            composable("gestaoVoluntarios") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Gestão de Voluntários", onLogout) {
                    GestaoVoluntariosScreen(navController)
                }
            }
        }


        // Subgráfico: Tesouraria
        navigation(startDestination = "tesouraria", route = "tesourariaNavGraph") {
            composable("tesouraria") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Tesouraria", onLogout) {
                    TreasuryScreen(treasuryViewModel)
                }
            }
            composable("transacoes") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Transações", onLogout) {
                    TransactionsScreen(treasuryViewModel)
                }
            }
            composable("tesourariaOptions") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Opções da Tesouraria", onLogout) {
                    TreasuryOptionsScreen(navController)
                }
            }
        }

        // Subgráfico: Visitas
        navigation(startDestination = "listVisitas", route = "visitasNavGraph") {
            composable("listVisitas") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Listar Visitas", onLogout) {
                    ListVisitasScreen(visitaViewModel)
                }
            }
            composable("registerVisita") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Registar Visita", onLogout) {
                    RegisterVisitaScreen(visitaViewModel, familiaViewModel, voluntarioViewModel)
                }
            }
            composable("relatorioVisitas") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Relatório de Visitas", onLogout) {
                    RelatorioVisitasScreen(visitaViewModel)
                }
            }
        }

        // Subgráfico: Utilizadores
        navigation(startDestination = "listUsers", route = "usersNavGraph") {
            composable("listUsers") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Listar Utilizadores", onLogout) {
                    ListUsersScreen(authViewModel)
                }
            }
            composable("registerUser") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Registar Utilizador", onLogout) {
                    RegisterUserScreen(authViewModel) {
                        navController.navigate("dashboard") {
                            popUpTo("dashboard") { inclusive = true }
                        }
                    }
                }
            }
            composable("editUser") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Editar Utilizador", onLogout) {
                    EditUserScreen(authViewModel)
                }
            }
        }

        // Subgráfico: Famílias
        navigation(startDestination = "listFamilias", route = "familiasNavGraph") {
            composable("listFamilias") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Listar Famílias", onLogout) {
                    ListarFamiliasScreen(familiaViewModel)
                }
            }
            composable("registerFamilia") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Registar Família", onLogout) {
                    RegisterFamiliaScreen(familiaViewModel)
                }
            }
        }

        composable("registerCalendar") {
            // Usar LaunchedEffect para chamar a função suspensa
            LaunchedEffect(Unit) {
                try {
                    voluntarios = voluntarioViewModel.getVoluntarios() // Chama a função suspensa
                    isLoading = false
                } catch (e: Exception) {
                    e.printStackTrace()
                    isLoading = false
                }
            }

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                MainScaffold(navController, "Registar Data no Calendário", onLogout) {
                    VolunteerCalendarScreen(calendarViewModel, voluntarios)
                }
            }
        }

        // Subgráfico: Calendário (mantém outras rotas)
        navigation(startDestination = "calendarOptions", route = "calendarNavGraph") {
            composable("calendarOptions") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Opções de Calendário", onLogout) {
                    AdminCalendarScreen(calendarViewModel)
                }
            }
        }

        // Subgráfico: Gestão de Voluntários
        navigation(startDestination = "gestaoVoluntariosOptions", route = "voluntariosNavGraph") {
            composable("gestaoVoluntariosOptions") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Gestão de Voluntários", onLogout) {
                    GestaoVoluntariosScreen(navController)
                }
            }
            composable("registerVoluntario") {
                MainScaffold(navController, "Registar Voluntário", onLogout) {
                    RegisterVoluntarioScreen(voluntarioViewModel, pessoaViewModel)
                }
            }
            composable("listVoluntarios") {
                MainScaffold(navController, "Listar Voluntários", onLogout) {
                    ListVoluntariosScreen(voluntarioViewModel)
                }
            }
            /*composable("editVoluntarios") {
                MainScaffold(navController, "Editar Voluntários", onLogout) {
                    EditVoluntariosScreen(voluntarioViewModel)
                }
            }*/
        }

        // Subgráfico: Gestão de Pessoas
        navigation(startDestination = "gestaoPessoasOptions", route = "pessoasNavGraph") {
            composable("gestaoPessoasOptions") {
                MainScaffold(navController, authViewModel.currentUserEmail ?: "Gestão de Pessoas", onLogout) {
                    GestaoPessoasScreen(navController)
                }
            }
            composable("registerPessoas") {
                MainScaffold(navController, "Registar Pessoas", onLogout) {
                    RegisterPessoaScreen(pessoaViewModel, familiaViewModel)
                }
            }
            /*composable("listPessoas") {
                MainScaffold(navController, "Listar Pessoas", onLogout) {
                    ListPessoasScreen(pessoaViewModel)
                }
            }
            composable("editPessoas") {
                MainScaffold(navController, "Editar Pessoas", onLogout) {
                    EditPessoasScreen(pessoaViewModel)
                }
            }*/
        }
    }
}
