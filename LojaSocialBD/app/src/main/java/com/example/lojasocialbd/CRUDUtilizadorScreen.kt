package com.example.lojasocialbd


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lojasocialbd.Utilizador

@Composable
fun CRUDUtilizadorScreen() {
    var utilizadores by remember { mutableStateOf(listOf<Utilizador>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Gestão de Utilizadores", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de utilizadores
        LazyColumn {
            items(utilizadores.size) { index ->
                val utilizador = utilizadores[index]
                Text(text = utilizador.toString())
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para adicionar um utilizador fictício
        Button(
            onClick = {
                utilizadores = utilizadores + Utilizador(
                    id = utilizadores.size + 1,
                    username = "user${utilizadores.size + 1}",
                    password = "password${utilizadores.size + 1}",
                    nome = "Utilizador ${utilizadores.size + 1}",
                    id_pessoa = utilizadores.size + 1,
                    tipo = "ADM"
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Adicionar Utilizador")
        }
    }
}