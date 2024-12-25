package com.example.lojasocialfirebase.visita

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
import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.lojasocialfirebase.familia.Familia
import com.example.lojasocialfirebase.familia.FamiliaViewModel
import com.example.lojasocialfirebase.voluntario.Voluntario
import com.example.lojasocialfirebase.voluntario.VoluntarioViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterVisitaScreen(
    visitaViewModel: VisitaViewModel,
    familiaViewModel: FamiliaViewModel,
    voluntarioViewModel: VoluntarioViewModel
) {
    var data_horaEnt by remember { mutableStateOf(Date()) }
    var familiaRef by remember { mutableStateOf("") }
    var familiaNome by remember { mutableStateOf("") }
    var numeroPessoas by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var voluntarioRef by remember { mutableStateOf("") }
    var voluntarioNome by remember { mutableStateOf("") }
    var levantarBens by remember { mutableStateOf(false) }
    var localLoja by remember { mutableStateOf("") }
    var contacto by remember { mutableStateOf("") }
    var referencia by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }
    var nacionalidade by remember { mutableStateOf("") }

    var dialogMessage by remember { mutableStateOf<String?>(null) }
    val familias = remember { mutableStateOf<List<Familia>>(emptyList()) }
    var isFamiliaDialogOpen by remember { mutableStateOf(false) }

    val voluntarios = remember { mutableStateOf<List<Voluntario>>(emptyList()) }
    var isVoluntarioDialogOpen by remember { mutableStateOf(false) }

    // Fetch famílias e voluntários
    LaunchedEffect(Unit) {
        familias.value = familiaViewModel.getFamilias()
        voluntarios.value = voluntarioViewModel.getVoluntarios()
    }

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text("Registrar Visita", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = buttonColor)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Registrar Visita",
                    style = MaterialTheme.typography.headlineMedium,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Campo de seleção de família
                    Text(
                        text = "Família: ${if (familiaNome.isEmpty()) "Selecionar Família" else familiaNome}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isFamiliaDialogOpen = true },
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    // Dialog para seleção de família
                    if (isFamiliaDialogOpen) {
                        AlertDialog(
                            onDismissRequest = { isFamiliaDialogOpen = false },
                            title = { Text("Selecionar Família") },
                            text = {
                                Column {
                                    familias.value.forEach { familia ->
                                        TextButton(onClick = {
                                            familiaRef = familia.idFamilia
                                            familiaNome = familia.nome

                                            // Busca os detalhes da família para atualizar a nacionalidade
                                            familiaViewModel.getFamiliaById(familia.idFamilia) { selectedFamilia ->
                                                nacionalidade = selectedFamilia?.paisCodigo ?: "N/A"
                                            }
                                            isFamiliaDialogOpen = false
                                        }) {
                                            Text(familia.nome)
                                        }
                                    }
                                }
                            },
                            confirmButton = {
                                TextButton(onClick = { isFamiliaDialogOpen = false }) {
                                    Text("Cancelar")
                                }
                            }
                        )
                    }

                    // Seleção de voluntário
                    Text(
                        text = "Voluntário: ${if (voluntarioNome.isEmpty()) "Selecionar Voluntário" else voluntarioNome}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isVoluntarioDialogOpen = true },
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    if (isVoluntarioDialogOpen) {
                        AlertDialog(
                            onDismissRequest = { isVoluntarioDialogOpen = false },
                            title = { Text("Selecionar Voluntário") },
                            text = {
                                Column {
                                    voluntarios.value.forEach { voluntario ->
                                        TextButton(onClick = {
                                            voluntarioRef = voluntario.idVoluntario
                                            voluntarioNome = voluntario.nomeVoluntario
                                            isVoluntarioDialogOpen = false
                                        }) {
                                            Text(voluntario.nomeVoluntario)
                                        }
                                    }
                                }
                            },
                            confirmButton = {
                                TextButton(onClick = { isVoluntarioDialogOpen = false }) {
                                    Text("Cancelar")
                                }
                            }
                        )
                    }

                    // Outros campos
                    CustomTextField(value = nome, onValueChange = { nome = it }, label = "Nome")
                    CustomTextField(
                        value = numeroPessoas,
                        onValueChange = { numeroPessoas = it },
                        label = "Número de Pessoas"
                    )

                    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                    CustomTextField(
                        value = dateFormat.format(data_horaEnt),
                        onValueChange = {},
                        label = "Data e Hora de Entrada",
                        enabled = false // Campo desabilitado para edição
                    )

                    CustomTextField(
                        value = localLoja,
                        onValueChange = { localLoja = it },
                        label = "Local da Loja"
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CustomTextField(
                            value = contacto,
                            onValueChange = { contacto = it },
                            label = "Contacto",
                            modifier = Modifier.weight(1f)
                        )
                        CustomTextField(
                            value = nacionalidade,
                            onValueChange = { nacionalidade = it },
                            label = "Nacionalidade",
                            enabled = false,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    CustomTextField(
                        value = referencia,
                        onValueChange = { referencia = it },
                        label = "Referência"
                    )
                    CustomTextField(value = notas, onValueChange = { notas = it }, label = "Notas")

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = levantarBens, onCheckedChange = { levantarBens = it })
                        Text("Levantar Bens")
                    }

                    Button(
                        onClick = {
                            val visita = Visita(
                                data_horaEnt = data_horaEnt,
                                familia_ref = familiaRef,
                                numero_pessoas = numeroPessoas.toIntOrNull() ?: 0,
                                nome = nome,
                                voluntario_ref = voluntarioRef,
                                levantarBens = levantarBens,
                                localLoja = localLoja,
                                contacto = contacto,
                                referencia = referencia,
                                notas = notas,
                                nacionalidade = nacionalidade
                            )

                            visitaViewModel.registerVisita(visita) { success ->
                                dialogMessage =
                                    if (success) "Visita adicionada com sucesso!" else "Erro ao adicionar visita."
                                if (success) {
                                    // Limpar campos após registro bem-sucedido
                                    data_horaEnt = Date()
                                    familiaRef = ""
                                    familiaNome = ""
                                    numeroPessoas = ""
                                    nome = ""
                                    voluntarioRef = ""
                                    voluntarioNome = ""
                                    levantarBens = false
                                    localLoja = ""
                                    contacto = ""
                                    referencia = ""
                                    notas = ""
                                    nacionalidade = ""
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Registrar Visita", color = Color.White)
                    }
                }

                dialogMessage?.let { message ->
                    CustomDialog(message = message, onDismiss = { dialogMessage = null })
                }
            }
        }
    }
}

