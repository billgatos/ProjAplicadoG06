package com.example.lojasocialfirebase.utilizadores

import androidx.compose.runtime.*
//import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

import com.example.lojasocialfirebase.auth.AuthViewModel
import com.example.lojasocialfirebase.ui.theme.CustomTextField
import com.example.lojasocialfirebase.ui.theme.backgroundColor
import com.example.lojasocialfirebase.ui.theme.buttonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterUserScreen(authViewModel: AuthViewModel, onRegisterSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedLanguage by remember { mutableStateOf("PT") } // Valor padrão PT

    val languages = listOf("PT", "EN") // Opções de idioma

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registar Utilizador") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Respeita o espaço reservado pelo Scaffold
                .padding(16.dp),
            contentAlignment = Alignment.Center // Centraliza vertical e horizontalmente
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, // Alinha o conteúdo horizontalmente
                verticalArrangement = Arrangement.Center // Espaçamento centralizado entre os elementos
            ) {
                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email"
                )
                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password"
                )

                // Dropdown de Idioma
                Text(
                    text = "Selecione o Idioma:",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                var expanded by remember { mutableStateOf(false) }
                Box {
                    OutlinedButton(onClick = { expanded = true }) {
                        Text(selectedLanguage)
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.width(100.dp)
                    ) {
                        languages.forEach { language ->
                            DropdownMenuItem(
                                text = { Text(language) },
                                onClick = {
                                    selectedLanguage = language
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        authViewModel.registerUser(email, password, selectedLanguage) { success ->
                            if (success) onRegisterSuccess()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registar", color = Color.White)
                }
            }
        }
    }
}