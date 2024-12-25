package com.example.lojasocialfirebase.visita

import androidx.lifecycle.ViewModel
import com.example.lojasocialfirebase.familia.Familia
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.Date

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
