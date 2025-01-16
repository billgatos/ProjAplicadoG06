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
import com.example.lojasocialfirebase.ui.theme.backgroundColor
import com.example.lojasocialfirebase.ui.theme.silverBlue

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor)
            )
        }
    ) { paddingValues ->
        if (loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(requests) { request ->
                    val isLocked = lockedRequests[request.id] ?: false
                    var status by remember { mutableStateOf(request.status) }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .combinedClickable(
                                onClick = {},
                                onDoubleClick = {
                                    calendarViewModel.updateRequestStatus(request.id, "Pendente") {
                                        lockedRequests[request.id] = false
                                        status = "Pendente"
                                    }
                                }
                            ),
                        colors = CardDefaults.cardColors(containerColor = silverBlue)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Voluntário: ${request.voluntarioNome}", color = Color.Black)
                            Text("Data: ${request.data}", color = Color.Black)
                            Text("Status: $status", color = Color.Black)
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Button(
                                    onClick = {
                                        calendarViewModel.updateRequestStatus(request.id, "Aprovado") {
                                            lockedRequests[request.id] = true
                                            status = "Aprovado"
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8BC34A)),
                                    enabled = !isLocked
                                ) {
                                    Text("Aprovar")
                                }
                                Button(
                                    onClick = {
                                        calendarViewModel.updateRequestStatus(request.id, "Rejeitado") {
                                            lockedRequests[request.id] = true
                                            status = "Rejeitado"
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)),
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
