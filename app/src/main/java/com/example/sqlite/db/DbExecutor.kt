package com.example.sqlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class DbExecutor {

    companion object {

        fun <T> tx(context: Context, callback: Callback<T>): T? {

            val db = DataBaseHelper.getInstance(context).db
            var result: T? = null

            try {
                db.beginTransactionNonExclusive()
                result = callback.run(db)
                db.setTransactionSuccessful()
            } catch (e: Exception) {

                Log.e("DB", e.message ?: "error")
            } finally {
                db.endTransaction()
            }

            return result
        }

    }

    interface Callback<T> {
        fun run(db: SQLiteDatabase): T
    }
}
