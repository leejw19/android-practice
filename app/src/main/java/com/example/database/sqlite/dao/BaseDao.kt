package com.example.database.sqlite.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import kotlin.math.ceil
import kotlin.math.floor


abstract class BaseDao<T>(private val db: SQLiteDatabase) {

    companion object {
        private const val SQLITE_MAX_COLUMN_COUNT = 999
    }

    protected abstract fun cursorToEntity(cursor: Cursor): T

    protected fun getLoopCount(columnCount: Int, dataCount: Int) =
        ceil((columnCount * dataCount).toDouble() / SQLITE_MAX_COLUMN_COUNT).toInt()

    protected fun getRowPerLoop(columnCount: Int) =
        floor(SQLITE_MAX_COLUMN_COUNT / columnCount.toDouble()).toInt()

    protected fun updateOrInsert(
        tableName: String,
        values: ContentValues,
        whereClause: String,
        whereArgs: Array<String>?
    ): Boolean = insert(tableName, values) || update(tableName, values, whereClause, whereArgs)

    protected fun query(
        tableName: String,
        selectionClause: String?,
        selectionArgs: Array<String>?
    ): Cursor? = query(tableName, null, selectionClause, selectionArgs)

    protected fun query(
        tableName: String,
        columns: Array<String>?,
        selectionClause: String?,
        selectionArgs: Array<String>?
    ): Cursor? = db.query(tableName, columns, selectionClause, selectionArgs, null, null, null)

    protected fun insert(
        tableName: String,
        values: ContentValues
    ): Boolean =
        db.insertWithOnConflict(tableName, null, values, SQLiteDatabase.CONFLICT_IGNORE) != -1L

    protected fun update(
        tableName: String,
        values: ContentValues,
        whereClause: String,
        whereArgs: Array<String>?
    ): Boolean = db.update(tableName, values, whereClause, whereArgs) > 0

    protected fun getColumnIndex(cursor: Cursor, columnName: String): Int? {
        val index = cursor.getColumnIndex(columnName)
        if (index < 0) return null
        return index

    }
}