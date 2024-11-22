package com.example.lojasocialfirebase.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    var userType: String? = null
        private set

    fun registerUser(email: String, password: String, onComplete: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
                db.collection("users").document(userId).set(mapOf("type" to "user"))
                onComplete(true)
            } else {
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
}
