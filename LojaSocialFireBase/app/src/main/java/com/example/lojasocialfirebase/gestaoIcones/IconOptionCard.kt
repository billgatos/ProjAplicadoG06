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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lojasocialfirebase.dashboard.DashboardOption

@Composable
fun IconOptionCard(navController: NavController, option: DashboardOption) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFA8E6CF)),
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
                tint = Color(0xFF4CAF50)
            )
            Text(
                text = option.title,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF2E7D32),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

