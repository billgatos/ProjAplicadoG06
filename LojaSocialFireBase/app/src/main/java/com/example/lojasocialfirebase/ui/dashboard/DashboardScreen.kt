package com.example.lojasocialfirebase.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lojasocialfirebase.dashboard.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController, dashboardViewModel: DashboardViewModel) {
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
                text = "Selecione uma opção",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF2E7D32),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Renderizando as opções do dashboardViewModel
            dashboardViewModel.options.forEach { option ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFA8E6CF)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { navController.navigate(option.route) }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = option.title,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

