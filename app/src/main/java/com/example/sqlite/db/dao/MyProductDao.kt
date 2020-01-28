package com.example.sqlite.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.sqlite.db.dto.MyProduct
import com.example.sqlite.db.schema.MyProductSchema
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_BOOKMARKED_AT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_FLAG_FETCH_SORT_VALUE_LIST_UPDATED_ASC
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_IS_TICKET_CONSUMED_MY_PRODUCT_FLAG
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_JSON_TEXT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_LAST_PRODUCT_EPISODE_ID
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_LAST_PRODUCT_EPISODE_TITLE
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_LAST_PURCHASED_AT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_LOCAL_HISTORY_STATUS
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_LOCAL_PUSH_STATUS
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_NOW_FREE_NEXT_META_INFO
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_NOW_FREE_NEXT_READ_AT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_NOW_FREE_OPEN_STATUS_FINISH_DAYS
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_PRODUCT_EPISODE_LIST_SORT_INFO
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_PRODUCT_ID
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_SORT_VALUE_LIST_UPDATED_ASC
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_STATUS
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_TICKET_CONSUMED_MY_PRODUCT_STATUS
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_TICKET_TOTAL_COUNT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_UPDATED_AT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_USER_CONSUMED_TICKET_PRODUCT_EPISODE_INFO_LAST_SYNC_TIME
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_WAIT_FREE_EPISODE_COUNT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_WAIT_FREE_EPISODE_READ_COUNT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_WAIT_FREE_TICKET_FINISH_CHARGED_AT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_WAIT_FREE_TICKET_START_CHARGED_AT

class MyProductDao(val db: SQLiteDatabase) : BaseDao<MyProduct>(db) {

    companion object {
        const val INSERT_SQL =
            "INSERT OR REPLACE INTO ${MyProductSchema.TABLE_NAME} (product_id, json_text, status) VALUES(?,?,?)"
        const val INSERT_SQL_PARAMS =
            ",(?,?,?)"

        const val COLUMN_COUNT = 3
    }

    fun updateOrInsert(myProductList: ArrayList<MyProduct>): Boolean {

        var successCount = 0

        myProductList.forEach {
            val values = ContentValues().apply {

                put(COLUMN_PRODUCT_ID, it.productId)
                put(COLUMN_JSON_TEXT, it.jsonText)
                put(COLUMN_STATUS, it.status)
            }

            if (updateOrInsert(
                    MyProductSchema.TABLE_NAME,
                    values,
                    "$COLUMN_PRODUCT_ID = ?",
                    arrayOf(it.productId.toString())
                )
            ) successCount++

        }

        return successCount == myProductList.size

    }

    fun updateOrInsert2(myProductList: ArrayList<MyProduct>): Boolean {

        if (myProductList.isEmpty()) {
            return false
        }

        val loopCount = getLoopCount(COLUMN_COUNT, myProductList.size)
        val maxRowCount = getRowPerLoop(COLUMN_COUNT)

        var currentListIndex = 0
        try {
            for (loopIndex in 0 until loopCount) {

                var endIndex = currentListIndex + maxRowCount
                if (endIndex >= myProductList.size) endIndex = myProductList.size

                var sql = ""
                for (i in currentListIndex until endIndex) {
                    sql += if (i == currentListIndex) INSERT_SQL else INSERT_SQL_PARAMS
                }

                val statement = db.compileStatement(sql)

                var index = 0

                for (i in currentListIndex until endIndex) {
                    statement.bindLong(index + 1, myProductList[i].productId?.toLong() ?: 0)
                    statement.bindString(index + 2, myProductList[i].jsonText ?: "")
                    statement.bindLong(index + 3, myProductList[i].status?.toLong() ?: 0)
                    index += COLUMN_COUNT
                }
                statement.execute()
                statement.close()
                currentListIndex = endIndex

            }

        } catch (e: Exception) {
            // Error
            return false
        }

        return true

    }

    fun updateOrInsert3(sql: String): Boolean {

        db.execSQL(sql)

        return true

    }

    fun selectAll(): ArrayList<MyProduct> {
        val myProductList = arrayListOf<MyProduct>()
        var count = 0
        query(MyProductSchema.TABLE_NAME, arrayOf(COLUMN_JSON_TEXT), null, null)?.let {
            try {
                it.moveToFirst()
                while (!it.isAfterLast) {
                    myProductList.add(cursorToEntity(it))
                    count++
                    it.moveToNext()
                }

            } catch (e: Exception) {
                Log.e("DB_EXECUTION", e.message ?: "error!!!")
            }

        }

        return myProductList
    }

    fun select(productId: Long): MyProduct? {

        return query(
            MyProductSchema.TABLE_NAME,
            "$COLUMN_PRODUCT_ID = ?",
            arrayOf(productId.toString())
        )?.let {
            if (it.moveToFirst()) cursorToEntity(it)
            else null
        }
    }

    fun selectByCondition(productId: Long): MyProduct? {

        return query(
            MyProductSchema.TABLE_NAME,
            "$COLUMN_PRODUCT_ID = ?",
            arrayOf(productId.toString())
        )?.let {
            if (it.moveToFirst()) cursorToEntity(it)
            else null
        }
    }

