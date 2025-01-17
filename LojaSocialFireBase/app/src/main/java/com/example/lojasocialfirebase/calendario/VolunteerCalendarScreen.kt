package com.example.lojasocialfirebase.calendario

import com.example.lojasocialfirebase.ui.theme.CustomDialog
import com.example.lojasocialfirebase.ui.theme.CustomTextField
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lojasocialfirebase.voluntario.Voluntario


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolunteerCalendarScreen(calendarViewModel: CalendarViewModel, voluntarios: List<Voluntario>) {
    var selectedDate by remember { mutableStateOf("") }
    var selectedVoluntario by remember { mutableStateOf<Voluntario?>(null) }
    var expanded by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registar Data") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF56C596))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Dropdown para selecionar o voluntário
            Box {
                Button(
                    onClick = { expanded = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004D40))
                ) {
                    Text(
                        text = selectedVoluntario?.nomeVoluntario ?: "Selecionar Voluntário",
                        color = Color.White
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    voluntarios.forEach { voluntario ->
                        DropdownMenuItem(
                            text = { Text(voluntario.nomeVoluntario) },
                            onClick = {
                                selectedVoluntario = voluntario
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo para selecionar a data
            CustomTextField(
                value = selectedDate,
                onValueChange = { selectedDate = it },
                label = "Data (yyyy-MM-dd)"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botão para registrar a data
            Button(
                onClick = {
                    if (selectedVoluntario != null && selectedDate.isNotEmpty()) {
                        calendarViewModel.addCalendarRequest(
                            selectedVoluntario!!.idVoluntario,
                            selectedVoluntario!!.nomeVoluntario,
                            selectedDate
                        ) { success ->
                            dialogMessage = if (success) {
                                "Data registada com sucesso!"
                            } else {
                                "Erro ao registar a data."
                            }
                        }
                    } else {
                        dialogMessage = "Por favor, selecione um voluntário e uma data."
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004D40))
            ) {
                Text("Registar Data", color = Color.White)
            }

            dialogMessage?.let { message ->
                CustomDialog(message = message, onDismiss = { dialogMessage = null })
            }
        }
    }
}
