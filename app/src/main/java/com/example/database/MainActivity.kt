package com.example.database

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.database.sqlite.dto.MyProductDto
import com.example.database.sqlite.method.BookShelfDataMethod
import com.example.database.MainActivity.InsertType.*
import com.example.database.realm.RealmDB
import com.example.database.realm.RealmTask
import com.example.database.realm.model.MyProduct
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where

import kotlinx.android.synthetic.main.content_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var testObjectList = arrayListOf<MyProductDto>()
    private val contentValueResult = arrayListOf<Pair<Long, Long>>()
    private val statementResult = arrayListOf<Pair<Long, Long>>()
    private val rawSqlResult = arrayListOf<Pair<Long, Long>>()

    enum class InsertType {
        CONTENT_VALUES, PREPARED_STATEMENT, RAW_SQL
    }

    companion object {
        const val OBJECT_COUNT = 100000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BookShelfDataMethod.deleteAllMyProduct(this)
        Log.d("DB_TEST", "count all : " + BookShelfDataMethod.readAllMyProduct(this)?.size)
        val data1 = createObjects(CONTENT_VALUES)
        val data2 = createObjects(PREPARED_STATEMENT, OBJECT_COUNT)

        Log.d("DB_TEST", "btn_test_1 ${System.currentTimeMillis()}")
        Thread {
            Log.d("DB_TEST", "btn_test_1 clicked")
//                it.isEnabled = false

//                BookShelfDataMethod.deleteAllMyProduct(this)
            contentValueResult.add(Pair(System.currentTimeMillis(), testUseContentsValues(data1)))
//                contentValueResult.add(Pair(System.currentTimeMillis(), testUseStatement(data)))
            showResult(CONTENT_VALUES)
//                it.isEnabled = true
        }.start()

        Log.d("DB_TEST", "btn_test_2 ${System.currentTimeMillis()}")
        Thread {
            Log.d("DB_TEST", "btn_test_2 clicked")

//                it.isEnabled = false

//                BookShelfDataMethod.deleteAllMyProduct(this)
            statementResult.add(Pair(System.currentTimeMillis(), testUseStatement(data2)))
            showResult(PREPARED_STATEMENT)
//                it.isEnabled = true
        }.start()
//
//        RealmDB.init(this)
////        DataBaseHelper.open(this)
//
//        val db = RealmDB.getInstance()
//        btn_test_1.setOnClickListener {
//            it.isEnabled = false
//
//            try {
//                Log.d("REALM_DB", "start")
//                db.executeTransaction {
//                    db.deleteAll()
//                }
//                createObjectsForRealm(50000)
//
//                val startTime = System.currentTimeMillis()
//                db.executeTransaction { db ->
//
//                    realmObjectList.forEach { myProduct ->
//                        val obj = db.createObject<MyProduct>(myProduct.productId)
//                        obj.jsonText = myProduct.jsonText
//                    }
//                }
//                val endTime = System.currentTimeMillis()
//                Log.d("REALM_DB", "${endTime - startTime}")
//                Log.d("REALM_DB", "finished")
//            } catch (e: Exception) {
//                Log.e("REALM_DB", "", e)
//            }
//
//
//            it.isEnabled = true
//        }
//        btn_test_2.setOnClickListener {
//            it.isEnabled = false
//
//            db.where<MyProduct>().findAll().forEach { result ->
//                Log.d("REALM_DB", "text = ${result.jsonText}")
//            }
//
//            it.isEnabled = true
//        }
//        btn_test_3.setOnClickListener {
//            it.isEnabled = false
//            createObjects(RAW_SQL)
//            BookShelfDataMethod.deleteAllMyProduct(this)
//            rawSqlResult.add(Pair(System.currentTimeMillis(), testUseRawSql()))
//            showResult(RAW_SQL)
//            it.isEnabled = true
//        }
        btn_test_1.setOnClickListener {

            doAsync {
                Log.d("DB_TEST", "btn_test_1 clicked")
//                it.isEnabled = false
                val data = createObjects(CONTENT_VALUES)
//                BookShelfDataMethod.deleteAllMyProduct(this)
                contentValueResult.add(Pair(System.currentTimeMillis(), testUseContentsValues(data)))
//                contentValueResult.add(Pair(System.currentTimeMillis(), testUseStatement(data)))
                showResult(CONTENT_VALUES)
//                it.isEnabled = true
            }.execute()
//            Thread {
//                Log.d("DB_TEST", "btn_test_1 clicked")
////                it.isEnabled = false
//                val data = createObjects(CONTENT_VALUES)
////                BookShelfDataMethod.deleteAllMyProduct(this)
//                contentValueResult.add(Pair(System.currentTimeMillis(), testUseContentsValues(data)))
////                contentValueResult.add(Pair(System.currentTimeMillis(), testUseStatement(data)))
//                showResult(CONTENT_VALUES)
////                it.isEnabled = true
//            }.start()

        }
        btn_test_2.setOnClickListener {
            doAsync {
                Log.d("DB_TEST", "btn_test_2 clicked")

//                it.isEnabled = false
                val data = createObjects(PREPARED_STATEMENT, OBJECT_COUNT)
//                BookShelfDataMethod.deleteAllMyProduct(this)
                statementResult.add(Pair(System.currentTimeMillis(), testUseStatement(data)))
                showResult(PREPARED_STATEMENT)
//                it.isEnabled = true
            }.execute()
//            Thread {
//                Log.d("DB_TEST", "btn_test_2 clicked")
//
////                it.isEnabled = false
//                val data = createObjects(PREPARED_STATEMENT, OBJECT_COUNT)
////                BookShelfDataMethod.deleteAllMyProduct(this)
//                statementResult.add(Pair(System.currentTimeMillis(), testUseStatement(data)))
//                showResult(PREPARED_STATEMENT)
////                it.isEnabled = true
//            }.start()

        }
        btn_test_3.setOnClickListener {
//            Log.d("DB_TEST", "btn_test_3 clicked")
//
//            it.isEnabled = false
//            val data = createObjects(RAW_SQL)
//            Thread {
//                val result =  BookShelfDataMethod.readAllMyProduct(this)
//
//                Log.d("DB_TEST", "count all : " + result?.size)
//                Log.d("DB_TEST", "last product : " + result?.last()?.jsonText)
//
//            }.start()
            doAsync {
                val result =  BookShelfDataMethod.readAllMyProduct(this)

                Log.d("DB_TEST", "count all : " + result?.size)
                Log.d("DB_TEST", "last product : " + result?.last()?.jsonText)

            }.execute()
//            rawSqlResult.add(Pair(System.currentTimeMillis(), testUseRawSql(data)))
//            showResult(RAW_SQL)
//            it.isEnabled = true
        }
    }

    private fun createObjects(insertType: InsertType, startIndex: Int = 0): ArrayList<MyProductDto> {

        val objectList: ArrayList<MyProductDto> = arrayListOf()

        for (i in startIndex until (startIndex + OBJECT_COUNT)) {
            val productId = (0..100000000).random()
            objectList.add(
                MyProductDto(
                    productId = i,
                    jsonText = "${insertType.name}_${i}",
                    status = 0
                )
            )
        }

        return objectList
    }

    val realmObjectList = arrayListOf<MyProduct>()
   private fun createObjectsForRealm(count: Int) {

       realmObjectList.clear()

        for (i in 0 until count) {
            realmObjectList.add(
                MyProduct().apply {
                    productId = (0..100000000).random()
                    jsonText = "realm_$i"
                }
            )
        }
    }

    private fun showResult(insertType: InsertType) {

        val resultList = when (insertType) {
            CONTENT_VALUES -> contentValueResult
            PREPARED_STATEMENT -> statementResult
            RAW_SQL -> rawSqlResult
        }

//        val resultView = when (insertType) {
//            CONTENT_VALUES -> txt_result_1
//            PREPARED_STATEMENT -> txt_result_2
//            RAW_SQL -> txt_result_3
//        }

        var logText = ""
        var resultText = "$OBJECT_COUNT 件登録：\n"
        resultList.forEach {
            val (startTime, endTime) = it
            logText = String.format("%,d ms", endTime - startTime)
            resultText += logText + "\n"
        }


//        resultView.text = resultText


        Log.d("DB_TEST", "($OBJECT_COUNT) ${insertType.name} : $logText")


    }



    private fun testUseContentsValues(data: ArrayList<MyProductDto>): Long {
        val result = BookShelfDataMethod.registerMyProducts(this, data)
        Log.d("DB_TEST", "result = $result")
        return System.currentTimeMillis()
    }

    private fun testUseStatement(data: ArrayList<MyProductDto>): Long {
        val result = BookShelfDataMethod.registerMyProductsWithStatement(this, data)
        Log.d("DB_TEST", "result = $result")
        return System.currentTimeMillis()
    }

    private fun testUseRawSql(data: ArrayList<MyProductDto>): Long {
        val result = BookShelfDataMethod.registerMyProductsWithRawSql(this, data)
        Log.d("DB_TEST", "result = $result")
        return System.currentTimeMillis()
    }

    class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            handler()
            return null
        }
    }
}
