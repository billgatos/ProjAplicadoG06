import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lojasocialfirebase.pessoa.Pessoa
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.launch

class PessoaViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val pessoasCollection = db.collection("pessoas")

    // Função para registrar uma nova pessoa com ID gerado automaticamente
    fun registerPessoa(pessoa: Pessoa, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val newDocRef = pessoasCollection.document()
                val pessoaWithId = pessoa.copy(idPessoa = newDocRef.id)
                newDocRef.set(pessoaWithId).await()
                onResult(true)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            }
        }
    }

    // Função para buscar todas as pessoas
    suspend fun getPessoas(): List<Pessoa> {
        return try {
            val snapshot = db.collection("pessoas").get().await()
            snapshot.documents.map { document ->
                Pessoa(
                    idPessoa = document.id,
                    nome = document.getString("nome") ?: "",
                    idade = document.getLong("idade")?.toInt() ?: 0,
                    contato = document.getString("contato") ?: "",
                    dataNascimento = document.getString("dataNascimento") ?: "",
                    paisCodigo = document.getString("paisCodigo") ?: "",
                    idFamilia = document.getString("idFamilia") ?: ""
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
