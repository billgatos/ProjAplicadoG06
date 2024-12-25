package com.example.lojasocialfirebase.visita

import java.util.Date

data class Visita(
    val data_horaEnt: Date,
    val familia_ref: String,
    val numero_pessoas: Int,
    val nome: String,
    val voluntario_ref: String,
    val levantarBens: Boolean,
    val localLoja: String,
    val contacto: String,
    val referencia: String,
    val notas: String,
    val nacionalidade: String
)