package com.example.sqlite.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.sqlite.db.dao.MyProductDao.Companion.COLUMN_COUNT
import kotlin.math.ceil
import kotlin.math.floor


abstract class BaseDao<T>(private val db: SQLiteDatabase) {

    companion object {
        const val SQLITE_MAX_COLUMN_COUNT = 999
    }

    abstract fun cursorToEntity(cursor: Cursor): T

    fun getLoopCount(columnCount: Int, dataCount: Int) = ceil((columnCount * dataCount).toDouble() / SQLITE_MAX_COLUMN_COUNT).toInt()

    fun getRowPerLoop(columnCount: Int) = floor(SQLITE_MAX_COLUMN_COUNT / COLUMN_COUNT.toDouble()).toInt()

    fun updateOrInsert(
        tableName: String,
        values: ContentValues,
        whereClause: String,
        whereArgs: Array<String>?
    ): Boolean = update(tableName, values, whereClause, whereArgs) || insert(tableName, values)

    fun query(
        tableName: String,
        selectionClause: String,
        selectionArgs: Array<String>?
    ): Cursor? = db.query(tableName, null, selectionClause, selectionArgs, null, null, null)

    fun insert(
        tableName: String,
        values: ContentValues
    ): Boolean =
        db.insertWithOnConflict(tableName, null, values, SQLiteDatabase.CONFLICT_IGNORE) != -1L

    fun update(
        tableName: String,
        values: ContentValues,
        whereClause: String,
        whereArgs: Array<String>?
    ): Boolean = db.update(tableName, values, whereClause, whereArgs) > 0

    interface Binder<T> {
        fun <T> bind(): T
    }
}