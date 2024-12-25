package com.example.lojasocialfirebase.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lojasocialfirebase.R
import com.example.lojasocialfirebase.gestaoIcones.GroupNavCalendar
import com.example.lojasocialfirebase.gestaoIcones.GroupNavRegistros
import com.example.lojasocialfirebase.gestaoIcones.GroupNavTesouraria
import com.example.lojasocialfirebase.gestaoIcones.IconOptionCard
import com.example.lojasocialfirebase.ui.theme.darkGreen
import com.example.lojasocialfirebase.ui.theme.militarGreen
import com.example.lojasocialfirebase.ui.theme.textColor
import com.example.lojasocialfirebase.visita.RelatorioVisitasScreen

@Composable
fun DashboardScreen(navController: NavController) {
    Scaffold(
        containerColor = Color(0xFFF1F8E9),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Painel com ícones
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val spectralFont = FontFamily(
                        Font(R.font.spectralextrabold)
                    )
                    Text(
                        text = "Painel Administrador",
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = spectralFont,
                        color = darkGreen,
                        fontSize = 22.sp,
                        letterSpacing = 1.5.sp, // Espaçamento entre as letras
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly, // Espaçamento uniforme
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        GroupNavRegistros(navController) // Ícone de Registros
                        GroupNavTesouraria(navController) // Ícone de Tesouraria
                        GroupNavCalendar(navController) // Ícone de Calendário
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Adicionando o botão de Relatório
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly, // Espaçamento uniforme
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconOptionCard(
                            navController = navController,
                            option = DashboardOption(
                                title = "Relatório de Visitas",
                                route = "relatorioVisitas",
                                icon = R.drawable.relatorio // Ícone do relatório
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDashboardScreen() {
    // Cria um NavController fictício para o preview
    val mockNavController = rememberNavController()

    // Renderiza o DashboardScreen com o controlador fictício
    DashboardScreen(navController = mockNavController)
}
