package com.example.lojasocialfirebase.dashboard

import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    val options = listOf(
        DashboardOption(title = "Registar Visitas", route = "registerVisita"),
        DashboardOption(title = "Registar Famílias", route = "registerFamilia"),
        DashboardOption(title = "Registar Pessoas", route = "registerPessoas"),
        DashboardOption(title = "Registar Utilizador", route = "userManagement"),
        DashboardOption(title = "Registar Voluntário", route = "registerVoluntario"),
        DashboardOption(title = "Gestão Calendario", route = "aprovarCalendario")
    )
}