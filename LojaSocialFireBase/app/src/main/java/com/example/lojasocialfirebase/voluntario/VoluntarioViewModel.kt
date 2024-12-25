package com.example.lojasocialfirebase.voluntario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lojasocialfirebase.pessoa.Pessoa
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.launch

class VoluntarioViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val voluntariosCollection = db.collection("voluntarios")

    // Função para registrar um novo voluntário com ID gerado automaticamente
    fun registerVoluntario(voluntario: Voluntario, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val newDocRef = voluntariosCollection.document()
                val voluntarioWithId = voluntario.copy(idVoluntario = newDocRef.id)
                newDocRef.set(voluntarioWithId).await()
                onResult(true)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            }
        }
    }

    // Função para buscar todas as pessoas
    suspend fun getVoluntarios(): List<Voluntario> {
        return try {
            val snapshot = db.collection("voluntarios").get().await()
            snapshot.documents.map { document ->
                Voluntario(
                    idVoluntario = document.id,
                    pessoaId = document.getString("pessoaId") ?: "",
                    nomeVoluntario = document.getString("nomeVoluntario") ?: ""
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun getVoluntarioById(voluntarioId: String, onResult: (Voluntario?) -> Unit) {
        db.collection("voluntarios").document(voluntarioId).get()
            .addOnSuccessListener { document ->
                val voluntario = document.toObject(Voluntario::class.java)?.copy(idVoluntario = voluntarioId)
                onResult(voluntario)
            }
            .addOnFailureListener {
                onResult(null)
            }
    }

}
