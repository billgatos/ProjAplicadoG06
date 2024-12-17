package com.example.lojasocialfirebase.voluntario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
}
