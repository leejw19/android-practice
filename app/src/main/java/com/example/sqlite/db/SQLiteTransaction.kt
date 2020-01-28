package com.example.sqlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class SQLiteTransaction {

    companion object {

        fun <T> tx(context: Context, inTransaction: InTransaction<T>): T? {

            val db = DataBaseHelper.getInstance(context).getDatabase()
            var result: T? = null

            try {
                db.beginTransactionNonExclusive()
                result = inTransaction.execute(db)
                db.setTransactionSuccessful()
            } catch (e: Exception) {

                Log.e("DB", e.message ?: "error")
            } finally {
                db.endTransaction()
            }

            return result
        }

    }

    interface InTransaction<T> {
        fun execute(db: SQLiteDatabase): T
    }
}
