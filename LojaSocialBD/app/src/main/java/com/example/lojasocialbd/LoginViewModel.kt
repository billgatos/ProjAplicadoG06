package com.example.lojasocialbd

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    // Função para validar login e retornar o tipo de utilizador
    fun login(username: String, password: String): String? {
        // Exemplo de validação simples
        return if (username == "admin" && password == "admin123") {
            "ADM"
        } else if (username == "user" && password == "user123") {
            "USER"
        } else {
            null
        }
    }
}
