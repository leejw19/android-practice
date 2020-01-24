package com.example.sqlite.db.method

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.sqlite.db.DbExecutor
import com.example.sqlite.db.dao.MyProductEpisodeDao
import com.example.sqlite.db.dto.MyProduct
import com.example.sqlite.db.dto.MyProductEpisode

class MyProductEpisodeMethod {

    companion object {

        fun register(context: Context, myProductList: ArrayList<MyProductEpisode>): Boolean? {

            return DbExecutor.tx(context, object : DbExecutor.Callback<Boolean> {
                override fun run(db: SQLiteDatabase): Boolean {
                    return MyProductEpisodeDao(db).updateOrInsert(myProductList)
                }
            })
        }


        fun read(context: Context, productId: Long, productEpisodeId: Long): MyProductEpisode? {
            return DbExecutor.tx(context, object : DbExecutor.Callback<MyProductEpisode?> {
                override fun run(db: SQLiteDatabase) =
                    MyProductEpisodeDao(db).select(productId, productEpisodeId)
            })
        }

        fun update(context: Context, myProductEpisode: MyProductEpisode): Boolean? {

            return DbExecutor.tx(context, object : DbExecutor.Callback<Boolean> {
                override fun run(db: SQLiteDatabase) =
                    MyProductEpisodeDao(db).update(myProductEpisode)
            })
        }
    }
}