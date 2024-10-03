    package com.example.lojasocialbd

    class Utilizador(
        val id: Int =0, val username: String ="", val password: String = "",
        val nome: String ="", val id_pessoa: Int=0, val tipo: String ="" )
    {
        override fun toString(): String {
            return "Utilizador(id=$id, username='$username', password='$password', nome='$nome', id_pessoa=$id_pessoa, tipo='$tipo')"
        }
    }

