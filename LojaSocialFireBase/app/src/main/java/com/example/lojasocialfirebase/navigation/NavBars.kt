package com.example.lojasocialfirebase.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lojasocialfirebase.R  // Importa a referência ao recurso R para acessar os ícones
import com.example.lojasocialfirebase.ui.theme.silverBrute
import com.example.lojasocialfirebase.ui.theme.silverGreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(userEmail: String) {
    TopAppBar(
        modifier = Modifier
            .padding(WindowInsets.statusBars.asPaddingValues()), // Respeita a barra de status
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center // Centraliza o texto horizontalmente
            ) {
                Text(
                    text = userEmail,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = silverGreen)
    )
}


@Composable
fun BottomNavBar(navController: NavController, onLogout: () -> Unit) {
    BottomAppBar(
        modifier = Modifier.height(42.dp), // Define a altura personalizada
        containerColor = silverGreen,
        contentColor = Color.White
    ) {
        // Ícone de Logout (esquerda)
        IconButton(onClick = { onLogout() }) {
            Icon(
                painter = painterResource(id = R.drawable.exit),
                contentDescription = "Sair",
                tint = silverBrute
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Ícone de Voltar (centro-esquerda)
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                painter = painterResource(id = R.drawable.backbutton), // Substitua pelo ícone correto
                contentDescription = "Voltar",
                tint = silverBrute
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Ícone de Casa (centro)
        IconButton(onClick = { navController.navigate("dashboard") }) {
            Icon(
                painter = painterResource(id = R.drawable.homeicon),
                contentDescription = "Página Inicial",
                tint = silverBrute
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Ícone de Notificações (direita)
        IconButton(onClick = { /* Placeholder para notificações */ }) {
            Icon(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "Notificações",
                tint = silverBrute
            )
        }
    }
}
