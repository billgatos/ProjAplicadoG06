package com.example.lojasocialfirebase.pessoa

import PessoaViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lojasocialfirebase.ui.theme.CustomDialog
import com.example.lojasocialfirebase.ui.theme.CustomTextField
import com.example.lojasocialfirebase.ui.theme.backgroundColor
import com.example.lojasocialfirebase.ui.theme.buttonColor
import com.example.lojasocialfirebase.ui.theme.textColor
import com.example.lojasocialfirebase.familia.Familia
import com.example.lojasocialfirebase.familia.FamiliaViewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.res.painterResource
import com.example.lojasocialfirebase.extrafun.CountryData

@Composable
fun RegisterPessoaScreen(
    pessoaViewModel: PessoaViewModel,
    familiaViewModel: FamiliaViewModel
) {
    var nome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var contacto by remember { mutableStateOf("") }
    var dataNascimento by remember { mutableStateOf("") }
    var paisCodigo by remember { mutableStateOf("") }
    var familiaRef by remember { mutableStateOf("") }
    var familiaNome by remember { mutableStateOf("") }

    var dialogMessage by remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()

    // Estado para armazenar a lista de famílias
    val familias = remember { mutableStateOf<List<Familia>>(emptyList()) }

    // Estado para controlar a exibição do Dialog
    var isDialogOpen by remember { mutableStateOf(false) }

    // Estado para controlar o Dropdown Menu
    var expanded by remember { mutableStateOf(false) }
    val selectedCountry = CountryData.getCountryByCode(paisCodigo)

    // Carregar as famílias ao iniciar a tela
    LaunchedEffect(Unit) {
        val result = familiaViewModel.getFamilias()
        familias.value = result

        // Log para verificar se os dados foram carregados
        println("Famílias carregadas: $result")
    }

    Scaffold(containerColor = backgroundColor) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registrar Pessoa",
                style = MaterialTheme.typography.headlineMedium,
                color = textColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Campo de seleção de Família (ID)
            Text(
                text = "Família: ${if (familiaNome.isEmpty()) "Selecionar Família" else familiaNome}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { isDialogOpen = true },
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge
            )

            //Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))

            // Dialog para seleção de família
            if (isDialogOpen) {
                AlertDialog(
                    onDismissRequest = { isDialogOpen = false },
                    title = { Text("Selecionar Família") },
                    text = {
                        Column {
                            familias.value.forEach { familia ->
                                TextButton(onClick = {
                                    familiaRef = familia.idFamilia
                                    familiaNome = familia.nome
                                    isDialogOpen = false
                                }) {
                                    Text(familia.nome)
                                }
                            }
                        }
                    },
                    confirmButton = {
                        TextButton(onClick = { isDialogOpen = false }) {
                            Text("Cancelar")
                        }
                    }
                )
            }

            CustomTextField(value = nome, onValueChange = { nome = it }, label = "Nome")
            CustomTextField(value = idade, onValueChange = { idade = it }, label = "Idade")
            CustomTextField(value = contacto, onValueChange = { contacto = it }, label = "Contato")
            CustomTextField(value = dataNascimento, onValueChange = { dataNascimento = it }, label = "Data de Nascimento (yyyy-MM-dd)")

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

            Button(
                onClick = {
                    val idadeInt = idade.toIntOrNull() ?: 0
                    val pessoa = Pessoa(
                        idPessoa = "",  // Será gerado automaticamente pelo Firebase
                        nome = nome,
                        idade = idadeInt,
                        contato = contacto,
                        dataNascimento = dataNascimento,
                        paisCodigo = paisCodigo,
                        idFamilia = familiaRef
                    )

                    pessoaViewModel.registerPessoa(pessoa) { success ->
                        if (success) {
                            // Limpar os campos após o registro bem-sucedido
                            nome = ""
                            idade = ""
                            contacto = ""
                            dataNascimento = ""
                            paisCodigo = ""
                            familiaRef = ""
                            familiaNome = ""
                            dialogMessage = "Pessoa registrada com sucesso!"
                        } else {
                            dialogMessage = "Erro ao registrar pessoa."
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar Pessoa", color = Color.White)
            }
        }
        // Mensagem de diálogo de feedback
        dialogMessage?.let { message ->
            CustomDialog(message = message, onDismiss = { dialogMessage = null })
        }
    }
}
