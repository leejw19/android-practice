package com.example.sqlite.db.method

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.sqlite.db.SQLiteTransaction
import com.example.sqlite.db.dao.MyProductDao
import com.example.sqlite.db.dto.MyProduct
import com.example.sqlite.db.schema.MyProductSchema

class BookShelfDataMethod {

    companion object {

        fun registerMyProducts(context: Context, myProductList: ArrayList<MyProduct>): Boolean? {
            return SQLiteTransaction.tx(context, object : SQLiteTransaction.InTransaction<Boolean> {
                override fun execute(db: SQLiteDatabase) =
                    MyProductDao(db).updateOrInsert(myProductList)
            })
        }

        fun registerMyProductsWithStatement(
            context: Context,
            myProductList: ArrayList<MyProduct>
        ): Boolean? {
            return SQLiteTransaction.tx(context, object : SQLiteTransaction.InTransaction<Boolean> {
                override fun execute(db: SQLiteDatabase) =
                    MyProductDao(db).updateOrInsert2(myProductList)
            })
        }

        fun registerMyProductsWithRawSql(
            context: Context,
            myProductList: ArrayList<MyProduct>
        ): Boolean? {

            return SQLiteTransaction.tx(context, object : SQLiteTransaction.InTransaction<Boolean> {
                override fun execute(db: SQLiteDatabase): Boolean {
                    val dao = MyProductDao(db)

                    myProductList.forEach {
                        dao.updateOrInsert3("INSERT OR REPLACE INTO ${MyProductSchema.TABLE_NAME} (product_id, json_text, status) VALUES(${it.productId},\"${it.jsonText}\",${it.status});")

                    }
                    return true
                }

            })
        }

        fun readAllMyProduct(context: Context): ArrayList<MyProduct>? {
            return SQLiteTransaction.tx(
                context,
                object : SQLiteTransaction.InTransaction<ArrayList<MyProduct>> {
                    override fun execute(db: SQLiteDatabase) = MyProductDao(db).selectAll()
                })
        }

        fun readMyProduct(context: Context, productId: Long): MyProduct? {
            return SQLiteTransaction.tx(
                context,
                object : SQLiteTransaction.InTransaction<MyProduct?> {
                    override fun execute(db: SQLiteDatabase) = MyProductDao(db).select(productId)
                })
        }

        fun updateMyProduct(context: Context, myProduct: MyProduct): Boolean? {

            return SQLiteTransaction.tx(context, object : SQLiteTransaction.InTransaction<Boolean> {
                override fun execute(db: SQLiteDatabase) = MyProductDao(db).update(myProduct)
            })
        }

        fun deleteAllMyProduct(context: Context): Nothing? {
            return SQLiteTransaction.tx(
                context,
                object : SQLiteTransaction.InTransaction<Nothing?> {
                    override fun execute(db: SQLiteDatabase): Nothing? {
                        MyProductDao(db).deleteAll()
                        return null
                    }
                })
        }
    }
}