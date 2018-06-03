package com.iamsdt.androidplayground.paging.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.iamsdt.androidplayground.paging.db.RetDao
import com.iamsdt.androidplayground.paging.retrofit.PojoKt

class PageDbVM: ViewModel(){

    fun getData(retDao: RetDao): LiveData<PagedList<PojoKt>> {
        val factory = retDao.getData()
        val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(40)//by default page size * 3
                .setPrefetchDistance(10) // default page size
                .setEnablePlaceholders(true) //default true
                // that's means scroll bar is not jump and all data set show on the
                //recycler view first after 30 it will show empty view
                // when load it will update with animation
                .build()

        return LivePagedListBuilder(factory,config).build()
    }
}