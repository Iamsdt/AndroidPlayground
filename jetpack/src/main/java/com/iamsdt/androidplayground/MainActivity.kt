package com.iamsdt.androidplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.iamsdt.androidplayground.retrofit.MyDatabase
import com.iamsdt.androidplayground.retrofit.RetDao

class MainActivity : AppCompatActivity() {

    //https://jsonplaceholder.typicode.com/photos

    lateinit var retDao: RetDao

    val viewModel:MyViewModel by lazy {
        ViewModelProviders.of(this).get(MyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retDao = MyDatabase.getDatabase(this).bookDao

        viewModel.getData(retDao).observe(this, Observer {

        })
    }
}
