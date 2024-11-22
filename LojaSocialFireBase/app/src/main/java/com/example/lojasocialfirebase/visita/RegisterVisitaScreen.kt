package com.example.lojasocialfirebase.visita

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Date
import androidx.compose.material3.Text
import androidx.compose.material3.TextField


@Composable
fun RegisterVisitaScreen(visitaViewModel: VisitaViewModel) {
    var nome by remember { mutableStateOf("") }
    var numeroPessoas by remember { mutableStateOf("") }
    var familiaRef by remember { mutableStateOf("") }
    var voluntarioRef by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
        TextField(
            value = numeroPessoas,
            onValueChange = { numeroPessoas = it },
            label = { Text("Número de Pessoas") }
        )
        TextField(value = familiaRef, onValueChange = { familiaRef = it }, label = { Text("Família (ID)") })
        TextField(value = voluntarioRef, onValueChange = { voluntarioRef = it }, label = { Text("Voluntário (ID)") })

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val visita = Visita(
                data_hora = Date(),
                numero_pessoas = numeroPessoas.toInt(),
                nome = nome,
                familia_ref = familiaRef,
                voluntario_ref = voluntarioRef
            )
            visitaViewModel.registerVisita(visita) { success ->
                if (success) {
                    // Ação para sucesso no registro
                }
            }
        }) {
            Text("Registrar Visita")
        }
    }
}
