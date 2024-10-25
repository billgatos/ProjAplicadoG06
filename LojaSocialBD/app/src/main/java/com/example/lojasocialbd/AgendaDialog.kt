package com.example.lojasocialbd

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.lojasocialbd.Agenda
import java.time.LocalDateTime

@Composable
fun AgendaDialog(
    agenda: Agenda? = null,
    onDismiss: () -> Unit,
    onSave: (descricao: String, dataHora: LocalDateTime) -> Unit
) {
    var descricao by remember { mutableStateOf(agenda?.descricao ?: "") }
    var dataHora by remember { mutableStateOf(agenda?.dataHora ?: LocalDateTime.now()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = { onSave(descricao, dataHora) }
            ) {
                Text("Salvar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        title = { Text(text = if (agenda == null) "Adicionar Agenda" else "Editar Agenda") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                BasicTextField(
                    value = descricao,
                    onValueChange = { descricao = it },
                    singleLine = true
                )
            }
        }
    )
}
