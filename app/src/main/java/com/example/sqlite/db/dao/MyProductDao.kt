package com.example.sqlite.db.dao

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.sqlite.db.pojo.MyProduct
import com.example.sqlite.db.schema.MyProductSchema.MY_PRODUCT_TABLE
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_PRODUCT_ID
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_JSON_TEXT
import com.example.sqlite.db.schema.MyProductSchema.COLUMN_STATUS

//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.MY_PRODUCT_TABLE
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_ID
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_PRODUCT_ID
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_LAST_PRODUCT_EPISODE_ID
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_LAST_PRODUCT_EPISODE_TITLE
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_TICKET_TOTAL_COUNT
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_WAIT_FREE_TICKET_START_CHARGED_AT
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_WAIT_FREE_TICKET_FINISH_CHARGED_AT
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_WAIT_FREE_EPISODE_COUNT
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_WAIT_FREE_EPISODE_READ_COUNT
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_NOW_FREE_NEXT_META_INFO
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_NOW_FREE_NEXT_READ_AT
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_NOW_FREE_OPEN_STATUS_FINISH_DAYS
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_IS_TICKET_CONSUMED_MY_PRODUCT_FLAG
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_LAST_PURCHASED_AT
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_BOOKMARKED_AT
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_USER_CONSUMED_TICKET_PRODUCT_EPISODE_INFO_LAST_SYNC_TIME
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_PRODUCT_EPISODE_LIST_SORT_INFO
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_LOCAL_PUSH_STATUS
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_LOCAL_HISTORY_STATUS
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_TICKET_CONSUMED_MY_PRODUCT_STATUS
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_JSON_TEXT
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_STATUS
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_SORT_VALUE_LIST_UPDATED_ASC
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_FLAG_FETCH_SORT_VALUE_LIST_UPDATED_ASC
//import com.example.test_db_pstm.db.schema.MyProductSchema.Companion.COLUMN_UPDATED_AT

class MyProductDao(db: SQLiteDatabase): BaseDao(db) {

    fun updateOrInsert(myProductList: ArrayList<MyProduct>): Boolean {

        var successCount = 0

        myProductList.forEach {
            val values = ContentValues().apply {

//                put(COLUMN_ID, it.id)
                put(COLUMN_PRODUCT_ID, it.productId)
//                put(COLUMN_LAST_PRODUCT_EPISODE_ID, it.lastProductEpisodeId)
//                put(COLUMN_LAST_PRODUCT_EPISODE_TITLE, it.lastProductEpisodeTitle)
//                put(COLUMN_TICKET_TOTAL_COUNT, it.ticketTotalCount)
//                put(COLUMN_WAIT_FREE_TICKET_START_CHARGED_AT, it.waitFreeTicketStartChargedAt)
//                put(COLUMN_WAIT_FREE_TICKET_FINISH_CHARGED_AT, it.waitFreeTicketFinishChargedAt)
//                put(COLUMN_WAIT_FREE_EPISODE_COUNT, it.waitFreeEpisodeCount)
//                put(COLUMN_WAIT_FREE_EPISODE_READ_COUNT, it.waitFreeEpisodeReadCount)
//                put(COLUMN_NOW_FREE_NEXT_META_INFO, it.nowFreeNextMetaInfo)
//                put(COLUMN_NOW_FREE_NEXT_READ_AT, it.nowFreeNextReadAt)
//                put(COLUMN_NOW_FREE_OPEN_STATUS_FINISH_DAYS, it.nowFreeOpenStatusFinishDays)
//                put(COLUMN_IS_TICKET_CONSUMED_MY_PRODUCT_FLAG, it.isTicketConsumedMyProductFlag)
//                put(COLUMN_LAST_PURCHASED_AT, it.lastPurchasedAt)
//                put(COLUMN_BOOKMARKED_AT, it.bookmarkedAt)
//                put(COLUMN_USER_CONSUMED_TICKET_PRODUCT_EPISODE_INFO_LAST_SYNC_TIME, it.userConsumedTicketProductEpisodeInfoLastSyncTime)
//                put(COLUMN_PRODUCT_EPISODE_LIST_SORT_INFO, it.productEpisodeListSortInfo)
//                put(COLUMN_LOCAL_PUSH_STATUS, it.localPushStatus)
//                put(COLUMN_LOCAL_HISTORY_STATUS,  it.localHistoryStatus)
//                put(COLUMN_TICKET_CONSUMED_MY_PRODUCT_STATUS, it.ticketConsumedMyProductStatus)
                put(COLUMN_JSON_TEXT, it.jsonText)
                put(COLUMN_STATUS, it.status)
//                put(COLUMN_SORT_VALUE_LIST_UPDATED_ASC, it.sortValueListUpdatedAsc)
//                put(COLUMN_FLAG_FETCH_SORT_VALUE_LIST_UPDATED_ASC, it.flagFetchSortValueListUpdatedAsc)
//                put(COLUMN_UPDATED_AT, it.updatedAt)
            }

            if (updateOrInsert(MY_PRODUCT_TABLE, values, "$COLUMN_PRODUCT_ID = ${it.productId}", null)) successCount++

        }

        return successCount == myProductList.size

    }
}