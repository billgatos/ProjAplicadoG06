package com.example.lojasocialfirebase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lojasocialfirebase.navigation.AppNavHost
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o Firebase antes de qualquer operação
        FirebaseApp.initializeApp(this)

        // Verifica e inicializa coleções do Firebase
        initializeFirebaseCollections { success ->
            if (success) {
                setContent {
                    AppNavHost() // Configura o sistema de navegação após a inicialização
                }
            } else {
                Toast.makeText(this, "Falha na inicialização do banco de dados", Toast.LENGTH_LONG).show()
            }
        }
    }
}

fun initializeFirebaseCollections(onComplete: (Boolean) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()

    // Definindo a coleção de inicialização e o usuário padrão
    val defaultUserEmail = "adminnet@gmail.com"
    val defaultUserPassword = "123456"  // Defina uma senha segura para o usuário padrão

    // Função para criar uma coleção vazia
    fun createEmptyCollection(collectionName: String) {
        db.collection(collectionName).document("dummy").set(emptyMap<String, Any>()).addOnSuccessListener {
            // Remove o documento "dummy" após a criação para deixar a coleção realmente vazia
            db.collection(collectionName).document("dummy").delete()
        }
    }

    // Cria coleções vazias, se necessário
    listOf("tarefas", "pais", "familias", "pessoas", "voluntarios", "agenda", "visitas", "lideres_espirituais").forEach { collectionName ->
        createEmptyCollection(collectionName)
    }

    // Verifica se o usuário padrão existe no Firebase Authentication
    auth.fetchSignInMethodsForEmail(defaultUserEmail).addOnCompleteListener { task ->
        if (task.isSuccessful && task.result?.signInMethods.isNullOrEmpty()) {
            // Cria o usuário padrão no Authentication
            auth.createUserWithEmailAndPassword(defaultUserEmail, defaultUserPassword).addOnCompleteListener { createUserTask ->
                if (createUserTask.isSuccessful) {
                    val userId = createUserTask.result?.user?.uid ?: return@addOnCompleteListener

                    // Adiciona o usuário padrão na coleção "users" com tipo "adm"
                    db.collection("users").document(userId).set(mapOf("type" to "adm"))
                        .addOnSuccessListener {
                            onComplete(true)  // Indica sucesso ao completar a inicialização
                        }
                        .addOnFailureListener {
                            onComplete(false)
                        }
                } else {
                    onComplete(false)
                }
            }
        } else {
            // O usuário padrão já existe, então a inicialização está completa
            onComplete(true)
        }
    }.addOnFailureListener {
        onComplete(false)
    }
}
