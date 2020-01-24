package com.example.sqlite.db.schema

object MyProductSchema {

        const val MY_PRODUCT_TABLE = "my_product"

        const val COLUMN_ID = "id"
        const val COLUMN_PRODUCT_ID = "product_id"
        const val COLUMN_LAST_PRODUCT_EPISODE_ID = "last_product_episode_id"
        const val COLUMN_LAST_PRODUCT_EPISODE_TITLE = "last_product_episode_title"
        const val COLUMN_TICKET_TOTAL_COUNT = "ticket_total_count"
        const val COLUMN_WAIT_FREE_TICKET_START_CHARGED_AT = "wait_free_ticket_start_charged_at"
        const val COLUMN_WAIT_FREE_TICKET_FINISH_CHARGED_AT = "wait_free_ticket_finish_charged_at"
        const val COLUMN_WAIT_FREE_EPISODE_COUNT = "wait_free_episode_count"
        const val COLUMN_WAIT_FREE_EPISODE_READ_COUNT = "wait_free_episode_read_count"
        const val COLUMN_NOW_FREE_NEXT_META_INFO = "now_free_next_meta_info"
        const val COLUMN_NOW_FREE_NEXT_READ_AT = "now_free_next_read_at"
        const val COLUMN_NOW_FREE_OPEN_STATUS_FINISH_DAYS = "now_free_open_status_finish_days"
        const val COLUMN_IS_TICKET_CONSUMED_MY_PRODUCT_FLAG = "is_ticket_cunsumed_my_product_flag"
        const val COLUMN_LAST_PURCHASED_AT = "last_purchased_at"
        const val COLUMN_BOOKMARKED_AT = "bookmarked_at"
        const val COLUMN_USER_CONSUMED_TICKET_PRODUCT_EPISODE_INFO_LAST_SYNC_TIME = "user_consumed_ticket_product_episode_info_last_sync_time"
        const val COLUMN_PRODUCT_EPISODE_LIST_SORT_INFO = "product_episode_list_sort_info"
        const val COLUMN_LOCAL_PUSH_STATUS = "local_push_status"
        const val COLUMN_LOCAL_HISTORY_STATUS = "local_history_status"
        const val COLUMN_TICKET_CONSUMED_MY_PRODUCT_STATUS = "ticket_cunsumed_my_product_status"
        const val COLUMN_JSON_TEXT = "json_text"
        const val COLUMN_STATUS = "status"
        const val COLUMN_SORT_VALUE_LIST_UPDATED_ASC = "sort_value_list_updated_asc"
        const val COLUMN_FLAG_FETCH_SORT_VALUE_LIST_UPDATED_ASC = "flag_fetch_sort_value_list_updated_asc"
        const val COLUMN_UPDATED_AT = "updated_at"

