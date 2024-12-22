package com.example.lojasocialfirebase.tesouraria

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lojasocialfirebase.ui.theme.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreasuryScreen(viewModel: TreasuryViewModel) {
    val balance by viewModel.balance.collectAsState()

    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tesouraria") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF56C596))
            )
        },
        containerColor = Color(0xFFF1F8E9) // Fundo verde claro
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Saldo Atual
            Text(
                text = "Saldo Atual: € ${"%.2f".format(balance)}",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF2E7D32)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de Entrada
            CustomTextField(value = amount, onValueChange = { amount = it }, label = "Valor")
            CustomTextField(value = description, onValueChange = { description = it }, label = "Descrição")

            Spacer(modifier = Modifier.height(16.dp))

            // Botões para Entrada e Saída
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        if (amount.isNotEmpty() && description.isNotEmpty()) {
                            viewModel.addTransaction(amount.toDouble(), description, "Entrada")
                            amount = ""
                            description = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Verde escuro
                ) {
                    Text("Adicionar Entrada", color = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (amount.isNotEmpty() && description.isNotEmpty()) {
                            viewModel.addTransaction(amount.toDouble(), description, "Saída")
                            amount = ""
                            description = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8BC34A)) // Verde médio
                ) {
                    Text("Adicionar Saída", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

