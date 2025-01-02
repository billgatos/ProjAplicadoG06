package com.example.lojasocialfirebase.familia

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

open class FamiliaViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    suspend fun getFamilias(): List<Familia> {
        return try {
            val snapshot = db.collection("familias").get().await()
            snapshot.documents.map { document ->
                Familia(
                    idFamilia = document.id,
                    nome = document.getString("nome") ?: "",
                    paisCodigo = document.getString("paisCodigo") ?: ""
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }


    // Função para buscar uma família pelo ID
    fun getFamiliaById(familiaId: String, onResult: (Familia?) -> Unit) {
        db.collection("familias").document(familiaId).get()
            .addOnSuccessListener { document ->
                val familia = document.toObject(Familia::class.java)?.copy(idFamilia = familiaId)
                onResult(familia)
            }
            .addOnFailureListener {
                onResult(null)
            }
    }

    // Função para buscar detalhes da família no Firebase
    fun fetchFamiliaDetails(familiaId: String, familiaViewModel: FamiliaViewModel, onResult: (String?) -> Unit) {
        familiaViewModel.getFamiliaById(familiaId) { familia ->
            onResult(familia?.paisCodigo)
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