/*
@Composable
fun RegisterVisitaScreen(visitaViewModel: VisitaViewModel, familiaViewModel: FamiliaViewModel) {
    var data_horaEnt by remember { mutableStateOf(Date()) }
    var familiaRef by remember { mutableStateOf("") }
    var familiaNome by remember { mutableStateOf("") }
    var numeroPessoas by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var voluntarioRef by remember { mutableStateOf("") }
    var levantarBens by remember { mutableStateOf(false) }
    var localLoja by remember { mutableStateOf("") }
    var contacto by remember { mutableStateOf("") }
    var referencia by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }
    var nacionalidade by remember { mutableStateOf("") }

    var dialogMessage by remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()

    val familias = remember { mutableStateOf<List<Familia>>(emptyList()) }
    var isDialogOpen by remember { mutableStateOf(false) }


    // Fetch famílias
    LaunchedEffect(Unit) {
        val result = familiaViewModel.getFamilias()
        familias.value = result
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
                text = "Registrar Visita",
                style = MaterialTheme.typography.headlineMedium,
                color = textColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Seleção de Família (Nome)
            Text(
                text = "Família: ${if (familiaNome.isEmpty()) "Selecionar Família" else familiaNome}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { isDialogOpen = true },
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge
            )

            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))

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

                                    // Busca os detalhes da família para atualizar a nacionalidade
                                    familiaViewModel.getFamiliaById(familia.idFamilia) { selectedFamilia ->
                                        nacionalidade = selectedFamilia?.paisCodigo ?: "N/A"
                                    }
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
            CustomTextField(value = numeroPessoas, onValueChange = { numeroPessoas = it }, label = "Número de Pessoas")
            CustomTextField(value = voluntarioRef, onValueChange = { voluntarioRef = it }, label = "Voluntário (ID)")

            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            CustomTextField(
                value = dateFormat.format(data_horaEnt),
                onValueChange = {},
                label = "Data e Hora de Entrada",
                enabled = false // Campo desabilitado para edição
            )

            CustomTextField(value = localLoja, onValueChange = { localLoja = it }, label = "Local da Loja")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(value = contacto, onValueChange = { contacto = it }, label = "Contacto", modifier = Modifier.weight(1f))
                CustomTextField(
                    value = nacionalidade,
                    onValueChange = { nacionalidade = it },
                    label = "Nacionalidade",
                    enabled = false, // Agora visível para o usuário
                    modifier = Modifier.weight(1f)
                )
            }

            CustomTextField(value = referencia, onValueChange = { referencia = it }, label = "Referência")
            CustomTextField(value = notas, onValueChange = { notas = it }, label = "Notas")

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = levantarBens, onCheckedChange = { levantarBens = it })
                Text("Levantar Bens")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val visita = Visita(
                        data_horaEnt = data_horaEnt,
                        familia_ref = familiaRef,
                        numero_pessoas = numeroPessoas.toIntOrNull() ?: 0,
                        nome = nome,
                        voluntario_ref = voluntarioRef,
                        levantarBens = levantarBens,
                        localLoja = localLoja,
                        contacto = contacto,
                        referencia = referencia,
                        notas = notas,
                        nacionalidade = nacionalidade
                    )

                    visitaViewModel.registerVisita(visita) { success ->
                        dialogMessage = if (success) "Visita adicionada com sucesso!" else "Erro ao adicionar visita."
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar Visita", color = Color.White)
            }
        }

        dialogMessage?.let { message ->
            CustomDialog(message = message, onDismiss = { dialogMessage = null })
        }
    }
}
        */