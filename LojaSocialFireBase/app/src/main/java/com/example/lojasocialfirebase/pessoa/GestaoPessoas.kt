package com.example.lojasocialfirebase.pessoa

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GestaoPessoasScreen(navController: NavController) {
    val options = listOf(
        DashboardOption(title = "Registrar Pessoas", route = "registerPessoas", icon = R.drawable.adicionar),
        DashboardOption(title = "Listar Pessoas", route = "listPessoas", icon = R.drawable.ler),
        DashboardOption(title = "Editar Pessoas", route = "editPessoas", icon = R.drawable.editar)
    )

    Scaffold(
        containerColor = Color(0xFFF1F8E9),
        topBar = {
            TopAppBar(
                title = { Text("Gestão de Pessoas") },
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
                text = "Gestão de Pessoas",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF2E7D32)
            )

            // Lista de opções para a gestão de pessoas
            options.forEach { option ->
                IconOptionCard(navController = navController, option = option)
            }
        }
    }
}
