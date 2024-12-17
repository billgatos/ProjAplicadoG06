package com.example.lojasocialfirebase.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    var userType: String? = null
        private set

    // Propriedade para obter o e-mail do usuário logado
    val currentUserEmail: String?
        get() = auth.currentUser?.email

    fun registerUser(email: String, password: String, onComplete: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
                val userdata = mapOf(
                    "type" to "user",
                    "email" to email)
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
