package com.example.lojasocialfirebase.pessoa

data class Pessoa(
    val idPessoa: String,           // ID da pessoa (String para referência no Firebase)
    val nome: String,
    val idade: Int,
    val contato: String,
    val dataNascimento: String,     // Usaremos String para datas no formato "yyyy-MM-dd"
    val paisCodigo: String,
    val idFamilia: String
)