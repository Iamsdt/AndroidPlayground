package com.iamsdt.androidplayground.retrofit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(PojoKt::class)],version = 1,
        //don't export database
        exportSchema = false)
abstract class MyDatabase: RoomDatabase(){


    abstract val bookDao:RetDao

    companion object {

        private var instance:MyDatabase ?= null
        private val dbName = "Pojokt"

        fun getDatabase(context: Context):MyDatabase{

            if (instance == null) {
                instance = Room.databaseBuilder(context,
                        MyDatabase::class.java,dbName).build()
            }

            return instance!!
        }
    }

}