package com.example.lojasocialfirebase.calendario

data class CalendarRequest(
    val id: String = "",              // ID do pedido de calendário
    val voluntarioId: String = "",    // ID do voluntário
    val voluntarioNome: String = "",  // Nome do voluntário
    val data: String = "",            // Data escolhida (yyyy-MM-dd)
    val status: String = "Pendente"   // Status: "Pendente", "Aprovado", "Rejeitado"
)
