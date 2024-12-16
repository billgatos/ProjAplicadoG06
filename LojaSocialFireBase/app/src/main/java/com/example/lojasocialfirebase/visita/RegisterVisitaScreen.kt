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

@Composable
fun RegisterVisitaScreen(visitaViewModel: VisitaViewModel) {
    var nome by remember { mutableStateOf("") }
    var numeroPessoas by remember { mutableStateOf("") }
    var familiaRef by remember { mutableStateOf("") }
    var voluntarioRef by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf<String?>(null) }

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
                text = "Registrar Visita",
                style = MaterialTheme.typography.headlineMedium,
                color = textColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomTextField(value = nome, onValueChange = { nome = it }, label = "Nome")
            CustomTextField(value = numeroPessoas, onValueChange = { numeroPessoas = it }, label = "Número de Pessoas")
            CustomTextField(value = familiaRef, onValueChange = { familiaRef = it }, label = "Família (ID)")
            CustomTextField(value = voluntarioRef, onValueChange = { voluntarioRef = it }, label = "Voluntário (ID)")

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val visita = Visita(
                        data_hora = Date(),
                        numero_pessoas = numeroPessoas.toIntOrNull() ?: 0,
                        nome = nome,
                        familia_ref = familiaRef,
                        voluntario_ref = voluntarioRef
                    )

                    visitaViewModel.registerVisita(visita) { success ->
                        if (success) {
                            dialogMessage = "Visita adicionada com sucesso!"
                            nome = ""
                            numeroPessoas = ""
                            familiaRef = ""
                            voluntarioRef = ""
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

        // Exibir a Dialog se registro for bem sucedido
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