package com.example.sqlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class SqliteTransaction {

    companion object {

        fun <T> tx(context: Context, callback: Callback<T>): T? {

            val db = DataBaseHelper.getInstance(context).writableDatabase
            var result: T? = null

            try {
                db.beginTransactionNonExclusive()
                result = callback.run(db)
                db.setTransactionSuccessful()
            } catch (e: Exception) {

                Log.e("DB", e.message)
            } finally {
                db.endTransaction()
            }

            return result
        }

    }

    interface Callback <T> {
        fun run(db: SQLiteDatabase): T
    }
}
