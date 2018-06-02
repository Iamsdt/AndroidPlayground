package com.iamsdt.androidplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.iamsdt.androidplayground.retrofit.PojoKt
import com.iamsdt.androidplayground.retrofit.RetDao

class MyViewModel:ViewModel(){

    fun getData(retDao: RetDao):LiveData<PagedList<PojoKt>> =
            retDao.getData()

}