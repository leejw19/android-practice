package com.example.sqlite.db.method

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.sqlite.db.SqliteTransaction
import com.example.sqlite.db.dao.MyProductDao
import com.example.sqlite.db.pojo.MyProduct

class MyProductMethod {

    companion object {

        fun register(context: Context, myProductList: ArrayList<MyProduct>): Boolean? {

            return SqliteTransaction.tx(context, object : SqliteTransaction.Callback <Boolean> {
                override fun run(db: SQLiteDatabase): Boolean {
                    return MyProductDao(db).updateOrInsert(myProductList)
                }
            })


        }
    }
}