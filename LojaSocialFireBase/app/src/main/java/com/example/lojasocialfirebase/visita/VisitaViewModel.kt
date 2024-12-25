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

    fun getVisitas(onComplete: (List<Visita>) -> Unit) {
        db.collection("visitas").get().addOnSuccessListener { snapshot ->
            val visitas = snapshot.documents.mapNotNull { document ->
                document.toObject(Visita::class.java)
            }
            onComplete(visitas)
        }.addOnFailureListener {
            onComplete(emptyList())
        }
    }

    fun getVisitasGroupedByNationality(onComplete: (Map<String, Int>) -> Unit) {
        db.collection("visitas").get().addOnSuccessListener { snapshot ->
            val visitas = snapshot.documents.mapNotNull { it.toObject(Visita::class.java) }
            val grouped = visitas.groupingBy { it.nacionalidade }.eachCount()
            onComplete(grouped)
        }.addOnFailureListener {
            onComplete(emptyMap())
        }
    }
}
