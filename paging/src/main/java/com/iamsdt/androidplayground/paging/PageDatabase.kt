package com.iamsdt.androidplayground.paging

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.iamsdt.androidplayground.R
import com.iamsdt.androidplayground.paging.adapter.PageDbAdapter
import com.iamsdt.androidplayground.paging.db.MyDatabase
import com.iamsdt.androidplayground.paging.db.RetDao
import com.iamsdt.androidplayground.paging.viewmodel.PageDbVM
import kotlinx.android.synthetic.main.page_db.*

class PageDatabase:AppCompatActivity(){

    val viewMode:PageDbVM by lazy {
        ViewModelProviders.of(this).get(PageDbVM::class.java)
    }

    lateinit var retDao: RetDao
    lateinit var adapter: PageDbAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_db)

        retDao = MyDatabase.getDatabase(this).retDao

        adapter = PageDbAdapter()

        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapter

        viewMode.getData(retDao).observe(this, Observer {
            adapter.submitList(it)
        })

    }

}