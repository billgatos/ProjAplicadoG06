package com.example.lojasocialfirebase.visita

import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lojasocialfirebase.ui.theme.CustomDialog
import com.example.lojasocialfirebase.ui.theme.CustomTextField
import com.example.lojasocialfirebase.ui.theme.backgroundColor
import com.example.lojasocialfirebase.ui.theme.buttonColor
import com.example.lojasocialfirebase.ui.theme.textColor
import com.example.lojasocialfirebase.visita.Visita
import com.example.lojasocialfirebase.visita.VisitaViewModel
import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.navigation.NavController
import com.example.lojasocialfirebase.R
import com.example.lojasocialfirebase.dashboard.DashboardOption
import com.example.lojasocialfirebase.familia.Familia
import com.example.lojasocialfirebase.familia.FamiliaViewModel
import com.example.lojasocialfirebase.gestaoIcones.IconOptionCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GestaoVisitasScreen(navController: NavController) {
    val options = listOf(
        DashboardOption(title = "Registrar Visitas", route = "registerVisita", icon = R.drawable.adicionar),
        DashboardOption(title = "Listar Visitas", route = "listVisitas", icon = R.drawable.ler),
        DashboardOption(title = "Editar Visitas", route = "editVisitas", icon = R.drawable.editar)
    )

    Scaffold(
        containerColor = Color(0xFFF1F8E9),
        topBar = {
            TopAppBar(
                title = { Text("Gestão de Visitas") },
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
                text = "Gestão de Visitas",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF2E7D32)
            )

            // Lista de opções para a gestão de visitas
            options.forEach { option ->
                IconOptionCard(navController = navController, option = option)
            }
        }
    }
}
