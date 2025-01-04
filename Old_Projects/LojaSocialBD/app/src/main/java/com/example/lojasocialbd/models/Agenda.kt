package com.example.lojasocialbd.models
import java.time.LocalDateTime

data class Agenda(
    var numero: Int = 0,
    var descricao: String = "",
    var dataHora: LocalDateTime = LocalDateTime.now(),
    val voluntarioIds: MutableList<Int> = mutableListOf()  // Lista de IDs de voluntários
) {
    override fun toString(): String {
        return "Agenda(numero=$numero, descricao='$descricao', dataHora=$dataHora)"
    }
}
