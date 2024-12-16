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

@Composable
fun RegisterVisitaScreen(visitaViewModel: VisitaViewModel) {
    var data_horaEnt by remember { mutableStateOf(Date()) } // Inicializa com a data e hora atuais
    var data_horaSai by remember { mutableStateOf("") }
    var familiaRef by remember { mutableStateOf("") }
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

    val scrollState = rememberScrollState() //verificar para usar nas proximas

    Scaffold(
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(scrollState), // Usa o scrollState aqui
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registrar Visita",
                style = MaterialTheme.typography.headlineMedium,
                color = textColor
            )

            Spacer(modifier = Modifier.height(24.dp))
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

            CustomTextField(value = nome, onValueChange = { nome = it }, label = "Nome")
            CustomTextField(value = numeroPessoas, onValueChange = { numeroPessoas = it }, label = "Número de Pessoas")
            CustomTextField(value = familiaRef, onValueChange = { familiaRef = it }, label = "Família (ID)")
            CustomTextField(value = voluntarioRef, onValueChange = { voluntarioRef = it }, label = "Voluntário (ID)")

            // Campos de Data e Hora de Entrada e Saída lado a lado
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    value = dateFormat.format(data_horaEnt),
                    onValueChange = {}, // Mantém como não editável
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
                CustomTextField(
                    value = contacto,
                    onValueChange = { contacto = it },
                    label = "Contato",
                    modifier = Modifier.weight(1f) // Ocupa metade da largura disponível
                )

                CustomTextField(
                    value = nacionalidade,
                    onValueChange = { nacionalidade = it },
                    label = "Nacionalidade",
                    modifier = Modifier.weight(1f) // Ocupa metade da largura disponível
                )
            }

            CustomTextField(value = referencia, onValueChange = { referencia = it }, label = "Referência")
            CustomTextField(value = notas, onValueChange = { notas = it }, label = "Notas")

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = levantarBens, onCheckedChange = { levantarBens = it })
                Text("Levantar Bens")
            }

            Button(
                onClick = {
                    val visita = Visita(
                        data_horaEnt = data_horaEnt, // Usa a data e hora atual no momento do registro
                        data_horaSai = Date(),       // Define a data de saída como a hora atual (ajuste se necessário)
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
                        if (success) {
                            dialogMessage = "Visita adicionada com sucesso!"
                            nome = ""
                            numeroPessoas = ""
                            familiaRef = ""
                            voluntarioRef = ""
                            data_horaEnt = Date() // Reseta para a nova data e hora atual
                            data_horaSai = ""
                            localLoja = ""
                            contacto = ""
                            referencia = ""
                            notas = ""
                            nacionalidade = ""
                            levantarBens = false
                        } else {
                            dialogMessage = "Erro ao adicionar visita."
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

@Preview(showBackground = true)
@Composable
fun RegisterVisitaScreenPreview() {
    RegisterVisitaScreen(visitaViewModel = VisitaViewModel())
}
