package com.example.lojasocialfirebase.gestaoIcones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lojasocialfirebase.R
import com.example.lojasocialfirebase.dashboard.DashboardOption
import com.example.lojasocialfirebase.ui.theme.backgroundColor
import com.example.lojasocialfirebase.ui.theme.deepBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrosOptionsScreen(navController: NavController) {
    val options = listOf(
        DashboardOption(title = "Gestão de Visitas", route = "gestaoVisitas", icon = R.drawable.visita),
        DashboardOption(title = "Gestão de Famílias", route = "gestaoFamilias", icon = R.drawable.familia),
        DashboardOption(title = "Gestão de Pessoas", route = "gestaoPessoas", icon = R.drawable.pessoas),
        DashboardOption(title = "Gestão de Utilizadores", route = "gestaoUtilizadores", icon = R.drawable.utilizadores),
        DashboardOption(title = "Gestão de Voluntários", route = "gestaoVoluntarios", icon = R.drawable.voluntarios)
    )

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text("Gestão de Registos") },
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
                text = "Gestão de Registos",
                style = MaterialTheme.typography.headlineMedium,
                color = deepBlue,
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

@Preview(showBackground = true)
@Composable
fun RegistrosOptionsScreenPreview() {
    val navController = rememberNavController()
    RegistrosOptionsScreen(navController = navController)
}


