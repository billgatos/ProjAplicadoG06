package com.example.lojasocialfirebase.voluntario

data class Voluntario(
    val idVoluntario: String,   // ID do voluntário (gerado automaticamente no Firebase)
    val pessoaId: String,        // Referência ao ID da Pessoa
    val nomeVoluntario: String
)
