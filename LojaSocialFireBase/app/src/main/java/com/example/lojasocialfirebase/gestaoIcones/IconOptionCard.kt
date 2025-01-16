package com.example.lojasocialfirebase.gestaoIcones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lojasocialfirebase.R
import com.example.lojasocialfirebase.dashboard.DashboardOption
import com.example.lojasocialfirebase.ui.theme.borderColor
import com.example.lojasocialfirebase.ui.theme.darkSeaGreen
import com.example.lojasocialfirebase.ui.theme.deepBlue
import com.example.lojasocialfirebase.ui.theme.silverBlue
import com.example.lojasocialfirebase.ui.theme.steelBlue
import com.example.lojasocialfirebase.ui.theme.textColor

@Composable
fun IconOptionCard(navController: NavController, option: DashboardOption) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = steelBlue),
        onClick = { navController.navigate(option.route) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = option.icon),
                contentDescription = option.title,
                modifier = Modifier.size(40.dp),
                tint = silverBlue
            )
            Text(
                text = option.title,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IconOptionCardPreview() {
    val navController = rememberNavController()
    val option = DashboardOption(title = "Exemplo", route = "exampleRoute", icon = R.drawable.visita)
    IconOptionCard(navController = navController, option = option)
}