package com.example.lojasocialfirebase.gestaoIcones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lojasocialfirebase.R
import com.example.lojasocialfirebase.extrafun.StyledTextIcon
import com.example.lojasocialfirebase.ui.theme.azulPiscina
import com.example.lojasocialfirebase.ui.theme.darkSeaGreen

@Composable
fun GroupNavTesouraria(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp)
    ) {
        IconButton(onClick = { navController.navigate("tesourariaOptions") },
            modifier = Modifier.size(80.dp) // Tamanho do botão
        ) {
            Icon(
                painter = painterResource(id = R.drawable.gestaofin),
                contentDescription = "Tesouraria e Transações",
                tint = darkSeaGreen,
                modifier = Modifier.size(60.dp) // Tamanho do ícone
            )
        }
        StyledTextIcon(
            text = "Tesouraria",
            fontSize = 16,
        )
    }
}