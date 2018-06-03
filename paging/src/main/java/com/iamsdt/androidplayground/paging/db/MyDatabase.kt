package com.iamsdt.androidplayground.paging.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import com.iamsdt.androidplayground.paging.retrofit.PojoKt

@Database(entities = [(PojoKt::class)],version = 1,
        //don't export database
        exportSchema = false)
abstract class MyDatabase: RoomDatabase(){


    abstract val bookDao: RetDao

    companion object {

        private var instance: MyDatabase?= null
        private val dbName = "Pojokt"

        fun getDatabase(context: Context): MyDatabase {

            if (instance == null) {
                instance = Room.databaseBuilder(context,
                        MyDatabase::class.java, dbName).build()
            }

            return instance!!
        }
    }

}