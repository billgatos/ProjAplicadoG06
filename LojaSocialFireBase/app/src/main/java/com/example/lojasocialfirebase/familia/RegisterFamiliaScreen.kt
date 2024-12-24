package com.example.lojasocialfirebase.familia

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lojasocialfirebase.extrafun.CountryData
import com.example.lojasocialfirebase.ui.theme.CustomDialog
import com.example.lojasocialfirebase.ui.theme.CustomTextField
import com.example.lojasocialfirebase.ui.theme.backgroundColor
import com.example.lojasocialfirebase.ui.theme.buttonColor
import com.example.lojasocialfirebase.ui.theme.textColor

@Composable
fun RegisterFamiliaScreen(familiaViewModel: FamiliaViewModel = viewModel()) {
    var nome by remember { mutableStateOf("") }
    var contato by remember { mutableStateOf("") }
    var paisCodigo by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf<String?>(null) }

    // Estado para controlar o Dropdown Menu
    var expanded by remember { mutableStateOf(false) }
    val selectedCountry = CountryData.getCountryByCode(paisCodigo)

    Scaffold(
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registar Família",
                style = MaterialTheme.typography.headlineMedium,
                color = textColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomTextField(value = nome, onValueChange = { nome = it }, label = "Nome")
            CustomTextField(value = contato, onValueChange = { contato = it }, label = "Contato")

            // Dropdown Menu para seleção de país
            Box {
                Button(
                    onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                ) {
                    Text(
                        text = selectedCountry?.name ?: "Selecionar País",
                        color = Color.White
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CountryData.countries.forEach { country ->
                        DropdownMenuItem(
                            text = { Text(country.name) },
                            onClick = {
                                paisCodigo = country.code
                                expanded = false
                            },
                            leadingIcon = {
                                Image(
                                    painter = painterResource(id = country.flag),
                                    contentDescription = "Bandeira de ${country.name}",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Exibir nome do país e a bandeira se o país for selecionado
            selectedCountry?.let {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = it.flag),
                        contentDescription = "Bandeira de ${it.name}",
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = textColor
                    )
                }
            }

            Button(
                onClick = {
                    val familia = Familia(
                        nome = nome,
                        contato = contato,
                        paisCodigo = paisCodigo
                    )

                    familiaViewModel.registerFamilia(familia) { success ->
                        if (success) {
                            nome = ""
                            contato = ""
                            paisCodigo = ""
                            dialogMessage =  "Família adicionada com sucesso!"
                        } else {
                            dialogMessage = "Erro ao adicionar família."
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registar Família", color = Color.White)
            }
        }

        dialogMessage?.let { message ->
            CustomDialog(message = message, onDismiss = { dialogMessage = null })
        }
    }
}

