package com.example.database.sqlite.method

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.database.sqlite.SQLiteTransaction
import com.example.database.sqlite.dao.MyProductDao
import com.example.database.sqlite.dto.MyProductDto
import com.example.database.sqlite.schema.MyProductSchema

class BookShelfDataMethod {

    companion object {

        fun registerMyProducts(context: Context, myProductList: ArrayList<MyProductDto>): Boolean? {
            return SQLiteTransaction.tx(context, object : SQLiteTransaction.InTransaction<Boolean> {
                override fun execute(db: SQLiteDatabase) =
                    MyProductDao(db).updateOrInsert(myProductList)
            })
        }

        fun registerMyProductsWithStatement(
            context: Context,
            myProductList: ArrayList<MyProductDto>
        ): Boolean? {
            return SQLiteTransaction.tx(context, object : SQLiteTransaction.InTransaction<Boolean> {
                override fun execute(db: SQLiteDatabase) =
                    MyProductDao(db).updateOrInsert2(myProductList)
            })
        }

        fun registerMyProductsWithRawSql(
            context: Context,
            myProductList: ArrayList<MyProductDto>
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

        fun readAllMyProduct(context: Context): ArrayList<MyProductDto>? {
            return SQLiteTransaction.tx(
                context,
                object : SQLiteTransaction.InTransaction<ArrayList<MyProductDto>> {
                    override fun execute(db: SQLiteDatabase) = MyProductDao(db).selectAll()
                })
        }

        fun readMyProduct(context: Context, productId: Long): MyProductDto? {
            return SQLiteTransaction.tx(
                context,
                object : SQLiteTransaction.InTransaction<MyProductDto?> {
                    override fun execute(db: SQLiteDatabase) = MyProductDao(db).select(productId)
                })
        }

        fun updateMyProduct(context: Context, myProductDto: MyProductDto): Boolean? {

            return SQLiteTransaction.tx(context, object : SQLiteTransaction.InTransaction<Boolean> {
                override fun execute(db: SQLiteDatabase) = MyProductDao(db).update(myProductDto)
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