package com.example.lojasocialfirebase.visita


import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

data class Visita(
    val data_hora: Date,
    val numero_pessoas: Int,
    val nome: String,
    val familia_ref: String,
    val voluntario_ref: String
)

class VisitaViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    fun registerVisita(visita: Visita, onComplete: (Boolean) -> Unit) {
        db.collection("visitas").add(visita).addOnSuccessListener {
            onComplete(true)
        }.addOnFailureListener {
            onComplete(false)
        }
    }
}
