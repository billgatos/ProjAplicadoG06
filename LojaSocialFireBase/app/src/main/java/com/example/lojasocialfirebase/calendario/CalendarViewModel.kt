package com.example.lojasocialfirebase.calendario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.launch

class CalendarViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val requestsCollection = db.collection("calendarRequests")
    private var listener: ListenerRegistration? = null

    fun addCalendarRequest(voluntarioId: String, voluntarioNome: String, data: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val newDocRef = requestsCollection.document()
                val request = CalendarRequest(
                    id = newDocRef.id,
                    voluntarioId = voluntarioId,
                    voluntarioNome = voluntarioNome,
                    data = data,
                    status = "Pendente"
                )
                newDocRef.set(request).await()
                onResult(true)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            }
        }
    }

    fun getCalendarRequests(onResult: (List<CalendarRequest>) -> Unit) {
        // Adiciona o snapshot listener para sincronização em tempo real
        listener = db.collection("calendarRequests")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    e.printStackTrace()
                    onResult(emptyList())
                    return@addSnapshotListener
                }

                if (snapshot != null && !snapshot.isEmpty) {
                    val result = snapshot.documents.map { document ->
                        CalendarRequest(
                            id = document.id,
                            voluntarioNome = document.getString("voluntarioNome") ?: "",
                            data = document.getString("data") ?: "",
                            status = document.getString("status") ?: "Pendente"
                        )
                    }
                    onResult(result)
                } else {
                    onResult(emptyList())
                }
            }
    }

    // Função para atualizar o status do pedido (aprovar ou rejeitar)
    fun updateRequestStatus(id: String, newStatus: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                requestsCollection.document(id).update("status", newStatus).await()
                onResult(true)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            }
        }
    }

    // Remove o listener quando não for mais necessário
    fun removeListener() {
        listener?.remove()
    }
}
