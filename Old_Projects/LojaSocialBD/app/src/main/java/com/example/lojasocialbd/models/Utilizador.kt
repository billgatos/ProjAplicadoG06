    package com.example.lojasocialbd.models

    /* Forma direta da classe sem ROOM
    class Utilizador(
        val id: Int =0, var username: String ="", var password: String = "",
        var nome: String ="", var id_pessoa: Int=0, var tipo: String ="" )
    {
        override fun toString(): String {
            return "Utilizador(id=$id, username='$username', password='$password', nome='$nome', id_pessoa=$id_pessoa, tipo='$tipo')"
        }
    }
    */
    // Usando a mesma classe com Room

    import androidx.room.Entity
    import androidx.room.PrimaryKey

    @Entity(tableName = "utilizadores") // Specify the table name
    data class Utilizador(
        @PrimaryKey(autoGenerate = true) // Mark id as the primary key and enable auto-increment
        val id: Int = 0,
        var username: String = "",
        var password: String = "",
        var nome: String = "",
        var id_pessoa: Int = 0,
        var tipo: String = ""
    ) {
        override fun toString(): String {
            return "Utilizador(id=$id, username='$username', password='$password', nome='$nome', id_pessoa=$id_pessoa, tipo='$tipo')"
        }
    }