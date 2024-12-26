package com.example.lojasocialfirebase.visita

import androidx.lifecycle.ViewModel
import com.example.lojasocialfirebase.familia.Familia
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
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

    fun getVisitasByDateRange(startDate: Date?, endDate: Date?, onComplete: (Map<String, Int>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val visitasRef = db.collection("visitas")

        var query = visitasRef as Query

        if (startDate != null) {
            query = query.whereGreaterThanOrEqualTo("data_horaEnt", startDate)
        }
        if (endDate != null) {
            query = query.whereLessThanOrEqualTo("data_horaEnt", endDate)
        }

        query.get()
            .addOnSuccessListener { documents ->
                val groupedData = mutableMapOf<String, Int>()
                for (document in documents) {
                    val nacionalidade = document.getString("nacionalidade") ?: "Desconhecido"
                    groupedData[nacionalidade] = groupedData.getOrDefault(nacionalidade, 0) + 1
                }
                onComplete(groupedData)
            }
            .addOnFailureListener {
                onComplete(emptyMap()) // Retorna um mapa vazio em caso de falha
            }
    }


}
