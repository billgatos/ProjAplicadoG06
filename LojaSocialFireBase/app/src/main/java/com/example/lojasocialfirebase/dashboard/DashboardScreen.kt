package com.example.lojasocialfirebase.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lojasocialfirebase.ui.theme.textColor

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
                text = "Dashboard Administrador",

                style = MaterialTheme.typography.headlineMedium,
                color = textColor




                //style = MaterialTheme.typography.headlineMedium.copy(
                  //  fontFamily = FontFamily.Serif,      // Fonte estilizada (ex.: Serif)
                    //fontWeight = FontWeight.Bold,       // Negrito para destaque
                    //letterSpacing = 1.5.sp,             // Espaçamento entre letras
                    //fontSize = 24.sp                    // Tamanho da fonte ajustado
                //),
                //color = Color(0xFF2E7D32),
                //modifier = Modifier.padding(bottom = 24.dp)
            )

            // Chamada à função reutilizável
            DashboardOptionsList(navController, dashboardViewModel.options)
        }
    }
}