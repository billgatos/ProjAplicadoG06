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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolunteerCalendarScreen(calendarViewModel: CalendarViewModel, voluntarioId: String, voluntarioNome: String) {
    var selectedDate by remember { mutableStateOf("") }
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
            CustomTextField(
                value = selectedDate,
                onValueChange = { selectedDate = it },
                label = "Data (yyyy-MM-dd)"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    calendarViewModel.addCalendarRequest(voluntarioId, voluntarioNome, selectedDate) { success ->
                        dialogMessage = if (success) {
                            "Data registrada com sucesso!"
                        } else {
                            "Erro ao registrar a data."
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004D40))
            ) {
                Text("Registrar Data", color = Color.White)
            }

            dialogMessage?.let { message ->
                CustomDialog(message = message, onDismiss = { dialogMessage = null })
            }
        }
    }
}

