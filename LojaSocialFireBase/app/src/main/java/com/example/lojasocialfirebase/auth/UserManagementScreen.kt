package com.example.lojasocialfirebase.auth

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.lojasocialfirebase.ui.theme.backgroundColor
import com.example.lojasocialfirebase.ui.theme.buttonColor
import com.example.lojasocialfirebase.ui.theme.textColor


@Composable
fun UserManagementScreen(viewModel: AuthViewModel) {
    var selectedOption by remember { mutableStateOf("create") } // Estado para escolher a ação
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf<String?>(null) }
    var newUserType by remember { mutableStateOf("") }
    var newEmail by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }


    Scaffold(
        containerColor = backgroundColor
    ) { paddingValues ->
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text("Gestão de Utilizadores",
            style = MaterialTheme.typography.headlineMedium,
            color = textColor)

        // Opções: Criar, Buscar/Modificar
        Row(modifier = Modifier.padding(vertical = 16.dp)) {
            Button(onClick = { selectedOption = "create" },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(8.dp),)
            {
                Text("Criar Utilizador",
                    color = Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { selectedOption = "modify" },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(8.dp),)
            {
                Text("Buscar / Modificar Utilizador",
                    color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tela de Criação de Utilizador
        if (selectedOption == "create") {
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email",
                    color = Color.White) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha",
                        color =Color.White)},
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.registerUser(email, password) { success ->
                    message = if (success) "Usuário criado com sucesso!" else "Erro ao criar usuário!"
                }},
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Gravar dados Utilizador",
                    color = Color.White)
            }
        }

        // Tela de Modificação de Utilizador
        if (selectedOption == "modify") {
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Buscar por Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                viewModel.fetchUserByEmail(email) { id, success ->
                    if (success) {
                        userId = id
                        message = "Utilizador encontrado! Modifique os dados abaixo."
                    } else {
                        message = "Utilizador não encontrado!"
                    }
                }
            }) {
                Text("Buscar Utilizador")
            }

            Spacer(modifier = Modifier.height(16.dp))
            if (userId != null) {
                TextField(
                    value = newUserType,
                    onValueChange = { newUserType = it },
                    label = { Text("Novo Tipo de Utilizador (ex: adm ou user)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = newEmail,
                    onValueChange = { newEmail = it },
                    label = { Text("Novo Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    userId?.let { id ->
                        viewModel.updateUserType(id, newUserType) { success ->
                            message = if (success) "Tipo de Utilizador atualizado!" else "Erro ao atualizar tipo!"
                        }
                    }
                }) {
                    Text("Atualizar Tipo")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    userId?.let { id ->
                        viewModel.updateUserEmail(id, newEmail) { success ->
                            message = if (success) "Email atualizado com sucesso!" else "Erro ao atualizar email!"
                        }
                    }
                }) {
                    Text("Atualizar Email")
                }
            }
        }

        // Exibição de Mensagens
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message, color = Color.Red)
    }
    }
}
