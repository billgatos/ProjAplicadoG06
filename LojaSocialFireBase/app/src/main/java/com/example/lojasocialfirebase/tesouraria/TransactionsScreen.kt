package com.example.lojasocialfirebase.tesouraria

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lojasocialfirebase.ui.theme.CustomTextField
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun TransactionsList(transactions: List<Transaction>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(transactions) { transaction ->
            TransactionCard(transaction = transaction)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen(viewModel: TreasuryViewModel) {
    val transactions by viewModel.transactions.collectAsState() // Observa as mudanças em tempo real
    var filteredTransactions by remember {
        mutableStateOf(transactions.sortedByDescending { it.date.toDateOrNull() })
    }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Transações") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF56C596))
            )
        },
        containerColor = Color(0xFFF1F8E9) // Fundo verde claro
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Lista de Transações",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF2E7D32)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de seleção de data
            Column {
                CustomTextField(
                    value = startDate,
                    onValueChange = { startDate = it },
                    label = "Data Inicial (yyyy-MM-dd)"
                )
                Spacer(modifier = Modifier.height(8.dp))
                CustomTextField(
                    value = endDate,
                    onValueChange = { endDate = it },
                    label = "Data Final (yyyy-MM-dd)"
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Botões "Filtrar" e "Limpar" afastados
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            // Aplicar filtro baseado no intervalo de datas, ignorando horas
                            filteredTransactions = transactions.filter { transaction ->
                                val transactionDate = transaction.date.toDateWithoutTimeOrNull()
                                val start = startDate.toDateWithoutTimeOrNull()
                                val end = endDate.toDateWithoutTimeOrNull()
                                transactionDate != null && start != null && end != null &&
                                        transactionDate in start..end
                            }.sortedByDescending { it.date.toDateOrNull() } // Ordenar pela data e hora
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF56C596)) // Verde
                    ) {
                        Text("Filtrar")
                    }

                    Button(
                        onClick = {
                            // Limpar campos e restaurar transações completas
                            startDate = ""
                            endDate = ""
                            filteredTransactions = transactions.sortedByDescending { it.date.toDateOrNull() }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)) // Vermelho
                    ) {
                        Text("Limpar", color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Exibição das Transações Filtradas
            TransactionsList(transactions = filteredTransactions)
        }
    }
}


fun String.toDateOrNull(): Date? {
    return try {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(this)
    } catch (e: Exception) {
        null
    }
}

fun String.toDateWithoutTimeOrNull(): Date? {
    return try {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
    } catch (e: Exception) {
        null
    }
}


