package com.example.sqlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlite.db.schema.MyProductSchema

class DataBaseHelper private constructor(context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "text_db.sql"

        private var instance: DataBaseHelper? = null

        fun getInstance(context: Context): DataBaseHelper =
            instance ?: synchronized(this) {
                DataBaseHelper(
                    context
                ).also {
                    instance = it
                }
            }
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(MyProductSchema.TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}