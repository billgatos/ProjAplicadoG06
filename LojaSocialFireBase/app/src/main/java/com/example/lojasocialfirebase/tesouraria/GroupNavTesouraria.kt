package com.example.lojasocialfirebase.tesouraria

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.lojasocialfirebase.R

@Composable
fun GroupNavTesouraria(navController: NavController) {
    IconButton(onClick = { navController.navigate("tesourariaOptions") }) {
        Icon(
            painter = painterResource(id = R.drawable.gestaofin), // Usar o recurso drawable
            contentDescription = "Tesouraria e Transações",
            tint = Color(0xFF4CAF50)
        )
    }
}

