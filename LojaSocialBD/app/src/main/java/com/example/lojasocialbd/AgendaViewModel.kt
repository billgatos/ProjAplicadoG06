package com.example.lojasocialbd


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime

class AgendaViewModel : ViewModel() {
    private var lastId = 0
    val agendaList = mutableStateListOf<Agenda>()

    fun addAgenda(descricao: String, dataHora: LocalDateTime) {
        val newAgenda = Agenda(numero = ++lastId, descricao = descricao, dataHora = dataHora)
        agendaList.add(newAgenda)
    }

    fun updateAgenda(numero: Int, descricao: String, dataHora: LocalDateTime) {
        val agenda = agendaList.find { it.numero == numero }
        agenda?.apply {
            this.descricao = descricao
            this.dataHora = dataHora
        }
    }

    fun deleteAgenda(numero: Int) {
        agendaList.removeAll { it.numero == numero }
    }
}
