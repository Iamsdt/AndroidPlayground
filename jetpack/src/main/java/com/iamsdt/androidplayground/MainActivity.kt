package com.iamsdt.androidplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.iamsdt.androidplayground.retrofit.MyDatabase
import com.iamsdt.androidplayground.retrofit.RetDao
import com.iamsdt.androidplayground.retrofit.RetDemo
import com.iamsdt.androidplayground.retrofit.RetrofitInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //https://jsonplaceholder.typicode.com/photos

    lateinit var retDao: RetDao
    lateinit var myAdapter: MyAdapter
    lateinit var retInterface: RetrofitInterface

    val viewModel:MyViewModel by lazy {
        ViewModelProviders.of(this).get(MyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retInterface = RetDemo().restInt!!

        myAdapter = MyAdapter()

        retDao = MyDatabase.getDatabase(this).bookDao

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = myAdapter

        viewModel.getData(retDao,retInterface).observe(this, Observer {
            if (it != null && it.isNotEmpty()){
                myAdapter.updateList(it)
            }
        })
    }
}
