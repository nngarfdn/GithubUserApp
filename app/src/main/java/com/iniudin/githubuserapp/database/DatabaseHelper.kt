package com.iniudin.githubuserapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.AVATAR
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.COMPANY
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.FOLLOWERS
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.FOLLOWERSURL
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.ID
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.LOCATION
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.LOGIN
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.NAME
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.PUBLICREPOS
import com.iniudin.githubuserapp.database.DatabaseContract.UserColumns.Companion.TABLE_NAME

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {

        private const val DATABASE_NAME = "favorite.db"

        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_TABLE_FAV = "CREATE TABLE $TABLE_NAME" +
                "($ID INTEGER PRIMARY KEY," +
                "$AVATAR TEXT ," +
                "$COMPANY TEXT," +
                "$FOLLOWERS INTEGER," +
                "$FOLLOWERSURL INTEGER," +
                "$LOCATION TEXT," +
                "$LOGIN TEXT," +
                "$NAME TEXT," +
                "$PUBLICREPOS INTEGER" +
                ")"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_FAV)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}