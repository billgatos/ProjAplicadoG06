package com.example.lojasocialfirebase.dashboard

import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    val options = listOf(
        DashboardOption(title = "Registrar Visitas", route = "registerVisita"),
        DashboardOption(title = "Registrar Famílias", route = "registerFamilia"),
        DashboardOption(title = "Registrar Pessoas", route = "registerPessoas"),
        DashboardOption(title = "Registrar Utilizador", route = "userManagement"),
        DashboardOption(title = "Registrar Voluntário", route = "registerVoluntario"),
        DashboardOption(title = "Gestão Calendario", route = "aprovarCalendario")
    )
}