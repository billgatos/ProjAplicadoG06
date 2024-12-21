package com.example.lojasocialfirebase.voluntario

import PessoaViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lojasocialfirebase.pessoa.Pessoa
import com.example.lojasocialfirebase.ui.theme.CustomDialog
import com.example.lojasocialfirebase.ui.theme.CustomTextField
import com.example.lojasocialfirebase.ui.theme.backgroundColor
import com.example.lojasocialfirebase.ui.theme.buttonColor
import com.example.lojasocialfirebase.ui.theme.textColor

@Composable
fun RegisterVoluntarioScreen(
    voluntarioViewModel: VoluntarioViewModel,
    pessoaViewModel: PessoaViewModel
) {
    var nome by remember { mutableStateOf("") }
    var pessoaId by remember { mutableStateOf("") }
    var pessoaNome by remember { mutableStateOf("") }

    var dialogMessage by remember { mutableStateOf<String?>(null) }
    var isDialogOpen by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    // Estado para armazenar a lista de pessoas
    val pessoas = remember { mutableStateOf<List<Pessoa>>(emptyList()) }

    // Carregar as pessoas ao iniciar a tela
    LaunchedEffect(Unit) {
        val result = pessoaViewModel.getPessoas()
        pessoas.value = result
    }

    Scaffold(containerColor = backgroundColor) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Registar Voluntário",
                style = MaterialTheme.typography.headlineMedium,
                color = textColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Campo de seleção de Pessoa (ID)
            Text(
                text = "Pessoa: ${if (pessoaNome.isEmpty()) "Selecionar Pessoa" else pessoaNome}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { isDialogOpen = true },
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge
            )

            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))

            // Dialog para seleção de Pessoa
            if (isDialogOpen) {
                AlertDialog(
                    onDismissRequest = { isDialogOpen = false },
                    title = { Text("Selecionar Pessoa") },
                    text = {
                        Column {
                            pessoas.value.forEach { pessoa ->
                                TextButton(onClick = {
                                    pessoaId = pessoa.idPessoa
                                    pessoaNome = pessoa.nome
                                    nome = pessoa.nome
                                    isDialogOpen = false
                                }) {
                                    Text(pessoa.nome)
                                }
                            }
                        }
                    },
                    confirmButton = {
                        TextButton(onClick = { isDialogOpen = false }) {
                            Text("Cancelar")
                        }
                    }
                )
            }

            CustomTextField(value = nome, onValueChange = { nome = it }, label = "Nome")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val voluntario = Voluntario(
                        idVoluntario = "",
                        pessoaId = pessoaId,
                        nomeVoluntario = nome
                    )

                    voluntarioViewModel.registerVoluntario(voluntario) { success ->
                        if (success) {
                            nome = ""
                            pessoaId = ""
                            pessoaNome = ""
                            dialogMessage = "Voluntário registado com sucesso!"
                        } else {
                            dialogMessage = "Erro ao registar voluntário."
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registar Voluntário", color = Color.White)
            }

            dialogMessage?.let { message ->
                CustomDialog(message = message, onDismiss = { dialogMessage = null })
            }
        }
    }
}
