package com.example.lojasocialbd

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CRUDAgendaScreen(viewModel: AgendaViewModel = viewModel(), onVoltarClick: () -> Unit) {
    var isAddingAgenda by remember { mutableStateOf(false) }
    var isEditingAgenda by remember { mutableStateOf<Agenda?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Agendas", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.agendaList) { agenda ->
                AgendaItem(
                    agenda = agenda,
                    onEdit = { isEditingAgenda = agenda },
                    onDelete = { viewModel.deleteAgenda(agenda.numero) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { isAddingAgenda = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar Nova Agenda")
        }
    }

    if (isAddingAgenda) {
        AgendaDialog(
            onDismiss = { isAddingAgenda = false },
            onSave = { descricao, dataHora ->
                viewModel.addAgenda(descricao, dataHora)
                isAddingAgenda = false
            }
        )
    }

    isEditingAgenda?.let { agenda ->
        AgendaDialog(
            agenda = agenda,
            onDismiss = { isEditingAgenda = null },
            onSave = { descricao, dataHora ->
                viewModel.updateAgenda(agenda.numero, descricao, dataHora)
                isEditingAgenda = null
            }
        )
    }
}
