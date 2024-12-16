package com.example.lojasocialfirebase.viewmodel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lojasocialfirebase.models.Familia

@Composable
fun RegisterFamiliaScreen(familiaViewModel: FamiliaViewModel = viewModel()) {
    var nome by remember { mutableStateOf("") }
    var contato by remember { mutableStateOf("") }
    var paisCodigo by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registrar Família",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomTextField(value = nome, onValueChange = { nome = it }, label = "Nome")
            CustomTextField(value = contato, onValueChange = { contato = it }, label = "Contato")
            CustomTextField(value = paisCodigo, onValueChange = { paisCodigo = it }, label = "Código do País")

            Button(
                onClick = {
                    val familia = Familia(
                        nome = nome,
                        contato = contato,
                        paisCodigo = paisCodigo
                    )

                    familiaViewModel.registerFamilia(familia) { success ->
                        dialogMessage = if (success) {
                            "Família adicionada com sucesso!"
                        } else {
                            "Erro ao adicionar família."
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text("Registrar Família", color = Color.White)
            }
        }

        dialogMessage?.let { message ->
            AlertDialog(
                onDismissRequest = { dialogMessage = null },
                confirmButton = {
                    Button(onClick = { dialogMessage = null }) {
                        Text("OK")
                    }
                },
                text = { Text(message) }
            )
        }
    }
}

@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}
