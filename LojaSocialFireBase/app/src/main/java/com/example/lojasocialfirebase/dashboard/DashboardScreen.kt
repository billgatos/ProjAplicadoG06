package com.example.lojasocialfirebase.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.lojasocialfirebase.gestaoIcones.GroupNavRelatorioVisitas
import com.example.lojasocialfirebase.gestaoIcones.GroupNavTesouraria
import com.example.lojasocialfirebase.ui.theme.darkGreen

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
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly, // Espaçamento uniforme
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        GroupNavCalendar(navController) // Ícone de Calendário
                        GroupNavRelatorioVisitas(navController) //Icone do Relatorio
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
