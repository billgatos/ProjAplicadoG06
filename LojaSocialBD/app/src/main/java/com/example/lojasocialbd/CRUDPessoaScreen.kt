package com.example.lojasocialbd

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CRUDPessoaScreen(onVoltarClick: () -> Unit) {
    var pessoas by remember { mutableStateOf(listOf("João", "Maria", "Pedro")) }
    var selectedPessoa by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Gestão de Pessoa", style = MaterialTheme.typography.titleLarge)

        // Lista de pessoas
        LazyColumn {
            items(pessoas) { pessoa ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(pessoa)
                    Row {
                        Button(onClick = { selectedPessoa = pessoa }) {
                            Text("Editar")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            pessoas = pessoas.filter { it != pessoa }
                        }) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Adicionar nova pessoa
        Button(onClick = { pessoas = pessoas + "Nova Pessoa" }) {
            Text("Adicionar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Voltar
        Button(onClick = { onVoltarClick() }) {
            Text("Voltar")
        }
    }
}
