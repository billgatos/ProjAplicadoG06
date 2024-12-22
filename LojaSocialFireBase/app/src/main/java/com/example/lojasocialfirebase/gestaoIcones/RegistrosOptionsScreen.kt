package com.example.lojasocialfirebase.gestaoIcones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lojasocialfirebase.R
import com.example.lojasocialfirebase.dashboard.DashboardOption
import com.example.lojasocialfirebase.dashboard.DashboardOptionsList
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrosOptionsScreen(navController: NavController) {
    val options = listOf(
        DashboardOption(title = "Registrar Visitas", route = "registerVisita", icon = R.drawable.visita),
        DashboardOption(title = "Registrar Famílias", route = "registerFamilia", icon = R.drawable.familia),
        DashboardOption(title = "Registrar Pessoas", route = "registerPessoas", icon = R.drawable.pessoas),
        DashboardOption(title = "Registrar Utilizadores", route = "userManagement", icon = R.drawable.utilizadores),
        DashboardOption(title = "Registrar Voluntários", route = "registerVoluntario", icon = R.drawable.voluntarios)
    )

    Scaffold(
        containerColor = Color(0xFFF1F8E9),
        topBar = {
            TopAppBar(
                title = { Text("Gestão de Registros") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF56C596))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Gestão de Registros",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF2E7D32)
            )

            // Lista de opções com ícones
            options.forEach { option ->
                IconOptionCard(navController = navController, option = option)
            }
        }
    }
}
