package com.example.lojasocialfirebase.visita

import androidx.lifecycle.ViewModel
import com.example.lojasocialfirebase.familia.Familia
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.Date

data class Visita(
    val data_horaEnt: Date,
    val data_horaSai: Date,
    val familia_ref: String,
    val numero_pessoas: Int,
    val nome: String,
    val voluntario_ref: String,
    val levantarBens: Boolean,
    val localLoja: String,
    val contacto: String,
    val referencia: String,
    val notas: String,
    val nacionalidade: String
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
