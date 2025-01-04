package com.example.lojasocialbd

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CRUDFamiliaScreen(onVoltarClick: () -> Unit) {
    var familias by remember { mutableStateOf(listOf("Família Silva", "Família Santos")) }
    var selectedFamilia by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Gestão de Família", style = MaterialTheme.typography.titleLarge)

        // Lista de famílias
        LazyColumn {
            items(familias) { familia ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(familia)
                    Row {
                        Button(onClick = { selectedFamilia = familia }) {
                            Text("Editar")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            familias = familias.filter { it != familia }
                        }) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Adicionar nova família
        Button(onClick = { familias = familias + "Nova Família" }) {
            Text("Adicionar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Voltar
        Button(onClick = { onVoltarClick() }) {
            Text("Voltar")
        }
    }
}
