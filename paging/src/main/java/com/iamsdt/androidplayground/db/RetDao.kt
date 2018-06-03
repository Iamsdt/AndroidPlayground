package com.iamsdt.androidplayground.db

import androidx.paging.DataSource
import androidx.room.Insert
import androidx.room.Query
import com.iamsdt.androidplayground.retrofit.PojoKt

interface RetDao{

    @Query("Select * From PojoKt")
    fun getData():DataSource.Factory<Int, PojoKt>

    @Insert
    fun insert(pojoKt: PojoKt)

}