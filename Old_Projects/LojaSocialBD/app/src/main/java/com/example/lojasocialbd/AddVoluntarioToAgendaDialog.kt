package com.example.lojasocialbd

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lojasocialbd.models.Agenda
import com.example.lojasocialbd.viewmodels.AgendaViewModel

@Composable
fun AddVoluntarioToAgendaDialog(
    viewModel: AgendaViewModel,
    agenda: Agenda?,
    onDismiss: () -> Unit
) {
    val voluntarios = remember { viewModel.voluntarioList }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Fechar")
            }
        },
        title = { Text("Associar Voluntário") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                voluntarios.forEach { voluntario ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                agenda?.let {
                                    viewModel.addVoluntarioToAgenda(it.numero, voluntario.id)
                                }
                            },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(voluntario.nome)
                        if (agenda?.voluntarioIds?.contains(voluntario.id) == true) {
                            Text("Associado", color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    )
}
