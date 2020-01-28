package com.example.sqlite.db.schema

object MyProductEpisodeSchema {

    const val MY_PRODUCT_EPISODE = "my_product"

    const val COLUMN_ID = "id"
    const val COLUMN_PRODUCT_ID = "product_id"
    const val COLUMN_PRODUCT_EPISODE_ID = "product_episode_id"
    const val COLUMN_TOTAL_PAGE_INFO = "total_page_info"
    const val COLUMN_FINISHED_PAGE_INFO = "finished_page_info"
    const val COLUMN_FINISHED_PAGE_EXTEND_INFO = "finished_page_extend_info"
    const val COLUMN_LAST_USED_TICKET_RENT_TYPE = "last_used_ticket_rent_type"
    const val COLUMN_LAST_USED_TICKET_TYPE_ID = "last_used_ticket_type_id"
    const val COLUMN_LAST_VIEWER_STARTED_AT = "last_viewer_started_at"
    const val COLUMN_TICKET_RENT_STARTED_AT = "ticket_rent_started_at"
    const val COLUMN_TICKET_RENT_EXPIRED_AT = "ticket_rent_expired_at"
    const val COLUMN_EPISODE_SALE_TYPE = "episode_sale_type"
    const val COLUMN_JSON_TEXT = "json_text"
    const val COLUMN_FILE_DOWNLOAD_STATUS = "file_download_status"
    const val COLUMN_STATUS = "status"
    const val COLUMN_UPDATED_AT = "updated_at"

    const val TABLE_CREATE = """CREATE TABLE IF NOT EXISTS 
        $MY_PRODUCT_EPISODE (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_PRODUCT_ID INTEGER NOT NULL,
            $COLUMN_PRODUCT_EPISODE_ID INTEGER NOT NULL,
            $COLUMN_TOTAL_PAGE_INFO INTEGER,
            $COLUMN_FINISHED_PAGE_INFO INTEGER,
            $COLUMN_FINISHED_PAGE_EXTEND_INFO TEXT,
            $COLUMN_LAST_USED_TICKET_RENT_TYPE INTEGER,
            $COLUMN_LAST_USED_TICKET_TYPE_ID INTEGER NOT NULL DEFAULT 0,
            $COLUMN_LAST_VIEWER_STARTED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
            $COLUMN_TICKET_RENT_STARTED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
            $COLUMN_TICKET_RENT_EXPIRED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
            $COLUMN_EPISODE_SALE_TYPE TEXT NOT NULL DEFAULT 'E',
            $COLUMN_JSON_TEXT TEXT,
            $COLUMN_FILE_DOWNLOAD_STATUS INTEGER NOT NULL DEFAULT 0,
            $COLUMN_STATUS INTEGER NOT NULL,
            $COLUMN_UPDATED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
            UNIQUE (`$COLUMN_PRODUCT_ID`, `$COLUMN_PRODUCT_EPISODE_ID`)
        );"""

    const val ALTER_TABLE_EPISODE_SALE_TYPE_AT_VER_400 =
        "ALTER TABLE $MY_PRODUCT_EPISODE ADD COLUMN $COLUMN_EPISODE_SALE_TYPE TEXT NOT NULL DEFAULT 'E'"

}