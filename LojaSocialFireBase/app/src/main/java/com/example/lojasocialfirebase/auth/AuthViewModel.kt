package com.example.lojasocialfirebase.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

open class AuthViewModel : ViewModel() {

    // Classe para representar o usuário
    data class User(
        val id: String,
        val email: String,
        val type: String
    )

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    var userType: String? = null
        private set

    // Propriedade para obter o e-mail do usuário logado
    val currentUserEmail: String?
        get() = auth.currentUser?.email

    // Fluxo para armazenar a lista de usuários
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    // Função para carregar os usuários do Firestore
    fun loadUsers() {
        viewModelScope.launch {
            try {
                val snapshot = db.collection("users").get().await()
                val userList = snapshot.documents.map { document ->
                    User(
                        id = document.id,
                        email = document.getString("email") ?: "",
                        type = document.getString("type") ?: "user"
                    )
                }
                _users.value = userList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    open fun registerUser(email: String, password: String, idioma : String, onComplete: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
                val userdata = mapOf(
                    "type" to "user",
                    "email" to email,
                    "idioma" to idioma)
                db.collection("users").document(userId)
                    .set(userdata)
                    .addOnSuccessListener{
                        onComplete(true)
                    }
            }else {
                onComplete(false)
            }
        }
    }

    fun loginUser(email: String, password: String, onComplete: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                fetchUserType { success ->
                    onComplete(success)
                }
            } else {
                onComplete(false)
            }
        }
    }

    private fun fetchUserType(onComplete: (Boolean) -> Unit) {
        val userId = auth.currentUser?.uid ?: return onComplete(false)
        db.collection("users").document(userId).get().addOnSuccessListener { document ->
            userType = document.getString("type")
            onComplete(true)
        }.addOnFailureListener {
            onComplete(false)
        }
    }

    fun updateUserType(userId: String, newType: String, onComplete: (Boolean) -> Unit) {
        db.collection("users").document(userId).update("type", newType)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    fun updateUserEmail(userId: String, newEmail: String, onComplete: (Boolean) -> Unit) {
        db.collection("users").document(userId).update("email", newEmail)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    fun fetchUserByEmail(email: String, onComplete: (String, Boolean) -> Unit) {
        db.collection("users").document(email).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    onComplete(email, true)  // Retorna o email como ID
                } else {
                    onComplete("", false)   // Indica falha ao encontrar
                }
            }
            .addOnFailureListener {
                onComplete("", false)       // Em caso de erro
            }
    }

    fun logoutUser() {
        auth.signOut()
    }


}
