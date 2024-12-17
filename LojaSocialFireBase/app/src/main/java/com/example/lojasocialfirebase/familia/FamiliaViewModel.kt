package com.example.lojasocialfirebase.familia

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class FamiliaViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    fun registerFamilia(familia: Familia, onComplete: (Boolean) -> Unit) {
        db.collection("familias").add(familia).addOnSuccessListener {
            onComplete(true)
        }.addOnFailureListener {
            onComplete(false)
        }
    }
}
