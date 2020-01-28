package com.example.sqlite

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlite.db.DataBaseHelper
import com.example.sqlite.db.dto.MyProduct
import com.example.sqlite.db.method.BookShelfDataMethod
import com.example.sqlite.MainActivity.InsertType.*

import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var testObjectList = arrayListOf<MyProduct>()
    private val contentValueResult = arrayListOf<Pair<Long, Long>>()
    private val statementResult = arrayListOf<Pair<Long, Long>>()
    private val rawSqlResult = arrayListOf<Pair<Long, Long>>()

    enum class InsertType {
        CONTENT_VALUES, PREPARED_STATEMENT, RAW_SQL
    }

    companion object {
        const val OBJECT_COUNT = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataBaseHelper.open(this)


        btn_test_1.setOnClickListener {
            it.isEnabled = false
            createObjects(CONTENT_VALUES)
            BookShelfDataMethod.deleteAllMyProduct(this)
            contentValueResult.add(Pair(System.currentTimeMillis(), testUseContentsValues()))
            showResult(CONTENT_VALUES)
            it.isEnabled = true
        }
        btn_test_2.setOnClickListener {
            it.isEnabled = false
            createObjects(PREPARED_STATEMENT)
            BookShelfDataMethod.deleteAllMyProduct(this)
            statementResult.add(Pair(System.currentTimeMillis(), testUseStatement()))
            showResult(PREPARED_STATEMENT)
            it.isEnabled = true
        }
        btn_test_3.setOnClickListener {
            it.isEnabled = false
            createObjects(RAW_SQL)
            BookShelfDataMethod.deleteAllMyProduct(this)
            rawSqlResult.add(Pair(System.currentTimeMillis(), testUseRawSql()))
            showResult(RAW_SQL)
            it.isEnabled = true
        }
    }

    private fun createObjects(insertType: InsertType) {

        testObjectList.clear()

        for (i in 0 until OBJECT_COUNT) {
            testObjectList.add(
                MyProduct(
                    productId = i,
                    jsonText = "${insertType.name}_$i"
                )
            )
        }
    }

    private fun showResult(insertType: InsertType) {

        val resultList = when (insertType) {
            CONTENT_VALUES -> contentValueResult
            PREPARED_STATEMENT -> statementResult
            RAW_SQL -> rawSqlResult
        }

        val resultView = when (insertType) {
            CONTENT_VALUES -> txt_result_1
            PREPARED_STATEMENT -> txt_result_2
            RAW_SQL -> txt_result_3
        }

        var logText = ""
        var resultText = "$OBJECT_COUNT 件登録：\n"
        resultList.forEach {
            val (startTime, endTime) = it
            logText = String.format("%,d ms", endTime - startTime)
            resultText += logText + "\n"
        }


        resultView.text = resultText


        Log.d("DB_TEST", "($OBJECT_COUNT) ${insertType.name} : $logText")


    }
//
//    private fun testDbInsert() {
//
//        val result = hashMapOf<InsertType, Pair<Long,Long>>()
//
//        result[CONTENT_VALUES] = Pair(System.currentTimeMillis(), testUseContentsValues())
//        result[PREPARED_STATEMENT] = Pair(System.currentTimeMillis(), testUseStatement())
//        result[RAW_SQL] = Pair(System.currentTimeMillis(), testUseRawSql())
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            var resultText = "${OBJECT_COUNT}件 テスト結果:\n"
//            Log.d("DB_EXECUTION", resultText)
//            result.forEach { (k, v) ->
//
//                val (startTime, endTime) = v
//
//                val logText = String.format("${k.name} : %,d ms", endTime - startTime)
//                resultText += logText + "\n"
//                Log.d("DB_EXECUTION", logText)
//            }
//
//            txt_result.text = resultText
//        }
//
//    }


    private fun testUseContentsValues(): Long {
        val result = BookShelfDataMethod.registerMyProducts(this, testObjectList)
        Log.d("DB_TEST", "result = $result")
        return System.currentTimeMillis()
    }

    private fun testUseStatement(): Long {
        val result = BookShelfDataMethod.registerMyProductsWithStatement(this, testObjectList)
        Log.d("DB_TEST", "result = $result")
        return System.currentTimeMillis()
    }

    private fun testUseRawSql(): Long {
        val result = BookShelfDataMethod.registerMyProductsWithRawSql(this, testObjectList)
        Log.d("DB_TEST", "result = $result")
        return System.currentTimeMillis()
    }
}
