package com.example.lojasocialbd

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserHomeScreen(onOptionClick: (String) -> Unit, onLogoutClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Bem-vindo, Utilizador", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = { onOptionClick("Visitas") }) {
            Text("Visitas")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onOptionClick("Familia") }) {
            Text("Família")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onOptionClick("Pessoa") }) {
            Text("Pessoa")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { onLogoutClick() }, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)) {
            Text("Logout")
        }
    }
}
