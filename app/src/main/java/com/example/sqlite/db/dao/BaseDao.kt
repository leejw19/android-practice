package com.example.sqlite.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

open class BaseDao(private val db: SQLiteDatabase) {

    fun updateOrInsert(
        tableName: String,
        values: ContentValues,
        whereClause: String,
        whereArgs: Array<String>?): Boolean {

        return db.update(tableName, values, whereClause, whereArgs) > 0 ||
                db.insertWithOnConflict(tableName, null, values, SQLiteDatabase.CONFLICT_IGNORE) != -1L

    }
    fun insert(tableName: String, values: ContentValues): Long {
        return db.insert(tableName, null, values)
    }

    fun query(
        tableName: String?,
        columns: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?,
        limit: String?
    ): Cursor? {
        return db.query(
            tableName, columns, selection,
            selectionArgs, groupBy, having, orderBy, limit
        )
    }
}