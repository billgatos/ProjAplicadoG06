package com.example.lojasocialfirebase.utilizadores

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.lojasocialfirebase.gestaoIcones.IconOptionCard
import com.example.lojasocialfirebase.ui.theme.backgroundColor
import com.example.lojasocialfirebase.ui.theme.deepBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GestaoUtilizadoresScreen(navController: NavController) {
    val options = listOf(
        DashboardOption(title = "Registar Utilizadores", route = "registerUser", icon = R.drawable.adicionar),
        DashboardOption(title = "Listar Utilizadores", route = "listUsers", icon = R.drawable.ler),
        DashboardOption(title = "Editar Utilizadores", route = "editUser", icon = R.drawable.editar)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestão de Visitas") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .padding(top = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Gestão de Utilizadores",
                style = MaterialTheme.typography.headlineMedium,
                color = deepBlue
            )

            // Lista de opções para a gestão de utilizadores
            options.forEach { option ->
                IconOptionCard(navController = navController, option = option)
            }
        }
    }
}
