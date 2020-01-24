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

    const val TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "$MY_PRODUCT_EPISODE  (" +
            "	$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "	$COLUMN_PRODUCT_ID INTEGER NOT NULL," +
            "	$COLUMN_PRODUCT_EPISODE_ID INTEGER NOT NULL," +
            "	$COLUMN_TOTAL_PAGE_INFO INTEGER," +                                                       // 총 페이지수 정보
            "	$COLUMN_FINISHED_PAGE_INFO INTEGER," +                                                    // 마지막 읽은 페이지 정보
            "	$COLUMN_FINISHED_PAGE_EXTEND_INFO TEXT," +                                                // epub등 마지막 본 페이지 정보를 숫자 형태로 정보를 저장할수 없는 경우 해당 필드를 이용한다.
            "	$COLUMN_LAST_USED_TICKET_RENT_TYPE INTEGER," +                                            // 최종 사용 티켓의 렌트 타입 (72시간 - 렌트 / 기간제한없은 - 소장 50) 여부
            "	$COLUMN_LAST_USED_TICKET_TYPE_ID INTEGER NOT NULL DEFAULT 0," +                           // 마지막으로 사용한 티켓 정보 ( 앱삭제 후 재설치시 해당값이 안들어갈 수 있음 - 단순 참고용 )
            "	$COLUMN_LAST_VIEWER_STARTED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +        // ImageViewer가 최종적으로 열린 시각 - (해당 필드를 이용해서 다운로드 파일 자동 삭제 기능이 돌아감 - 리스트 정렬 기준 )
            "	$COLUMN_TICKET_RENT_STARTED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +        // 렌트타입인 경우 : 읽기 시작한 시점
            "	$COLUMN_TICKET_RENT_EXPIRED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +        // 렌트타입인 경우 : 종료 시점
            "	$COLUMN_EPISODE_SALE_TYPE TEXT NOT NULL DEFAULT 'E'," +                                   // 에피소드 판매 타입 (E:화 판매 / V:권 판매)
            "	$COLUMN_JSON_TEXT TEXT," +
            "	$COLUMN_FILE_DOWNLOAD_STATUS INTEGER NOT NULL DEFAULT 0," +                               // 파일 다운로드 여부 상태 정보 0 : 없음 , 10 : 저장 되어 있음.
            "	$COLUMN_STATUS INTEGER NOT NULL," +
            "	$COLUMN_UPDATED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +                    // 파일 다운로드 시점 (뷰어가 실행되는 시점에서 업데이트 됨) - 원래의미는 file_updated_at 이 더 적절하나 서버에서 이렇게 이름을져서~~
            "	UNIQUE (`$COLUMN_PRODUCT_ID`, `$COLUMN_PRODUCT_EPISODE_ID`)" +
            ");"

    const val ALTER_TABLE_EPISODE_SALE_TYPE_AT_VER_400 = "ALTER TABLE $MY_PRODUCT_EPISODE " +
            "ADD COLUMN $COLUMN_EPISODE_SALE_TYPE TEXT NOT NULL DEFAULT 'E'"

}