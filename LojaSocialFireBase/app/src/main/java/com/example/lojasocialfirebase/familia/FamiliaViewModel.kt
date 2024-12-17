package com.example.lojasocialfirebase.familia

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FamiliaViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    suspend fun getFamilias(): List<Familia> {
        return try {
            val snapshot = db.collection("familias").get().await()
            snapshot.documents.map { document ->
                Familia(
                    idFamilia = document.id,
                    nome = document.getString("nome") ?: ""
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun registerFamilia(familia: Familia, onComplete: (Boolean) -> Unit) {
        db.collection("familias").add(familia)
            .addOnSuccessListener { documentReference ->
                // Atualizar o documento com o ID gerado
                documentReference.update("idFamilia", documentReference.id)
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }

}
