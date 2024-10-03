package com.example.lojasocialbd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.viewModelFactory

class DBHelper(context : Context): SQLiteOpenHelper(context,"database.db", null, 1 )
{
//    colocar aqui por baixo as instruções SQL
//    que façam sentido no arranque da base de dados.
//    MAs que não sejam sempre executadas !!!!!

    var sql = arrayOf(
        "CREATE Table utilizador(id integer primary key autoincrement, username text, password text, " +
                "nome text,id_pessoa integer, tipo text)",
        "INSERT INTO utilizador (username, password, nome, id_pesssoa, tipo)" +
                "values " +
                "('admin','1234','Roberto','0','ADM')",
        "INSERT INTO utilizador (username, password, nome, id_pesssoa, tipo)" +
                "values " +
                "('user','user','Funcionário 1','0','USER')"
        )


    override fun onCreate(db: SQLiteDatabase) {
        sql.forEach {
            db.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("Drop table utilizador")
        onCreate(db)

    }
}
