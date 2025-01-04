package com.example.lojasocialfirebase.auth

import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.lojasocialfirebase.R
import com.example.lojasocialfirebase.ui.theme.CustomDialog
import com.example.lojasocialfirebase.ui.theme.CustomTextField
import com.example.lojasocialfirebase.ui.theme.CustomTextPassword
import com.example.lojasocialfirebase.ui.theme.backgroundColor
import com.example.lojasocialfirebase.ui.theme.buttonColor
import com.example.lojasocialfirebase.ui.theme.textColor
import com.google.firebase.auth.FirebaseAuth

val auth: FirebaseAuth = FirebaseAuth.getInstance()

@Composable
fun LoginScreen(authViewModel: AuthViewModel, onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Adiciona a logo acima do texto "Login"
            Image(
                painter = painterResource(id = R.drawable.lojasocialogo),
                contentDescription = "Logo Loja Social",
                modifier = Modifier
                    .size(250.dp)  // Ajusta o tamanho da logo
                    .padding(bottom = 16.dp)  // Espaçamento abaixo da logo
            )

            Text(
                text = "Bem vindo de volta.",
                style = MaterialTheme.typography.headlineMedium,
                color = textColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomTextField(value = email, onValueChange = { email = it }, label = "Email")
            CustomTextPassword(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                isPassword = true  // Define que é um campo de senha,
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    authViewModel.loginUser(email, password) { success ->
                        if (success && authViewModel.userType == "adm" || success && authViewModel.userType == "user") {
                            onLoginSuccess()
                        } else {
                            dialogMessage = "Acesso negado. Apenas administradores têm permissão."
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login", color = Color.White)
            }
        }

        // Exibir a Dialog personalizada se houver uma mensagem
        dialogMessage?.let { message ->
            CustomDialog(message = message, onDismiss = { dialogMessage = null })
        }
    }
}
