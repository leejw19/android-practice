package com.example.database.sqlite.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.database.sqlite.dto.MyProductDto
import com.example.database.sqlite.schema.MyProductSchema
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_BOOKMARKED_AT
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_FLAG_FETCH_SORT_VALUE_LIST_UPDATED_ASC
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_IS_TICKET_CONSUMED_MY_PRODUCT_FLAG
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_JSON_TEXT
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_LAST_PRODUCT_EPISODE_ID
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_LAST_PRODUCT_EPISODE_TITLE
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_LAST_PURCHASED_AT
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_LOCAL_HISTORY_STATUS
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_LOCAL_PUSH_STATUS
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_NOW_FREE_NEXT_META_INFO
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_NOW_FREE_NEXT_READ_AT
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_NOW_FREE_OPEN_STATUS_FINISH_DAYS
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_PRODUCT_EPISODE_LIST_SORT_INFO
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_PRODUCT_ID
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_SORT_VALUE_LIST_UPDATED_ASC
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_STATUS
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_TICKET_CONSUMED_MY_PRODUCT_STATUS
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_TICKET_TOTAL_COUNT
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_UPDATED_AT
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_USER_CONSUMED_TICKET_PRODUCT_EPISODE_INFO_LAST_SYNC_TIME
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_WAIT_FREE_EPISODE_COUNT
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_WAIT_FREE_EPISODE_READ_COUNT
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_WAIT_FREE_TICKET_FINISH_CHARGED_AT
import com.example.database.sqlite.schema.MyProductSchema.COLUMN_WAIT_FREE_TICKET_START_CHARGED_AT

class MyProductDao(val db: SQLiteDatabase) : BaseDao<MyProductDto>(db) {

    companion object {
        const val INSERT_SQL =
            "INSERT OR REPLACE INTO ${MyProductSchema.TABLE_NAME} (product_id, json_text, status) VALUES(?,?,?)"
        const val INSERT_SQL_PARAMS =
            ",(?,?,?)"

        const val COLUMN_COUNT = 3
    }

    fun updateOrInsert(myProductList: ArrayList<MyProductDto>): Boolean {

        var successCount = 0

        try {
            myProductList.forEach {
                val values = ContentValues().apply {

                    put(COLUMN_PRODUCT_ID, it.productId)
                    put(COLUMN_JSON_TEXT, it.jsonText)
                    put(COLUMN_STATUS, it.status)
                }

                val result = updateOrInsert(
                    MyProductSchema.TABLE_NAME,
                    values,
                    "$COLUMN_PRODUCT_ID = ?",
                    arrayOf(it.productId.toString())
                )
                if (result) successCount++

            }
        } catch (e: Exception) {
            Log.e("DB_TEST", "", e)
        }

        Log.d("DB_TEST", "success count = $successCount")
        return successCount == myProductList.size

    }

    fun updateOrInsert2(myProductList: ArrayList<MyProductDto>): Boolean {

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

    fun selectAll(): ArrayList<MyProductDto> {
        val myProductList = arrayListOf<MyProductDto>()
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

    fun select(productId: Long): MyProductDto? {

        return query(
            MyProductSchema.TABLE_NAME,
            "$COLUMN_PRODUCT_ID = ?",
            arrayOf(productId.toString())
        )?.let {
            if (it.moveToFirst()) cursorToEntity(it)
            else null
        }
    }

    fun selectByCondition(productId: Long): MyProductDto? {

        return query(
            MyProductSchema.TABLE_NAME,
            "$COLUMN_PRODUCT_ID = ?",
            arrayOf(productId.toString())
        )?.let {
            if (it.moveToFirst()) cursorToEntity(it)
            else null
        }
    }

    fun update(myProductDto: MyProductDto): Boolean {
        val values = ContentValues().apply {

            put(COLUMN_PRODUCT_ID, myProductDto.productId)
            put(COLUMN_LAST_PRODUCT_EPISODE_ID, myProductDto.lastProductEpisodeId)
            put(COLUMN_LAST_PRODUCT_EPISODE_TITLE, myProductDto.lastProductEpisodeTitle)
            put(COLUMN_TICKET_TOTAL_COUNT, myProductDto.ticketTotalCount)
            put(COLUMN_WAIT_FREE_TICKET_START_CHARGED_AT, myProductDto.ticketTotalCount)
            put(COLUMN_WAIT_FREE_TICKET_FINISH_CHARGED_AT, myProductDto.waitFreeTicketFinishChargedAt)
            put(COLUMN_WAIT_FREE_EPISODE_COUNT, myProductDto.waitFreeEpisodeCount)
            put(COLUMN_WAIT_FREE_EPISODE_READ_COUNT, myProductDto.waitFreeEpisodeReadCount)
            put(COLUMN_NOW_FREE_NEXT_META_INFO, myProductDto.nowFreeNextMetaInfo)
            put(COLUMN_NOW_FREE_NEXT_READ_AT, myProductDto.nowFreeNextReadAt)
            put(COLUMN_NOW_FREE_OPEN_STATUS_FINISH_DAYS, myProductDto.nowFreeOpenStatusFinishDays)
            put(COLUMN_IS_TICKET_CONSUMED_MY_PRODUCT_FLAG, myProductDto.isTicketConsumedMyProductFlag)
            put(COLUMN_LAST_PURCHASED_AT, myProductDto.lastPurchasedAt)
            put(COLUMN_BOOKMARKED_AT, myProductDto.bookmarkedAt)
            put(
                COLUMN_USER_CONSUMED_TICKET_PRODUCT_EPISODE_INFO_LAST_SYNC_TIME,
                myProductDto.userConsumedTicketProductEpisodeInfoLastSyncTime
            )
            put(COLUMN_PRODUCT_EPISODE_LIST_SORT_INFO, myProductDto.productEpisodeListSortInfo)
            put(COLUMN_LOCAL_PUSH_STATUS, myProductDto.localPushStatus)
            put(COLUMN_LOCAL_HISTORY_STATUS, myProductDto.localHistoryStatus)
            put(COLUMN_TICKET_CONSUMED_MY_PRODUCT_STATUS, myProductDto.ticketConsumedMyProductStatus)
            put(COLUMN_JSON_TEXT, myProductDto.jsonText)
            put(COLUMN_STATUS, myProductDto.status)
            put(COLUMN_SORT_VALUE_LIST_UPDATED_ASC, myProductDto.sortValueListUpdatedAsc)
            put(
                COLUMN_FLAG_FETCH_SORT_VALUE_LIST_UPDATED_ASC,
                myProductDto.flagFetchSortValueListUpdatedAsc
            )
            put(COLUMN_UPDATED_AT, myProductDto.updatedAt)
        }

        return update(
            MyProductSchema.TABLE_NAME,
            values,
            "$COLUMN_PRODUCT_ID = ?",
            arrayOf(myProductDto.productId.toString())
        )

    }

    fun deleteAll() {
        db.delete(MyProductSchema.TABLE_NAME, null, null)
    }

    override fun cursorToEntity(cursor: Cursor): MyProductDto {
        return MyProductDto(
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