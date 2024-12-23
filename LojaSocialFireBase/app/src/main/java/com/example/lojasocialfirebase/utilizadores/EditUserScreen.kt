package com.example.lojasocialfirebase.utilizadores

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lojasocialfirebase.auth.AuthViewModel
import com.example.lojasocialfirebase.ui.theme.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserScreen(viewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var newType by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color(0xFFF1F8E9),
        topBar = {
            TopAppBar(
                title = { Text("Editar Utilizador") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF56C596))
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center // Centraliza o conteúdo
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, // Alinha o conteúdo horizontalmente
                verticalArrangement = Arrangement.Center, // Espaçamento entre os itens
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                // Campo para o e-mail
                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email do Utilizador"
                )

                // Campo para o novo tipo
                CustomTextField(
                    value = newType,
                    onValueChange = { newType = it },
                    label = "Novo Tipo (adm ou user)"
                )

                // Botão para atualizar o tipo do usuário
                Button(
                    onClick = {
                        viewModel.updateUserType(email, newType) { success ->
                            message = if (success) "Tipo atualizado com sucesso!" else "Erro ao atualizar tipo!"
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF56C596))
                ) {
                    Text("Atualizar Tipo", color = Color.White)
                }

                // Mensagem de feedback
                if (message.isNotEmpty()) {
                    Text(
                        text = message,
                        color = if (message.contains("sucesso")) Color.Green else Color.Red,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
