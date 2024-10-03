package com.example.lojasocialbd


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lojasocialbd.Utilizador
import androidx.compose.ui.graphics.Color



// Função de ecrã principal do CRUD
@Composable
fun CRUDUtilizadorScreen(onVoltarClick: () -> Unit) {
    // Lista de utilizadores (mockada para este exemplo)
    var utilizadores by remember { mutableStateOf(listOf(
        Utilizador(1, "user1", "pass1", "Nome 1", 1, "ADM"),
        Utilizador(2, "user2", "pass2", "Nome 2", 2, "USER"),
        Utilizador(3, "user3", "pass3", "Nome 3", 3, "ADM")
    )) }

    // Variável para armazenar o utilizador selecionado para edição
    var utilizadorSelecionado by remember { mutableStateOf<Utilizador?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botão Voltar
        Button(onClick = { onVoltarClick() }, colors = ButtonDefaults.buttonColors(Color.Gray)) {
            Text("Voltar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Lista de Utilizadores", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de utilizadores
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(utilizadores) { utilizador ->
                UtilizadorItem(
                    utilizador = utilizador,
                    onEditClick = { utilizadorSelecionado = it },
                    onDeleteClick = {
                        utilizadores = utilizadores.filter { u -> u.id != it.id }
                    }
                )
            }
        }

        // Se um utilizador for selecionado, exibir o formulário de edição
        utilizadorSelecionado?.let { utilizador ->
            Spacer(modifier = Modifier.height(16.dp))

            // Formulário de edição
            EditarUtilizadorForm(
                utilizador = utilizador,
                onSaveClick = {
                    utilizadores = utilizadores.map { u ->
                        if (u.id == utilizador.id) utilizador else u
                    }
                    utilizadorSelecionado = null
                },
                onCancelClick = { utilizadorSelecionado = null }
            )
        }
    }
}

@Composable
fun UtilizadorItem(
    utilizador: Utilizador,
    onEditClick: (Utilizador) -> Unit,
    onDeleteClick: (Utilizador) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = utilizador.nome, style = MaterialTheme.typography.bodyMedium)
        Row {
            Button(onClick = { onEditClick(utilizador) }) {
                Text("Editar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onDeleteClick(utilizador) }, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)) {
                Text("Eliminar")
            }
        }
    }
}

@Composable
fun EditarUtilizadorForm(
    utilizador: Utilizador,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    var username by remember { mutableStateOf(utilizador.username) }
    var password by remember { mutableStateOf(utilizador.password) }
    var nome by remember { mutableStateOf(utilizador.nome) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Editar Utilizador", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = onCancelClick, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)) {
                Text("Cancelar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                // Atualizar os valores do utilizador
                utilizador.username = username
                utilizador.password = password
                utilizador.nome = nome
                onSaveClick()
            }) {
                Text("Salvar")
            }
        }
    }
}