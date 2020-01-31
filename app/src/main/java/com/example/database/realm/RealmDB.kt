package com.example.database.realm

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

object RealmDB {

//    companion object {

        private var instance: Realm? = null

        fun init(context: Context) {
            Realm.init(context)
            val config = RealmConfiguration.Builder().name("test.realm").build()
            Realm.setDefaultConfiguration(config)
            instance = Realm.getDefaultInstance()
        }

        fun getInstance(): Realm {
            return instance ?: Realm.getDefaultInstance().also {
                instance = it
            }
        }


//    }
}