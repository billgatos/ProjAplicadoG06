package com.example.lojasocialbd


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.lojasocialbd.models.Voluntario

@Composable
fun AddVoluntarioDialog(
    onDismiss: () -> Unit,
    onAddVoluntario: (Voluntario) -> Unit
) {
    var nome by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                if (nome.isNotBlank()) {
                    val voluntario = Voluntario(id = (0..1000).random(), nome = nome, email = "", telefone = "")
                    onAddVoluntario(voluntario)
                }
            }) {
                Text("Adicionar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        title = { Text("Adicionar Voluntário") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome do Voluntário") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}