    fun update(myProduct: MyProduct): Boolean {
        val values = ContentValues().apply {

            put(COLUMN_PRODUCT_ID, myProduct.productId)
            put(COLUMN_LAST_PRODUCT_EPISODE_ID, myProduct.lastProductEpisodeId)
            put(COLUMN_LAST_PRODUCT_EPISODE_TITLE, myProduct.lastProductEpisodeTitle)
            put(COLUMN_TICKET_TOTAL_COUNT, myProduct.ticketTotalCount)
            put(COLUMN_WAIT_FREE_TICKET_START_CHARGED_AT, myProduct.ticketTotalCount)
            put(COLUMN_WAIT_FREE_TICKET_FINISH_CHARGED_AT, myProduct.waitFreeTicketFinishChargedAt)
            put(COLUMN_WAIT_FREE_EPISODE_COUNT, myProduct.waitFreeEpisodeCount)
            put(COLUMN_WAIT_FREE_EPISODE_READ_COUNT, myProduct.waitFreeEpisodeReadCount)
            put(COLUMN_NOW_FREE_NEXT_META_INFO, myProduct.nowFreeNextMetaInfo)
            put(COLUMN_NOW_FREE_NEXT_READ_AT, myProduct.nowFreeNextReadAt)
            put(COLUMN_NOW_FREE_OPEN_STATUS_FINISH_DAYS, myProduct.nowFreeOpenStatusFinishDays)
            put(COLUMN_IS_TICKET_CONSUMED_MY_PRODUCT_FLAG, myProduct.isTicketConsumedMyProductFlag)
            put(COLUMN_LAST_PURCHASED_AT, myProduct.lastPurchasedAt)
            put(COLUMN_BOOKMARKED_AT, myProduct.bookmarkedAt)
            put(
                COLUMN_USER_CONSUMED_TICKET_PRODUCT_EPISODE_INFO_LAST_SYNC_TIME,
                myProduct.userConsumedTicketProductEpisodeInfoLastSyncTime
            )
            put(COLUMN_PRODUCT_EPISODE_LIST_SORT_INFO, myProduct.productEpisodeListSortInfo)
            put(COLUMN_LOCAL_PUSH_STATUS, myProduct.localPushStatus)
            put(COLUMN_LOCAL_HISTORY_STATUS, myProduct.localHistoryStatus)
            put(COLUMN_TICKET_CONSUMED_MY_PRODUCT_STATUS, myProduct.ticketConsumedMyProductStatus)
            put(COLUMN_JSON_TEXT, myProduct.jsonText)
            put(COLUMN_STATUS, myProduct.status)
            put(COLUMN_SORT_VALUE_LIST_UPDATED_ASC, myProduct.sortValueListUpdatedAsc)
            put(
                COLUMN_FLAG_FETCH_SORT_VALUE_LIST_UPDATED_ASC,
                myProduct.flagFetchSortValueListUpdatedAsc
            )
            put(COLUMN_UPDATED_AT, myProduct.updatedAt)
        }

        return update(
            MyProductSchema.TABLE_NAME,
            values,
            "$COLUMN_PRODUCT_ID = ?",
            arrayOf(myProduct.productId.toString())
        )

    }

    fun deleteAll() {
        db.delete(MyProductSchema.TABLE_NAME, null, null)
    }

    override fun cursorToEntity(cursor: Cursor): MyProduct {
        return MyProduct(
            getColumnIndex(cursor, COLUMN_PRODUCT_ID)?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_LAST_PRODUCT_EPISODE_ID)?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_LAST_PRODUCT_EPISODE_TITLE)?.let { cursor.getString(it) },
            getColumnIndex(cursor, COLUMN_TICKET_TOTAL_COUNT)?.let { cursor.getInt(it) },
            getColumnIndex(
                cursor,
                COLUMN_WAIT_FREE_TICKET_START_CHARGED_AT
            )?.let { cursor.getString(it) },
            getColumnIndex(
                cursor,
                COLUMN_WAIT_FREE_TICKET_FINISH_CHARGED_AT
            )?.let { cursor.getString(it) },
            getColumnIndex(cursor, COLUMN_WAIT_FREE_EPISODE_COUNT)?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_WAIT_FREE_EPISODE_READ_COUNT)?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_NOW_FREE_NEXT_META_INFO)?.let { cursor.getString(it) },
            getColumnIndex(cursor, COLUMN_NOW_FREE_NEXT_READ_AT)?.let { cursor.getString(it) },
            getColumnIndex(
                cursor,
                COLUMN_NOW_FREE_OPEN_STATUS_FINISH_DAYS
            )?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_IS_TICKET_CONSUMED_MY_PRODUCT_FLAG)?.let {
                cursor.getInt(
                    it
                )
            },
            getColumnIndex(cursor, COLUMN_LAST_PURCHASED_AT)?.let { cursor.getString(it) },
            getColumnIndex(cursor, COLUMN_BOOKMARKED_AT)?.let { cursor.getString(it) },
            getColumnIndex(
                cursor,
                COLUMN_USER_CONSUMED_TICKET_PRODUCT_EPISODE_INFO_LAST_SYNC_TIME
            )?.let { cursor.getString(it) },
            getColumnIndex(
                cursor,
                COLUMN_PRODUCT_EPISODE_LIST_SORT_INFO
            )?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_LOCAL_PUSH_STATUS)?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_LOCAL_HISTORY_STATUS)?.let { cursor.getInt(it) },
            getColumnIndex(
                cursor,
                COLUMN_TICKET_CONSUMED_MY_PRODUCT_STATUS
            )?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_JSON_TEXT)?.let { cursor.getString(it) },
            getColumnIndex(cursor, COLUMN_STATUS)?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_SORT_VALUE_LIST_UPDATED_ASC)?.let { cursor.getInt(it) },
            getColumnIndex(
                cursor,
                COLUMN_FLAG_FETCH_SORT_VALUE_LIST_UPDATED_ASC
            )?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_UPDATED_AT)?.let { cursor.getString(it) }
        )
    }

}