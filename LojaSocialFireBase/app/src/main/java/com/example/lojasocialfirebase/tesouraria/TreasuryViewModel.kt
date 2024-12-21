package com.example.lojasocialfirebase.tesouraria

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

class TreasuryViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val transactionsCollection = db.collection("transactions")

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions

    private val _balance = MutableStateFlow(0.0)
    val balance: StateFlow<Double> = _balance

    init {
        observeTransactions()
    }

    private fun observeTransactions() {
        transactionsCollection.addSnapshotListener { snapshot, e ->
            if (e != null) {
                e.printStackTrace()
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val loadedTransactions = snapshot.documents.map { document ->
                    Transaction(
                        id = document.id,
                        amount = document.getDouble("amount") ?: 0.0,
                        description = document.getString("description") ?: "",
                        date = document.getString("date") ?: "",
                        type = document.getString("type") ?: ""
                    )
                }
                _transactions.value = loadedTransactions
                calculateBalance(loadedTransactions)
            }
        }
    }

    private fun calculateBalance(transactions: List<Transaction>) {
        val balance = transactions.sumOf {
            if (it.type == "Entrada") it.amount else -it.amount
        }
        _balance.value = balance
    }

    fun addTransaction(amount: Double, description: String, type: String) {
        viewModelScope.launch {
            try {
                val id = transactionsCollection.document().id
                val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                val transaction = Transaction(
                    id = id,
                    amount = amount,
                    description = description,
                    date = currentDate,
                    type = type
                )
                transactionsCollection.document(id).set(transaction).await()
                // As mudanças serão refletidas automaticamente pelo SnapshotListener
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
