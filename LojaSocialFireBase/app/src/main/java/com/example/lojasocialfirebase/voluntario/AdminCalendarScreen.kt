package com.example.lojasocialfirebase.voluntario

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lojasocialfirebase.calendario.CalendarRequest
import com.example.lojasocialfirebase.calendario.CalendarViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun AdminCalendarScreen(calendarViewModel: CalendarViewModel) {
    var requests by remember { mutableStateOf<List<CalendarRequest>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    val lockedRequests = remember { mutableStateMapOf<String, Boolean>() }

    // Função para carregar os pedidos
    fun loadRequests() {
        loading = true
        calendarViewModel.getCalendarRequests { result ->
            requests = result
            loading = false
            // Inicializar o estado de bloqueio para todos os pedidos
            result.forEach { request ->
                lockedRequests[request.id] = request.status == "Aprovado" || request.status == "Rejeitado"
            }
        }
    }

    // Carregar os pedidos ao iniciar a tela
    LaunchedEffect(Unit) {
        loadRequests()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Validação de Calendário") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF56C596))
            )
        }
    ) { paddingValues ->
        if (loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                requests.forEach { request ->
                    val isLocked = lockedRequests[request.id] ?: false
                    var status by remember { mutableStateOf(request.status) }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .combinedClickable(
                                onClick = {},
                                onDoubleClick = {
                                    calendarViewModel.updateRequestStatus(request.id, "Pendente") {
                                        lockedRequests[request.id] = false
                                        status = "Pendente"
                                    }
                                }
                            ),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF004D40))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Voluntário: ${request.voluntarioNome}", color = Color.White)
                            Text("Data: ${request.data}", color = Color.White)
                            Text("Status: $status", color = Color.White)
                            Row {
                                Button(
                                    onClick = {
                                        calendarViewModel.updateRequestStatus(request.id, "Aprovado") {
                                            lockedRequests[request.id] = true
                                            status = "Aprovado"
                                        }
                                    },
                                    enabled = !isLocked
                                ) {
                                    Text("Aprovar")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        calendarViewModel.updateRequestStatus(request.id, "Rejeitado") {
                                            lockedRequests[request.id] = true
                                            status = "Rejeitado"
                                        }
                                    },
                                    enabled = !isLocked
                                ) {
                                    Text("Rejeitar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
