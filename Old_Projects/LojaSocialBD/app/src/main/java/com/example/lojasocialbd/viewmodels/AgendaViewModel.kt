package com.example.lojasocialbd.viewmodels
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.lojasocialbd.models.Agenda
import com.example.lojasocialbd.models.Voluntario
import java.time.LocalDateTime

class AgendaViewModel : ViewModel() {
    private var lastId = 0
    private var lastVoluntarioId = 0

    val agendaList = mutableStateListOf<Agenda>()
    val voluntarioList = mutableStateListOf<Voluntario>()

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

    fun getVoluntarioById(id: Int): Voluntario? = voluntarioList.find { it.id == id }

    fun deleteVoluntario(id: Int) {
        voluntarioList.removeAll { it.id == id }
    }

    // Métodos para associar e desassociar voluntários em uma Agenda
    fun addVoluntarioToAgenda(agendaNumero: Int, voluntarioId: Int) {
        agendaList.find { it.numero == agendaNumero }?.voluntarioIds?.add(voluntarioId)
    }

    fun removeVoluntarioFromAgenda(agendaNumero: Int, voluntarioId: Int) {
        agendaList.find { it.numero == agendaNumero }?.voluntarioIds?.remove(voluntarioId)
    }
}
