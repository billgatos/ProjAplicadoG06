package com.example.lojasocialbd

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CRUDTesourariaScreen(onVoltarClick: () -> Unit) {
    var items by remember { mutableStateOf(listOf("Receita 1", "Receita 2", "Despesa 1")) }
    var selectedItem by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Gestão de Tesouraria", style = MaterialTheme.typography.titleLarge)

        // Lista de itens de tesouraria
        LazyColumn {
            items(items) { item ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(item)
                    Row {
                        Button(onClick = { selectedItem = item }) {
                            Text("Editar")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            items = items.filter { it != item }
                        }) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Adicionar novo item de tesouraria
        Button(onClick = { items = items + "Nova Receita/Despesa" }) {
            Text("Adicionar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Voltar
        Button(onClick = { onVoltarClick() }) {
            Text("Voltar")
        }
    }
}
