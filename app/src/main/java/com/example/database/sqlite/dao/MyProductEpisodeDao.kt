package com.example.database.sqlite.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.database.sqlite.dto.MyProductEpisode
import com.example.database.sqlite.schema.MyProductEpisodeSchema.MY_PRODUCT_EPISODE
import com.example.database.sqlite.schema.MyProductEpisodeSchema.COLUMN_PRODUCT_ID
import com.example.database.sqlite.schema.MyProductEpisodeSchema.COLUMN_PRODUCT_EPISODE_ID
import com.example.database.sqlite.schema.MyProductEpisodeSchema.COLUMN_TOTAL_PAGE_INFO
import com.example.database.sqlite.schema.MyProductEpisodeSchema.COLUMN_FINISHED_PAGE_INFO
import com.example.database.sqlite.schema.MyProductEpisodeSchema.COLUMN_FINISHED_PAGE_EXTEND_INFO
import com.example.database.sqlite.schema.MyProductEpisodeSchema.COLUMN_LAST_USED_TICKET_RENT_TYPE
import com.example.database.sqlite.schema.MyProductEpisodeSchema.COLUMN_LAST_USED_TICKET_TYPE_ID
import com.example.database.sqlite.schema.MyProductEpisodeSchema.COLUMN_LAST_VIEWER_STARTED_AT
import com.example.database.sqlite.schema.MyProductEpisodeSchema.COLUMN_TICKET_RENT_STARTED_AT
import com.example.database.sqlite.schema.MyProductEpisodeSchema.COLUMN_TICKET_RENT_EXPIRED_AT
import com.example.database.sqlite.schema.MyProductEpisodeSchema.COLUMN_EPISODE_SALE_TYPE
import com.example.database.sqlite.schema.MyProductEpisodeSchema.COLUMN_JSON_TEXT
import com.example.database.sqlite.schema.MyProductEpisodeSchema.COLUMN_FILE_DOWNLOAD_STATUS
import com.example.database.sqlite.schema.MyProductEpisodeSchema.COLUMN_STATUS
import com.example.database.sqlite.schema.MyProductEpisodeSchema.COLUMN_UPDATED_AT

class MyProductEpisodeDao(db: SQLiteDatabase) : BaseDao<MyProductEpisode>(db) {

    fun updateOrInsert(myProductEpisodeList: ArrayList<MyProductEpisode>): Boolean {

        var successCount = 0

        myProductEpisodeList.forEach {
            val values = ContentValues().apply {

                put(COLUMN_PRODUCT_ID, it.productId)
                put(COLUMN_PRODUCT_EPISODE_ID, it.productEpisodeId)
                put(COLUMN_JSON_TEXT, it.jsonText)
                put(COLUMN_STATUS, it.status)
            }

            if (updateOrInsert(
                    MY_PRODUCT_EPISODE,
                    values,
                    "$COLUMN_PRODUCT_ID = ? AND $COLUMN_PRODUCT_EPISODE_ID = ?",
                    arrayOf(it.productId.toString(), it.productEpisodeId.toString())
                )
            ) successCount++

        }

        return successCount == myProductEpisodeList.size

    }

    fun update(myProductEpisode: MyProductEpisode): Boolean {
        val values = ContentValues().apply {

            put(COLUMN_PRODUCT_ID, myProductEpisode.productId)
            put(COLUMN_PRODUCT_EPISODE_ID, myProductEpisode.productEpisodeId)
            put(COLUMN_TOTAL_PAGE_INFO, myProductEpisode.totalPageInfo)
            put(COLUMN_FINISHED_PAGE_INFO, myProductEpisode.finishedPageInfo)
            put(COLUMN_FINISHED_PAGE_EXTEND_INFO, myProductEpisode.finishedPageExtendInfo)
            put(COLUMN_LAST_USED_TICKET_RENT_TYPE, myProductEpisode.lastUsedTicketRentType)
            put(COLUMN_LAST_USED_TICKET_TYPE_ID, myProductEpisode.lastUsedTicketTypeId)
            put(COLUMN_LAST_VIEWER_STARTED_AT, myProductEpisode.lastViewerStartedAt)
            put(COLUMN_TICKET_RENT_STARTED_AT, myProductEpisode.ticketRentStartedAt)
            put(COLUMN_TICKET_RENT_EXPIRED_AT, myProductEpisode.ticketRentExpiredAt)
            put(COLUMN_EPISODE_SALE_TYPE, myProductEpisode.episodeSaleType)
            put(COLUMN_JSON_TEXT, myProductEpisode.jsonText)
            put(COLUMN_FILE_DOWNLOAD_STATUS, myProductEpisode.fileDownloadStatus)
            put(COLUMN_STATUS, myProductEpisode.status)
            put(COLUMN_UPDATED_AT, myProductEpisode.updatedAt)
        }

        return update(
            MY_PRODUCT_EPISODE,
            values,
            "$COLUMN_PRODUCT_ID = ? AND $COLUMN_PRODUCT_EPISODE_ID = ?",
            arrayOf(
                myProductEpisode.productId.toString(),
                myProductEpisode.productEpisodeId.toString()
            )
        )

    }

    fun select(productId: Long, episodeId: Long): MyProductEpisode? = query(
        MY_PRODUCT_EPISODE,
        "$COLUMN_PRODUCT_ID = ? AND $COLUMN_PRODUCT_EPISODE_ID = ?",
        arrayOf(productId.toString(), episodeId.toString())
    )?.let {
        if (it.moveToFirst()) cursorToEntity(it)
        else null
    }

    override fun cursorToEntity(cursor: Cursor): MyProductEpisode {

        return MyProductEpisode(
            getColumnIndex(cursor, COLUMN_PRODUCT_ID)?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_PRODUCT_EPISODE_ID)?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_TOTAL_PAGE_INFO)?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_FINISHED_PAGE_INFO)?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_FINISHED_PAGE_EXTEND_INFO)?.let { cursor.getString(it) },
            getColumnIndex(cursor, COLUMN_LAST_USED_TICKET_RENT_TYPE)?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_LAST_USED_TICKET_TYPE_ID)?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_LAST_VIEWER_STARTED_AT)?.let { cursor.getString(it) },
            getColumnIndex(cursor, COLUMN_TICKET_RENT_STARTED_AT)?.let { cursor.getString(it) },
            getColumnIndex(cursor, COLUMN_TICKET_RENT_EXPIRED_AT)?.let { cursor.getString(it) },
            getColumnIndex(cursor, COLUMN_EPISODE_SALE_TYPE)?.let { cursor.getString(it) },
            getColumnIndex(cursor, COLUMN_JSON_TEXT)?.let { cursor.getString(it) },
            getColumnIndex(cursor, COLUMN_FILE_DOWNLOAD_STATUS)?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_STATUS)?.let { cursor.getInt(it) },
            getColumnIndex(cursor, COLUMN_UPDATED_AT)?.let { cursor.getString(it) }
        )
    }
}