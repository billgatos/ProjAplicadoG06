package com.example.lojasocialfirebase.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.lojasocialfirebase.R  // Importa a referência ao recurso R para acessar os ícones


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(userEmail: String) {
    TopAppBar(
        title = {
            Text(
                text = userEmail,
                color = Color.White,
                textAlign = TextAlign.Start
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF56C596))
    )
}

@Composable
fun BottomNavBar(navController: NavController, onLogout: () -> Unit) {
    BottomAppBar(
        containerColor = Color(0xFF56C596),
        contentColor = Color.White
    ) {
        // Ícone de Logout (esquerda)
        IconButton(onClick = { onLogout() }) {
            Icon(
                painter = painterResource(id = R.drawable.exit),
                contentDescription = "Sair",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Ícone de Casa (centro)
        IconButton(onClick = { navController.navigate("dashboard") }) {
            Icon(
                painter = painterResource(id = R.drawable.homeicon),
                contentDescription = "Página Inicial",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Ícone de Notificações (direita)
        IconButton(onClick = { /* Placeholder para notificações */ }) {
            Icon(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "Notificações",
                tint = Color.White
            )
        }
    }
}
