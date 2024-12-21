import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lojasocialfirebase.ui.theme.CustomDialog
import com.example.lojasocialfirebase.ui.theme.CustomTextField
import com.example.lojasocialfirebase.ui.theme.backgroundColor
import com.example.lojasocialfirebase.ui.theme.buttonColor
import com.example.lojasocialfirebase.ui.theme.textColor
import com.example.lojasocialfirebase.visita.Visita
import com.example.lojasocialfirebase.visita.VisitaViewModel
import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.lojasocialfirebase.familia.Familia
import com.example.lojasocialfirebase.familia.FamiliaViewModel

@Composable
fun RegisterVisitaScreen(visitaViewModel: VisitaViewModel, familiaViewModel: FamiliaViewModel) {
    var data_horaEnt by remember { mutableStateOf(Date()) }
    var data_horaSai by remember { mutableStateOf("") }
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

    // Estado para armazenar a lista de famílias
    val familias = remember { mutableStateOf<List<Familia>>(emptyList()) }

    // Estado para controlar a exibição do Dialog
    var isDialogOpen by remember { mutableStateOf(false) }

    // Chamada ao Firebase para buscar as famílias
    LaunchedEffect(Unit) {
        val result = familiaViewModel.getFamilias()
        familias.value = result

        // Adicionando log para verificar se os dados foram carregados
        println("Familias carregadas: $result")
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
                text = "Registar Visita",
                style = MaterialTheme.typography.headlineMedium,
                color = textColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

            // Campo separado para Família (ID)
            Text(
                text = "Família (ID): $familiaRef",
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    value = dateFormat.format(data_horaEnt),
                    onValueChange = {},
                    label = "Data e Hora de Entrada",
                    modifier = Modifier.weight(1f)
                )

                CustomTextField(
                    value = data_horaSai,
                    onValueChange = { data_horaSai = it },
                    label = "Data e Hora de Saída",
                    modifier = Modifier.weight(1f)
                )
            }

            CustomTextField(value = localLoja, onValueChange = { localLoja = it }, label = "Local da Loja")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(value = contacto, onValueChange = { contacto = it }, label = "Contato", modifier = Modifier.weight(1f))
                CustomTextField(value = nacionalidade, onValueChange = { nacionalidade = it }, label = "Nacionalidade", modifier = Modifier.weight(1f))
            }

            CustomTextField(value = referencia, onValueChange = { referencia = it }, label = "Referência")
            CustomTextField(value = notas, onValueChange = { notas = it }, label = "Notas")

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = levantarBens, onCheckedChange = { levantarBens = it })
                Text("Levantar Bens")
            }

            Button(
                onClick = {
                    val visita = Visita(
                        data_horaEnt = data_horaEnt,
                        data_horaSai = Date(),
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
