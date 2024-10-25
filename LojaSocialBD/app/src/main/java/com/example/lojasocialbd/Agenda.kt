package com.example.lojasocialbd
import java.time.LocalDateTime

data class Agenda(
    var numero: Int = 0,
    var descricao: String = "",
    var dataHora: LocalDateTime = LocalDateTime.now()
) {
    override fun toString(): String {
        return "Agenda(numero=$numero, descricao='$descricao', dataHora=$dataHora)"
    }
}
