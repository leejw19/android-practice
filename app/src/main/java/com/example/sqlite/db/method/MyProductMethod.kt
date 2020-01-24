package com.example.sqlite.db.method

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.sqlite.db.DbExecutor
import com.example.sqlite.db.dao.MyProductDao
import com.example.sqlite.db.dto.MyProduct

class MyProductMethod {

    companion object {

        fun register(context: Context, myProductList: ArrayList<MyProduct>): Boolean? {
            return DbExecutor.tx(context, object : DbExecutor.Callback<Boolean> {
                override fun run(db: SQLiteDatabase) =
                    MyProductDao(db).updateOrInsert(myProductList)
            })
        }

        fun read(context: Context, productId: Long): MyProduct? {
            return DbExecutor.tx(context, object : DbExecutor.Callback<MyProduct?> {
                override fun run(db: SQLiteDatabase) = MyProductDao(db).select(productId)
            })
        }

        fun update(context: Context, myProduct: MyProduct): Boolean? {

            return DbExecutor.tx(context, object : DbExecutor.Callback<Boolean> {
                override fun run(db: SQLiteDatabase) = MyProductDao(db).update(myProduct)
            })
        }
    }
}