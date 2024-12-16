package com.example.lojasocialfirebase.dashboard

import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    val options = listOf(
        DashboardOption(title = "Registrar Visitas", route = "registerVisita")
    )
}