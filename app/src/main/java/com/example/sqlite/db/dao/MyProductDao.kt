package com.example.sqlite.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.sqlite.db.dto.MyProduct
import com.example.sqlite.db.schema.MyProductSchema.MY_PRODUCT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_ID
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_PRODUCT_ID
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_LAST_PRODUCT_EPISODE_ID
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_LAST_PRODUCT_EPISODE_TITLE
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_TICKET_TOTAL_COUNT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_WAIT_FREE_TICKET_START_CHARGED_AT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_WAIT_FREE_TICKET_FINISH_CHARGED_AT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_WAIT_FREE_EPISODE_COUNT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_WAIT_FREE_EPISODE_READ_COUNT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_NOW_FREE_NEXT_META_INFO
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_NOW_FREE_NEXT_READ_AT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_NOW_FREE_OPEN_STATUS_FINISH_DAYS
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_IS_TICKET_CONSUMED_MY_PRODUCT_FLAG
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_LAST_PURCHASED_AT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_BOOKMARKED_AT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_USER_CONSUMED_TICKET_PRODUCT_EPISODE_INFO_LAST_SYNC_TIME
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_PRODUCT_EPISODE_LIST_SORT_INFO
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_LOCAL_PUSH_STATUS
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_LOCAL_HISTORY_STATUS
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_TICKET_CONSUMED_MY_PRODUCT_STATUS
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_JSON_TEXT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_STATUS
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_SORT_VALUE_LIST_UPDATED_ASC
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_FLAG_FETCH_SORT_VALUE_LIST_UPDATED_ASC
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_UPDATED_AT

class MyProductDao(val db: SQLiteDatabase) : BaseDao<MyProduct>(db) {

    fun updateOrInsert(myProductList: ArrayList<MyProduct>): Boolean {

        var successCount = 0

        myProductList.forEach {
            val values = ContentValues().apply {

                put(COLUMN_PRODUCT_ID, it.productId)
                put(COLUMN_JSON_TEXT, it.jsonText)
                put(COLUMN_STATUS, it.status)
            }

            if (updateOrInsert(
                    MY_PRODUCT,
                    values,
                    "$COLUMN_PRODUCT_ID = ?",
                    arrayOf(it.productId.toString())
                )
            ) successCount++

        }

        return successCount == myProductList.size

    }

    fun select(productId: Long): MyProduct? {

        return query(MY_PRODUCT, "$COLUMN_PRODUCT_ID = ?", arrayOf(productId.toString()))?.let {
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
            MY_PRODUCT,
            values,
            "$COLUMN_PRODUCT_ID = ?",
            arrayOf(myProduct.productId.toString())
        )

    }

    override fun cursorToEntity(cursor: Cursor): MyProduct {
        return MyProduct(
            cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
            cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)),
            cursor.getInt(cursor.getColumnIndex(COLUMN_LAST_PRODUCT_EPISODE_ID)),
            cursor.getString(cursor.getColumnIndex(COLUMN_LAST_PRODUCT_EPISODE_TITLE)),
            cursor.getInt(cursor.getColumnIndex(COLUMN_TICKET_TOTAL_COUNT)),
            cursor.getString(cursor.getColumnIndex(COLUMN_WAIT_FREE_TICKET_START_CHARGED_AT)),
            cursor.getString(cursor.getColumnIndex(COLUMN_WAIT_FREE_TICKET_FINISH_CHARGED_AT)),
            cursor.getInt(cursor.getColumnIndex(COLUMN_WAIT_FREE_EPISODE_COUNT)),
            cursor.getInt(cursor.getColumnIndex(COLUMN_WAIT_FREE_EPISODE_READ_COUNT)),
            cursor.getString(cursor.getColumnIndex(COLUMN_NOW_FREE_NEXT_META_INFO)),
            cursor.getString(cursor.getColumnIndex(COLUMN_NOW_FREE_NEXT_READ_AT)),
            cursor.getInt(cursor.getColumnIndex(COLUMN_NOW_FREE_OPEN_STATUS_FINISH_DAYS)),
            cursor.getInt(cursor.getColumnIndex(COLUMN_IS_TICKET_CONSUMED_MY_PRODUCT_FLAG)),
            cursor.getString(cursor.getColumnIndex(COLUMN_LAST_PURCHASED_AT)),
            cursor.getString(cursor.getColumnIndex(COLUMN_BOOKMARKED_AT)),
            cursor.getString(
                cursor.getColumnIndex(
                    COLUMN_USER_CONSUMED_TICKET_PRODUCT_EPISODE_INFO_LAST_SYNC_TIME
                )
            ),
            cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_EPISODE_LIST_SORT_INFO)),
            cursor.getInt(cursor.getColumnIndex(COLUMN_LOCAL_PUSH_STATUS)),
            cursor.getInt(cursor.getColumnIndex(COLUMN_LOCAL_HISTORY_STATUS)),
            cursor.getInt(cursor.getColumnIndex(COLUMN_TICKET_CONSUMED_MY_PRODUCT_STATUS)),
            cursor.getString(cursor.getColumnIndex(COLUMN_JSON_TEXT)),
            cursor.getInt(cursor.getColumnIndex(COLUMN_STATUS)),
            cursor.getInt(cursor.getColumnIndex(COLUMN_SORT_VALUE_LIST_UPDATED_ASC)),
            cursor.getInt(cursor.getColumnIndex(COLUMN_FLAG_FETCH_SORT_VALUE_LIST_UPDATED_ASC)),
            cursor.getString(cursor.getColumnIndex(COLUMN_UPDATED_AT))
        )
    }

}