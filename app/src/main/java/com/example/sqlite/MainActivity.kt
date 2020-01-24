package com.example.sqlite

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.sqlite.db.dto.MyProduct
import com.example.sqlite.db.dto.MyProductEpisode
import com.example.sqlite.db.method.MyProductEpisodeMethod
import com.example.sqlite.db.method.MyProductMethod

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyProductMethod.read(this, 1)
        MyProductMethod.register(this, arrayListOf(MyProduct()))
        MyProductMethod.update(this, MyProduct())


        MyProductEpisodeMethod.read(this, 1, 1)
        MyProductEpisodeMethod.register(this, arrayListOf(MyProductEpisode()))
        MyProductEpisodeMethod.update(this, MyProductEpisode())
    }


}
