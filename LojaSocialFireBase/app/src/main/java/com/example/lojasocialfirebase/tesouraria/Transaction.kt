package com.example.lojasocialfirebase.tesouraria

data class Transaction(
    val id: String = "",            // ID da transação
    val amount: Double = 0.0,       // Valor da transação
    val description: String = "",   // Descrição da atividade
    val date: String = "",          // Data da transação no formato "yyyy-MM-dd HH:mm:ss"
    val type: String = ""           // Tipo: "Entrada" ou "Saída"
)
