package com.example.lojasocialfirebase.gestaoIcones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserOptionsScreen(navController: NavController) {
    val options = listOf(
        DashboardOption(title = "Gestão de Visitas", route = "gestaoVisitas", icon = R.drawable.visita),
        DashboardOption(title = "Gestão de Famílias", route = "gestaoFamilias", icon = R.drawable.familia),
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
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título na parte superior
            Text(
                text = "Gestão de Registros",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF2E7D32),
                modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
            )

            // Adiciona espaçamento abaixo do título
            Spacer(modifier = Modifier.height(110.dp))

            // Botões organizados em uma coluna centralizada
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp) // Espaçamento entre os botões
            ) {
                options.forEach { option ->
                    IconOptionCard(navController = navController, option = option)
                }
            }
        }
    }
}

