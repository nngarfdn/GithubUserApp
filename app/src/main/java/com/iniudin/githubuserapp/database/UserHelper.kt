package com.iniudin.githubuserapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.ID
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.TABLE_NAME
import java.sql.SQLException

class UserHelper (context: Context) {

    private var dataBaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private var INSTANCE: UserHelper? = null
        fun getInstance(context: Context): UserHelper = INSTANCE?: synchronized(this){
            INSTANCE?: UserHelper(context)
        }
    }

    init {
        dataBaseHelper = DatabaseHelper(context)
    }

    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE, null, null, null, null, null, "$ID DESC"
        )
    }

    fun queryById(id: String): Cursor {
        return database.query(
            DATABASE_TABLE, null, "$ID = ?", arrayOf(id), null, null, null, null
        )
    }

    fun update(id: String, values: ContentValues?): Int {
        open()
        return database.update(DATABASE_TABLE, values, "$ID = ?", arrayOf(id))
    }
    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }
    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$ID = '$id'", null)
    }
}