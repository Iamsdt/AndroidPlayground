package com.iamsdt.androidplayground.paging.db


import android.arch.paging.DataSource
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.iamsdt.androidplayground.paging.retrofit.PojoKt


interface RetDao{

    @Query("Select * From PojoKt")
    fun getData(): DataSource.Factory<Int, PojoKt>

    @Insert
    fun insert(pojoKt: PojoKt)

}