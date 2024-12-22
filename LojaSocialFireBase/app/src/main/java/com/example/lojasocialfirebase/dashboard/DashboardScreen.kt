package com.example.lojasocialfirebase.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lojasocialfirebase.gestaoIcones.GroupNavCalendar
import com.example.lojasocialfirebase.gestaoIcones.GroupNavRegistros
import com.example.lojasocialfirebase.gestaoIcones.GroupNavTesouraria
import com.example.lojasocialfirebase.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    Scaffold(
        containerColor = Color(0xFFF1F8E9),
        topBar = {
            TopAppBar(
                title = { Text("Dashboard do Administrador") },
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
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Dashboard Administrador",
                style = MaterialTheme.typography.headlineMedium,
                color = textColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Linha horizontal para os ícones
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp), // Espaçamento entre os ícones
                verticalAlignment = Alignment.CenterVertically
            ) {
                GroupNavRegistros(navController) // Ícone de Registros
                GroupNavTesouraria(navController) // Ícone de Tesouraria
                GroupNavCalendar(navController) // Ícone de Calendário
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDashboardScreen() {
    val navController = rememberNavController() // NavController para navegação

    DashboardScreen(navController = navController)
}
