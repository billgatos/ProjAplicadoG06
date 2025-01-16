package com.example.lojasocialfirebase.visita

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lojasocialfirebase.ui.theme.backgroundColor
import com.example.lojasocialfirebase.ui.theme.buttonColor
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListVisitasScreen(
    visitaViewModel: VisitaViewModel,
    topBarTitle: String = "Lista de Visitas",
    compactBars: Boolean = false // Parâmetro para compactar TopAppBar e BottomAppBar
) {
    var visitas by remember { mutableStateOf<List<Visita>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch visitas do Firebase
    LaunchedEffect(Unit) {
        visitaViewModel.getVisitas { fetchedVisitas ->
            visitas = fetchedVisitas
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(topBarTitle, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = buttonColor),
                modifier = if (compactBars) Modifier.height(48.dp) else Modifier
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = buttonColor)
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentPadding = PaddingValues(bottom = paddingValues.calculateBottomPadding() + 64.dp), // Garante espaço suficiente
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(visitas) { visita ->
                        VisitaCard(visita)
                    }
                }
            }
        }
    }
}


@Composable
fun VisitaCard(visita: Visita) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Nome: ${visita.nome}", style = MaterialTheme.typography.bodyLarge)
            Text("Família Ref: ${visita.familia_ref}", style = MaterialTheme.typography.bodyMedium)
            Text("Voluntário Ref: ${visita.voluntario_ref}", style = MaterialTheme.typography.bodyMedium)
            Text("Local da Loja: ${visita.localLoja}", style = MaterialTheme.typography.bodyMedium)
            Text("Nacionalidade: ${visita.nacionalidade}", style = MaterialTheme.typography.bodyMedium)
            Text("Número de Pessoas: ${visita.numero_pessoas}", style = MaterialTheme.typography.bodyMedium)
            Text("Referência: ${visita.referencia}", style = MaterialTheme.typography.bodyMedium)
            Text("Notas: ${visita.notas}", style = MaterialTheme.typography.bodyMedium)
            Text(
                "Data de Entrada: ${SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(visita.data_horaEnt)}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text("Levantar Bens: ${if (visita.levantarBens) "Sim" else "Não"}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}



