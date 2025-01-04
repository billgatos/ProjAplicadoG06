package com.example.lojasocialbd.viewmodels


// versão sem ROOM para o LoginViewModel

//class LoginViewModel : ViewModel() {
//
//    // Função para validar login e retornar o tipo de utilizador
//    fun login(username: String, password: String): String? {
//        // Exemplo de validação simples
//        return if (username == "admin" && password == "admin123") {
//            "ADM"
//        } else if (username == "user" && password == "user123") {
//            "USER"
//        } else {
//            null
//        }
//    }
//}


// versão ROOM para o LoginViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lojasocialbd.database.AppDatabase
import com.example.lojasocialbd.models.Utilizador
import kotlinx.coroutines.Dispatchers
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.lojasocialbd.dao.UtilizadorDao

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val utilizadorDao = db.utilizadorDao()

    // Função para validar login e retornar o tipo de utilizador
    fun login(username: String, password: String, onLoginResult: (String?) -> Unit) {
        viewModelScope.launch {
            val utilizador: Utilizador? = withContext(Dispatchers.IO) {
                utilizadorDao.getUtilizadorByUsername(username)
            }

            // Validar se o utilizador existe e a senha está correta
            if (utilizador != null && utilizador.password == password) {
                onLoginResult(utilizador.tipo) // Retorna o tipo de utilizador
            } else {
                onLoginResult(null) // Retorna null se a validação falhar
            }
        }
    }
}