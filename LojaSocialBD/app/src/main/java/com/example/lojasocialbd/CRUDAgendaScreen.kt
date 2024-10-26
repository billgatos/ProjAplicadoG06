package com.example.lojasocialbd

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lojasocialbd.models.Agenda
import com.example.lojasocialbd.ui.theme.components.AgendaDialog
import com.example.lojasocialbd.ui.theme.components.AgendaItem
import com.example.lojasocialbd.viewmodels.AgendaViewModel

@Composable
fun CRUDAgendaScreen(viewModel: AgendaViewModel = viewModel(), onVoltarClick: () -> Unit) {
    //faz inserir e alterar agenda
    var showAddEditDialog by remember { mutableStateOf(false) }
    //seleciona um registo de agenda
    var selectedAgenda  by remember { mutableStateOf<Agenda?>(null) }
    //faz inserir e alterar voluntário
    var showAddVoluntarioDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("Gestão de Agendas",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(CenterHorizontally))


        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.agendaList) { agenda ->
                AgendaItem(
                    agenda = agenda,
                    onEdit = { selectedAgenda = agenda
                        showAddEditDialog = true },
                    onDelete = { viewModel.deleteAgenda(agenda.numero)
                               },
                    onAddVoluntario = {
                            selectedAgenda = agenda
                            showAddVoluntarioDialog = true
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                selectedAgenda = null
                showAddEditDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar Nova Agenda")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Botão Voltar
        Button(onClick = { onVoltarClick() }) {
            Text("Voltar")
        }
    }

    if (showAddEditDialog) {
        AgendaDialog(
            agenda = selectedAgenda,
            onDismiss = { showAddEditDialog = false },
            onSave = { descricao, dataHora ->
                if (selectedAgenda == null) {
                    viewModel.addAgenda(descricao, dataHora)
                } else {
                    viewModel.updateAgenda(selectedAgenda!!.numero, descricao, dataHora)
                }
                showAddEditDialog = false
            }
        )
    }

    if (showAddVoluntarioDialog) {
        AddVoluntarioToAgendaDialog(
            viewModel = viewModel,
            agenda = selectedAgenda,
            onDismiss = { showAddVoluntarioDialog = false }
        )
    }
}