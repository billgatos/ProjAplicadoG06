package com.example.lojasocialfirebase.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val darkGreen = Color(0xFF006400)
val darkSeaGreen = Color(0xFF8FBC8F)

/*
// Cores personalizadas
val lightGreen = Color(0xFFA8E6CF)
val buttonColor = Color(0xFF56C596)
val textColor = Color(0xFF2E7D32)
val backgroundColor = Color(0xFFF1F8E9)
val militarGreen = Color(0xFF4B5320)
val silverGreen = Color(0xFFB0C4B1)
val silverBrute = Color(0xFF4A4A4A)
*/

// Cores personalizadas
val lightBlue = Color(0xFFADD8E6) // Azul claro
val buttonColor = Color(0xFF1E90FF) // Azul intenso para botões
val textColor = Color(0xFF00008B) // Azul escuro para textos
val backgroundColor = Color(0xFFE6F7FF) // Azul bem claro para fundo
val deepBlue = Color(0xFF000080) // Azul profundo
val silverBlue = Color(0xFFB0C4DE) // Azul prateado
val steelBlue = Color(0xFF4682B4) // Azul aço
val borderColor = Color(0xFF5A9BD5)
val bruteBlueSilver = Color(0xFF34495E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier, // Parâmetro modifier com valor padrão
    enabled: Boolean = true         // Novo parâmetro enabled com valor padrão true
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(
            label,
            color = borderColor,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold // Letras mais intensas
            )
        )  },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = lightBlue,
            focusedIndicatorColor = borderColor,
            unfocusedIndicatorColor = borderColor,
            cursorColor = borderColor,
            focusedTextColor = textColor,
            unfocusedTextColor = textColor
        ),
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold,     // Aplica negrito ao texto digitado
            color = textColor                 // Aplica a cor ao texto digitado
        ),
        shape = RoundedCornerShape(8.dp),
        enabled = enabled, // Define o comportamento de habilitação/desabilitação
        modifier = modifier // Aplica o modifier passado como parâmetro
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextPassword(value: String, onValueChange: (String) -> Unit, label: String, isPassword: Boolean = false) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(
            label,
            color = borderColor,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold // Letras mais intensas
            )
        )  },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = lightBlue,
            focusedIndicatorColor = borderColor,
            unfocusedIndicatorColor = borderColor,
            cursorColor = borderColor
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun CustomDialog(message: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .background(color = lightBlue, shape = RoundedCornerShape(12.dp))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyLarge,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("OK", color = Color.White)
                }
            }
        }
    }
}