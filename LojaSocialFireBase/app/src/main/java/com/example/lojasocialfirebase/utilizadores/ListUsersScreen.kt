package com.example.lojasocialfirebase.utilizadores

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lojasocialfirebase.auth.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListUsersScreen(viewModel: AuthViewModel) {
    // Coletar os usuários do ViewModel
    val users by viewModel.users.collectAsState(initial = emptyList())

    // Carregar usuários ao iniciar a tela
    LaunchedEffect(Unit) {
        viewModel.loadUsers()
    }

    Scaffold(
        containerColor = Color(0xFFF1F8E9),
        topBar = {
            TopAppBar(
                title = { Text("Listar Utilizadores") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF56C596))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Exibir lista de usuários
            users.forEach { user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Email: ${user.email}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Tipo: ${user.type}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
