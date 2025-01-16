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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lojasocialfirebase.R
import com.example.lojasocialfirebase.ui.theme.backgroundColor
import com.example.lojasocialfirebase.ui.theme.buttonColor
import com.example.lojasocialfirebase.ui.theme.deepBlue
import com.example.lojasocialfirebase.ui.theme.silverBlue
import com.example.lojasocialfirebase.ui.theme.textColor

// Função reutilizável para renderizar a lista de opções do dashboard
@Composable
fun DashboardOptionsList(navController: NavController, options: List<DashboardOption>) {
    options.forEach { option ->
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = silverBlue),
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
                    color = deepBlue)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardOptionsListPreview() {
    val navController = rememberNavController()
    val options = listOf(
        DashboardOption(title = "Opção 1", route = "route1", icon = R.drawable.ic_launcher_foreground),
        DashboardOption(title = "Opção 2", route = "route2", icon = R.drawable.ic_launcher_foreground),
        DashboardOption(title = "Opção 3", route = "route3", icon = R.drawable.ic_launcher_foreground)
    )
    DashboardOptionsList(navController = navController, options = options)
}

