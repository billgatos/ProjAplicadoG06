package com.example.lojasocialfirebase.visita

import java.util.Date

data class Visita(
    val data_horaEnt: Date = Date(),
    val familia_ref: String = "",
    val numero_pessoas: Int = 0,
    val nome: String = "",
    val voluntario_ref: String = "",
    val levantarBens: Boolean = false,
    val localLoja: String = "",
    val contacto: String = "",
    val referencia: String = "",
    val notas: String = "",
    val nacionalidade: String = ""
) {
    // Construtor sem argumentos necessário para Firebase
    constructor() : this(
        data_horaEnt = Date(),
        familia_ref = "",
        numero_pessoas = 0,
        nome = "",
        voluntario_ref = "",
        levantarBens = false,
        localLoja = "",
        contacto = "",
        referencia = "",
        notas = "",
        nacionalidade = ""
    )
}
