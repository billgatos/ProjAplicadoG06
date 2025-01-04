package com.example.lojasocialbd.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.lojasocialbd.dao.UtilizadorDao
import com.example.lojasocialbd.models.Utilizador

@Database(entities = [Utilizador::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun utilizadorDao(): UtilizadorDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

