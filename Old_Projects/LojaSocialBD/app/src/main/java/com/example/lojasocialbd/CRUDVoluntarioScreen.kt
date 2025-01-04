package com.example.lojasocialbd

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CRUDVoluntarioScreen(onVoltarClick: () -> Unit) {
    var voluntarios by remember { mutableStateOf(listOf("Voluntário A", "Voluntário B")) }
    var selectedVoluntario by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Gestão de Voluntários", style = MaterialTheme.typography.titleLarge)

        // Lista de voluntários
        LazyColumn {
            items(voluntarios) { voluntario ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(voluntario)
                    Row {
                        Button(onClick = { selectedVoluntario = voluntario }) {
                            Text("Editar")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            voluntarios = voluntarios.filter { it != voluntario }
                        }) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Adicionar novo voluntário
        Button(onClick = { voluntarios = voluntarios + "Novo Voluntário" }) {
            Text("Adicionar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Voltar
        Button(onClick = { onVoltarClick() }) {
            Text("Voltar")
        }
    }
}
