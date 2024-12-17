package com.example.lojasocialfirebase.familia

import com.google.firebase.Timestamp

data class Familia(
    val idFamilia: String = "",
    val nome: String = "",
    val contato: String = "",
    val paisCodigo: String = "",
    val dataHoraRegistro: Timestamp = Timestamp.now() //Captura Data e Hora atual do dispositivo
)
