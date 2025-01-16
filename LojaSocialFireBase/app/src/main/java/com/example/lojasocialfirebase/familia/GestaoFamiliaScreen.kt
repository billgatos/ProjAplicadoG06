package com.example.lojasocialfirebase.familia

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
fun GestaoFamiliasScreen(navController: NavController) {
    val options = listOf(
        DashboardOption(title = "Registrar Famílias", route = "registerFamilia", icon = R.drawable.adicionar),
        DashboardOption(title = "Listar Famílias", route = "listFamilias", icon = R.drawable.ler),
        DashboardOption(title = "Editar Famílias", route = "editFamilias", icon = R.drawable.editar)
    )

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text("Gestão de Famílias") },
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
                text = "Gestão de Famílias",
                style = MaterialTheme.typography.headlineMedium,
                color = deepBlue
            )

            // Lista de opções para a gestão de famílias
            options.forEach { option ->
                IconOptionCard(navController = navController, option = option)
            }
        }
    }
}