        const val TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " +
                "$MY_PRODUCT_TABLE  (" +
                "	$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "	$COLUMN_PRODUCT_ID INTEGER NOT NULL," +
                "	$COLUMN_LAST_PRODUCT_EPISODE_ID INTEGER," +																								// 마지막 읽은 화차 아이디 정보 (3.18.0 부터 사용 안함 - episode 테이블에서 직접 검색)
                "	$COLUMN_LAST_PRODUCT_EPISODE_TITLE TEXT," +																								// 마지막 읽은 화차 타이틀 정보 (3.18.0 부터 사용 안함 - episode 테이블에서 직접 검색)
                "	$COLUMN_TICKET_TOTAL_COUNT INTEGER," +																									// 티켓 토탈 카운트 수
                "	$COLUMN_WAIT_FREE_TICKET_START_CHARGED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +											// 기다리면 무료 티켓 충전 시작 시점
                "	$COLUMN_WAIT_FREE_TICKET_FINISH_CHARGED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +											// 기다리면 무료 티켓 충전 완료 시점
                "	$COLUMN_WAIT_FREE_EPISODE_COUNT INTEGER NOT NULL DEFAULT -1," +																			// 기다무 에피소드 총 카운트 (무료화만 본경우 때문에 디폴트값 -1로 설정)
                "	$COLUMN_WAIT_FREE_EPISODE_READ_COUNT INTEGER NOT NULL DEFAULT -2," +																	// 내가 읽은 기다무 에피소드 카운트 (무료화만 본경우 때문에 디폴트값 -2로 설정)
                "	$COLUMN_NOW_FREE_NEXT_META_INFO TEXT DEFAULT ''," +																						// 今だけ０￥ 작품의 다음화 메타 정보 (now:0 or 날짜 값 or null 이 나올수 있음)
                "	$COLUMN_NOW_FREE_NEXT_READ_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +														// 今だけ０￥ 작품의 다음화 NOW_FREE_OPEN 상태로 변경 되는 날짜 정보
                "	$COLUMN_NOW_FREE_OPEN_STATUS_FINISH_DAYS INTEGER NOT NULL DEFAULT 0," +																	// 今だけ０￥ 작품의 에피소드가 NOW_FREE_OPEN 상태에서 몇일 동안 유지되는지 설정 값
                "	$COLUMN_IS_TICKET_CONSUMED_MY_PRODUCT_FLAG INTEGER NOT NULL DEFAULT 0," +																// 티켓 사용 여부 정보 - 보관함 데이터에서 데이터 셋팅 됨. (무료티켓이 아닌 전부), v3.13.0 부터 무료 에피소드도 내려주고 있어서 이제 의미가 없음
                "	$COLUMN_LAST_PURCHASED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +															// 마지막 유료 구매 날짜 정보 - 유료, 선물 키켓 소비 및 웹구매 작품
                "	$COLUMN_BOOKMARKED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +																// 북마크 설정 시각
                "	$COLUMN_USER_CONSUMED_TICKET_PRODUCT_EPISODE_INFO_LAST_SYNC_TIME DATETIME NOT NULL DEFAULT '1999-01-01 00:00:00'," +					// 구매한 에피소드 정보 최종 데이터 싱크 시간 - 유저 에피소드 데이터
                "	$COLUMN_PRODUCT_EPISODE_LIST_SORT_INFO INTEGER NOT NULL DEFAULT 1," +																	// 프로덕트 홈 에피소드 리스트 정렬 값(최신순, 신착순)
                "	$COLUMN_LOCAL_PUSH_STATUS INTEGER NOT NULL DEFAULT 1," + 																				// 로컬푸쉬 상태값 - (기다무 티켓 충전 알림 : 기본 :enable(1))
                "	$COLUMN_LOCAL_HISTORY_STATUS INTEGER NOT NULL DEFAULT 0," + 																			// 열람 히스토리 상태 (0정상, -10 삭제, -21강제 삭제)
                "	$COLUMN_TICKET_CONSUMED_MY_PRODUCT_STATUS INTEGER NOT NULL DEFAULT 0," +																// 마이작품 상태값 (v1.x 대 데이터를 지원하기 위해 유지 함 현재는 이조건 필요 없을 듯합)
                "	$COLUMN_JSON_TEXT TEXT," +
                "	$COLUMN_STATUS INTEGER NOT NULL," +
                "	$COLUMN_SORT_VALUE_LIST_UPDATED_ASC INTEGER NOT NULL DEFAULT 0," +																		// 리스트 최신 업데이트 순 정렬값(뷰어 진입시, 기다무 티켓 충전시 업데이트 된다
                "	$COLUMN_FLAG_FETCH_SORT_VALUE_LIST_UPDATED_ASC INTEGER NOT NULL DEFAULT 0," +															// 기다무 충전에 의한 sort_value_list_updated_asc 작업 설정 플래그, 필드 1인 경우 수행 대상
                "	$COLUMN_UPDATED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +																	// 로컬 히스트로 정보 업데이트 시각(use Ticket 후 뷰어 진입전에 업데이트 됨)
                "	UNIQUE ($COLUMN_PRODUCT_ID)" +
                ");"

//    companion object {
//        const val MY_PRODUCT_TABLE = "my_product"
//
//        const val COLUMN_ID = "id"
//        const val COLUMN_PRODUCT_ID = "product_id"
//        const val COLUMN_LAST_PRODUCT_EPISODE_ID = "last_product_episode_id"
//        const val COLUMN_LAST_PRODUCT_EPISODE_TITLE = "last_product_episode_title"
//        const val COLUMN_TICKET_TOTAL_COUNT = "ticket_total_count"
//        const val COLUMN_WAIT_FREE_TICKET_START_CHARGED_AT = "wait_free_ticket_start_charged_at"
//        const val COLUMN_WAIT_FREE_TICKET_FINISH_CHARGED_AT = "wait_free_ticket_finish_charged_at"
//        const val COLUMN_WAIT_FREE_EPISODE_COUNT = "wait_free_episode_count"
//        const val COLUMN_WAIT_FREE_EPISODE_READ_COUNT = "wait_free_episode_read_count"
//        const val COLUMN_NOW_FREE_NEXT_META_INFO = "now_free_next_meta_info"
//        const val COLUMN_NOW_FREE_NEXT_READ_AT = "now_free_next_read_at"
//        const val COLUMN_NOW_FREE_OPEN_STATUS_FINISH_DAYS = "now_free_open_status_finish_days"
//        const val COLUMN_IS_TICKET_CONSUMED_MY_PRODUCT_FLAG = "is_ticket_cunsumed_my_product_flag"
//        const val COLUMN_LAST_PURCHASED_AT = "last_purchased_at"
//        const val COLUMN_BOOKMARKED_AT = "bookmarked_at"
//        const val COLUMN_USER_CONSUMED_TICKET_PRODUCT_EPISODE_INFO_LAST_SYNC_TIME = "user_consumed_ticket_product_episode_info_last_sync_time"
//        const val COLUMN_PRODUCT_EPISODE_LIST_SORT_INFO = "product_episode_list_sort_info"
//        const val COLUMN_LOCAL_PUSH_STATUS = "local_push_status"
//        const val COLUMN_LOCAL_HISTORY_STATUS = "local_history_status"
//        const val COLUMN_TICKET_CONSUMED_MY_PRODUCT_STATUS = "ticket_cunsumed_my_product_status"
//        const val COLUMN_JSON_TEXT = "json_text"
//        const val COLUMN_STATUS = "status"
//        const val COLUMN_SORT_VALUE_LIST_UPDATED_ASC = "sort_value_list_updated_asc"
//        const val COLUMN_FLAG_FETCH_SORT_VALUE_LIST_UPDATED_ASC = "flag_fetch_sort_value_list_updated_asc"
//        const val COLUMN_UPDATED_AT = "updated_at"
//
//        const val TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " +
//                "$MY_PRODUCT_TABLE  (" +
//                "	$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "	$COLUMN_PRODUCT_ID INTEGER NOT NULL," +
//                "	$COLUMN_LAST_PRODUCT_EPISODE_ID INTEGER," +																								// 마지막 읽은 화차 아이디 정보 (3.18.0 부터 사용 안함 - episode 테이블에서 직접 검색)
//                "	$COLUMN_LAST_PRODUCT_EPISODE_TITLE TEXT," +																								// 마지막 읽은 화차 타이틀 정보 (3.18.0 부터 사용 안함 - episode 테이블에서 직접 검색)
//                "	$COLUMN_TICKET_TOTAL_COUNT INTEGER," +																									// 티켓 토탈 카운트 수
//                "	$COLUMN_WAIT_FREE_TICKET_START_CHARGED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +											// 기다리면 무료 티켓 충전 시작 시점
//                "	$COLUMN_WAIT_FREE_TICKET_FINISH_CHARGED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +											// 기다리면 무료 티켓 충전 완료 시점
//                "	$COLUMN_WAIT_FREE_EPISODE_COUNT INTEGER NOT NULL DEFAULT -1," +																			// 기다무 에피소드 총 카운트 (무료화만 본경우 때문에 디폴트값 -1로 설정)
//                "	$COLUMN_WAIT_FREE_EPISODE_READ_COUNT INTEGER NOT NULL DEFAULT -2," +																	// 내가 읽은 기다무 에피소드 카운트 (무료화만 본경우 때문에 디폴트값 -2로 설정)
//                "	$COLUMN_NOW_FREE_NEXT_META_INFO TEXT DEFAULT ''," +																						// 今だけ０￥ 작품의 다음화 메타 정보 (now:0 or 날짜 값 or null 이 나올수 있음)
//                "	$COLUMN_NOW_FREE_NEXT_READ_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +														// 今だけ０￥ 작품의 다음화 NOW_FREE_OPEN 상태로 변경 되는 날짜 정보
//                "	$COLUMN_NOW_FREE_OPEN_STATUS_FINISH_DAYS INTEGER NOT NULL DEFAULT 0," +																	// 今だけ０￥ 작품의 에피소드가 NOW_FREE_OPEN 상태에서 몇일 동안 유지되는지 설정 값
//                "	$COLUMN_IS_TICKET_CONSUMED_MY_PRODUCT_FLAG INTEGER NOT NULL DEFAULT 0," +																// 티켓 사용 여부 정보 - 보관함 데이터에서 데이터 셋팅 됨. (무료티켓이 아닌 전부), v3.13.0 부터 무료 에피소드도 내려주고 있어서 이제 의미가 없음
//                "	$COLUMN_LAST_PURCHASED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +															// 마지막 유료 구매 날짜 정보 - 유료, 선물 키켓 소비 및 웹구매 작품
//                "	$COLUMN_BOOKMARKED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +																// 북마크 설정 시각
//                "	$COLUMN_USER_CONSUMED_TICKET_PRODUCT_EPISODE_INFO_LAST_SYNC_TIME DATETIME NOT NULL DEFAULT '1999-01-01 00:00:00'," +					// 구매한 에피소드 정보 최종 데이터 싱크 시간 - 유저 에피소드 데이터
//                "	$COLUMN_PRODUCT_EPISODE_LIST_SORT_INFO INTEGER NOT NULL DEFAULT 1," +																	// 프로덕트 홈 에피소드 리스트 정렬 값(최신순, 신착순)
//                "	$COLUMN_LOCAL_PUSH_STATUS INTEGER NOT NULL DEFAULT 1," + 																				// 로컬푸쉬 상태값 - (기다무 티켓 충전 알림 : 기본 :enable(1))
//                "	$COLUMN_LOCAL_HISTORY_STATUS INTEGER NOT NULL DEFAULT 0," + 																			// 열람 히스토리 상태 (0정상, -10 삭제, -21강제 삭제)
//                "	$COLUMN_TICKET_CONSUMED_MY_PRODUCT_STATUS INTEGER NOT NULL DEFAULT 0," +																// 마이작품 상태값 (v1.x 대 데이터를 지원하기 위해 유지 함 현재는 이조건 필요 없을 듯합)
//                "	$COLUMN_JSON_TEXT TEXT," +
//                "	$COLUMN_STATUS INTEGER NOT NULL," +
//                "	$COLUMN_SORT_VALUE_LIST_UPDATED_ASC INTEGER NOT NULL DEFAULT 0," +																		// 리스트 최신 업데이트 순 정렬값(뷰어 진입시, 기다무 티켓 충전시 업데이트 된다
//                "	$COLUMN_FLAG_FETCH_SORT_VALUE_LIST_UPDATED_ASC INTEGER NOT NULL DEFAULT 0," +															// 기다무 충전에 의한 sort_value_list_updated_asc 작업 설정 플래그, 필드 1인 경우 수행 대상
//                "	$COLUMN_UPDATED_AT DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'," +																	// 로컬 히스트로 정보 업데이트 시각(use Ticket 후 뷰어 진입전에 업데이트 됨)
//                "	UNIQUE ($COLUMN_PRODUCT_ID)" +
//                ");"
//

//
////        val COLUMNS = arrayOf(
////            COLUMN_ID,
////            COLUMN_PRODUCT_ID,
////            COLUMN_LAST_PRODUCT_EPISODE_ID,
////            COLUMN_LAST_PRODUCT_EPISODE_TITLE,
////            COLUMN_TICKET_TOTAL_COUNT,
////            COLUMN_WAIT_FREE_TICKET_START_CHARGED_AT,
////            COLUMN_WAIT_FREE_TICKET_FINISH_CHARGED_AT,
////            COLUMN_WAIT_FREE_EPISODE_COUNT,
////            COLUMN_WAIT_FREE_EPISODE_READ_COUNT,
////            COLUMN_NOW_FREE_NEXT_META_INFO,
////            COLUMN_NOW_FREE_NEXT_READ_AT,
////            COLUMN_NOW_FREE_OPEN_STATUS_FINISH_DAYS,
////            COLUMN_IS_TICKET_CUNSUMED_MY_PRODUCT_FLAG,
////            COLUMN_LAST_PURCHASED_AT,
////            COLUMN_BOOKMARKED_AT,
////            COLUMN_USER_CONSUMED_TICKET_PRODUCT_EPISODE_INFO_LAST_SYNC_TIME,
////            COLUMN_PRODUCT_EPISODE_LIST_SORT_INFO,
////            COLUMN_LOCAL_PUSH_STATUS,
////            COLUMN_LOCAL_HISTORY_STATUS,
////            COLUMN_TICKET_CUNSUMED_MY_PRODUCT_STATUS,
////            COLUMN_JSON_TEXT,
////            COLUMN_STATUS,
////            COLUMN_SORT_VALUE_LIST_UPDATED_ASC,
////            COLUMN_FLAG_FETCH_SORT_VALUE_LIST_UPDATED_ASC,
////            COLUMN_UPDATED_AT
////        )
//    }
}