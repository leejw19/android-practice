package com.example.sqlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.sqlite.db.schema.MyProductEpisodeSchema
import com.example.sqlite.db.schema.MyProductSchema

class DataBaseHelper private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "text.db"

        private var instance: DataBaseHelper? = null

        fun getInstance(context: Context): DataBaseHelper =
            instance ?: synchronized(this) {
                DataBaseHelper(
                    context
                ).also {
                    instance = it
                }
            }

        fun open(context: Context) {
            getInstance(context)
        }


    }

    val db: SQLiteDatabase = writableDatabase

    override fun close() {
        db.close()
        super.close()
    }

    override fun onCreate(db: SQLiteDatabase?) {

        Log.d("DataBase", "Calling onCreate")
        Log.d("DataBase", "DB is " + (if (db == null) "null" else "not null"))
        try {
            db?.execSQL(MyProductSchema.CREATE_TABLE)
            db?.execSQL(MyProductEpisodeSchema.TABLE_CREATE)
        } catch (e: Exception) {
            // TODO(handle error)
            Log.e("DataBase", e.message)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        if (db == null) {
            return
        }

        try {
            // Ver 2.0 에서 북마크 기능 추가로 인한 Alter문 추가 is_bookmark
            if (oldVersion <= 199) {
                db.execSQL(MyProductSchema.ALTER_TABLE_BOOKMARKED_AT_VER_200)
            }

            // Ver 2.1.1에서 보관함 기다무 티켓 필터 리스트 표시에서 읽지 않은 기다무 에피소드가 없는경우 노출 안시켜 주기 위해 추가 됨.
            if (oldVersion <= 210) {
                db.execSQL(MyProductSchema.ALTER_TABLE_WAIT_FREE_EPISODE_COUNT_VER_211)
                db.execSQL(MyProductSchema.ALTER_TABLE_WAIT_FREE_EPISODE_READ_COUNT_VER_211)
            }

            if (oldVersion <= 242) {
                db.execSQL(MyProductSchema.ALTER_TABLE_TICKET_TOTAL_COUNT_VER_250)
            }


            // 북마크 100개 이상 추가한 사람에 대한 서버와 데이터 동기화 시켜 주는 로직
            // 클라버그로 북마크를 100개 이상 추가 한 사람의 경우 클라에는 데이터가 남아 있으나 서버에는 100개 이후에 추가된 데이터가 안남아 있음
            // 버전 코드 305 까지 이면서 업데이트를 한 유저의 경우 로컬 속성 값을 -999으로 처리해주면 MainTabActivity에서 해당 값이 -999 인 사람만
            // 마이그레이션 작업을 실행하도록 로직을 추가 함.
            // 정상적으로 완료 된 경우 1값으로 설정 됨.
            // 북마크 100개 이상 추가한 사람에 대한 서버와 데이터 동기화 시켜 주는 로직
            if (oldVersion <= 305) {

            }

            // 결제 화면 튜토리얼 초기화 작업 - 기존 업그레이드 유저인 버전코드 308 까지 튜토리얼 안보이도록 설정
            if (oldVersion <= 308) {

            }

            if (oldVersion <= 311) {
                //UserManager.getInstance().setMainWaitfreeTabLastSelectedTabPosition(0); // Ver3.2.2 부터 position값이 아닌 장르 코드값으로 복원하여 삭제 함
            }


            if (oldVersion <= 337) {
                db.execSQL(MyProductSchema.ALTER_TABLE_NOW_FREE_NEXT_META_INFO_VER_380)
                db.execSQL(MyProductSchema.ALTER_TABLE_NOW_FREE_NEXT_READ_AT_VER_380)
                db.execSQL(MyProductSchema.ALTER_TABLE_NOW_FREE_OPEN_STATUS_FINISH_DAYS_VER_380)
            }

            if (oldVersion <= 383) {
                db.execSQL(MyProductSchema.ALTER_TABLE_LAST_PURCHASED_AT_VER_390)
            }


            if (oldVersion <= 399) {
                db.execSQL(MyProductEpisodeSchema.ALTER_TABLE_EPISODE_SALE_TYPE_AT_VER_400)
            }

            // 권탭 툴팁 플래그 설정 - 업데이트 유저 한테만 보여주기
            if (oldVersion <= 429) {

            }


        } catch (e: Exception) {
            // TODO(handle error)
        }

    }

}