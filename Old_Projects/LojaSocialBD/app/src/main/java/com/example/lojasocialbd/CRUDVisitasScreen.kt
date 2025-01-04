package com.example.lojasocialbd

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CRUDVisitasScreen(onVoltarClick: () -> Unit) {
    var visitas by remember { mutableStateOf(listOf("Visita 1", "Visita 2")) }
    var selectedVisita by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Gestão de Visitas", style = MaterialTheme.typography.titleLarge)

        // Lista de visitas
        LazyColumn {
            items(visitas) { visita ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(visita)
                    Row {
                        Button(onClick = { selectedVisita = visita }) {
                            Text("Editar")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            visitas = visitas.filter { it != visita }
                        }) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Adicionar nova visita
        Button(onClick = { visitas = visitas + "Nova Visita" }) {
            Text("Adicionar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Voltar
        Button(onClick = { onVoltarClick() }) {
            Text("Voltar")
        }
    }
}
