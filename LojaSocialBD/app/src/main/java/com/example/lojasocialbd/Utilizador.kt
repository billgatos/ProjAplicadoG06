    package com.example.lojasocialbd

    class Utilizador(
        val id: Int =0, var username: String ="", var password: String = "",
        var nome: String ="", var id_pessoa: Int=0, var tipo: String ="" )
    {
        override fun toString(): String {
            return "Utilizador(id=$id, username='$username', password='$password', nome='$nome', id_pessoa=$id_pessoa, tipo='$tipo')"
        }
    }

