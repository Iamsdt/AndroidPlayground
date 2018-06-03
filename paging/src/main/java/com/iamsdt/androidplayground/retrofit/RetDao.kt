package com.iamsdt.androidplayground.retrofit

import androidx.paging.DataSource
import androidx.room.Insert
import androidx.room.Query

interface RetDao{

    @Query("Select * From PojoKt")
    fun getData():DataSource.Factory<Int,PojoKt>

    @Insert
    fun insert(pojoKt: PojoKt)

}