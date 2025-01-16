package com.example.lojasocialfirebase.tesouraria

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
import com.example.lojasocialfirebase.ui.theme.backgroundColor
import com.example.lojasocialfirebase.ui.theme.deepBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreasuryOptionsScreen(navController: NavController) {
    val options = listOf(
        DashboardOption(title = "Tesouraria", route = "tesouraria", icon = R.drawable.fluxocaixa),
        DashboardOption(title = "Ver Transações", route = "transacoes", icon = R.drawable.historicotrans)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Opções da Tesouraria") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor)
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
                text = "Gestão Tesouraria",
                style = MaterialTheme.typography.headlineMedium,
                color = deepBlue
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Usando a função reutilizável para exibir as opções
            DashboardOptionsList(navController = navController, options = options)
        }
    }
}
