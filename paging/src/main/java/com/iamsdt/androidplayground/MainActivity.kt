package com.iamsdt.androidplayground

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.iamsdt.androidplayground.paging.PageMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //https://jsonplaceholder.typicode.com/photos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        paging.setOnClickListener {
            startActivity(Intent(this,PageMainActivity::class.java))
        }
    }
}
