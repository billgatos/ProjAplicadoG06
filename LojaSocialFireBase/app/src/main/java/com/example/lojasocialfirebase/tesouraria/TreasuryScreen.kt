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
    val balance by viewModel.balance.collectAsState() // Saldo atualizado em tempo real

    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // Reconfigurar o listener quando a tela é exibida novamente
    LaunchedEffect(Unit) {
        viewModel.refreshListener()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tesouraria") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF56C596))
            )
        },
        containerColor = Color(0xFFF1F8E9)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Saldo Atual: € ${"%.2f".format(balance)}",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF2E7D32)
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(value = amount, onValueChange = { amount = it }, label = "Valor")
            CustomTextField(value = description, onValueChange = { description = it }, label = "Descrição")

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        if (amount.isNotEmpty() && description.isNotEmpty()) {
                            viewModel.addTransaction(amount.toDouble(), description, "Entrada")
                            amount = ""
                            description = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
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
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8BC34A))
                ) {
                    Text("Adicionar Saída", color = Color.White)
                }
            }
        }
    }
}


