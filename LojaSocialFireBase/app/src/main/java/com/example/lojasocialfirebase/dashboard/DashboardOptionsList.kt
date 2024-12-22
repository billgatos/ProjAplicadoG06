package com.example.lojasocialfirebase.dashboard
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lojasocialfirebase.ui.theme.buttonColor
import com.example.lojasocialfirebase.ui.theme.textColor

// Função reutilizável para renderizar a lista de opções do dashboard
@Composable
fun DashboardOptionsList(navController: NavController, options: List<DashboardOption>) {
    options.forEach { option ->
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
                    color = buttonColor)
            }
        }
    }
}
