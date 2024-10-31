package com.example.lojasocialbd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import com.example.lojasocialbd.models.Utilizador

@Dao
interface UtilizadorDao {
    @Insert
    suspend fun insert(utilizador: Utilizador)

    @Update
    suspend fun update(utilizador: Utilizador)

    @Delete
    suspend fun delete(utilizador: Utilizador)

    @Query("SELECT * FROM utilizadores")
    suspend fun getAllUtilizadores(): List<Utilizador>

    @Query("SELECT * FROM utilizadores WHERE id = :id")
    suspend fun getUtilizadorById(id: Int): Utilizador?

    @Query("SELECT * FROM utilizadores WHERE username = :username")
    suspend fun getUtilizadorByUsername(username: String): Utilizador?

    //@Query("SELECT * FROM utilizador WHERE username = :username LIMIT 1")
    //suspend fun getUtilizadorByUsername(username: String): Utilizador?

    @Query("SELECT COUNT(*) FROM utilizadores")
    suspend fun countUtilizadores(): Int
}