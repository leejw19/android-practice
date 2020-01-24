package com.example.sqlite.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

abstract class BaseDao<T>(private val db: SQLiteDatabase) {


    abstract fun cursorToEntity(cursor: Cursor): T

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
}