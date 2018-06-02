package com.iamsdt.androidplayground.retrofit

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.room.Insert
import androidx.room.Query

interface RetDao{

    @Query("Select * From PojoKt")
    fun getData():LiveData<PagedList<PojoKt>>

    @Insert
    fun insert(pojoKt: PojoKt)

}