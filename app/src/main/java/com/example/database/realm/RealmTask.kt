package com.example.database.realm

import android.content.Context
import android.util.Log
import io.realm.Realm

class RealmTask {

    companion object {

        fun <T> tx(context: Context, transaction: Transaction<T>): T? {

            val db = RealmDB.getInstance()
            var result: T? = null

            try {
                db.beginTransaction()
                result = transaction.execute(db)
                db.commitTransaction()
            } catch (e: Exception) {

                Log.e("DB", e.message ?: "error")

                db.cancelTransaction()
            } finally {
                db.close()
            }
            return result
        }
    }

    interface Transaction<T> {
        fun execute(db: Realm): T
    }
}