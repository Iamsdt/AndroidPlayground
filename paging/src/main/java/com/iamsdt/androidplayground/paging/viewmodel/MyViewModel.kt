package com.iamsdt.androidplayground.paging.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.os.Handler
import android.os.HandlerThread
import android.util.Log

import com.iamsdt.androidplayground.paging.retrofit.PojoKt
import com.iamsdt.androidplayground.paging.db.RetDao
import com.iamsdt.androidplayground.paging.retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel: ViewModel(){


    fun getData(retDao: RetDao, retrofitInterface: RetrofitInterface): LiveData<PagedList<PojoKt>> {


        val factory = retDao.getData()
        val liveData = LivePagedListBuilder(factory,20).build()


        val thread = HandlerThread("datainser")
        thread.start()
        val handler = Handler(thread.looper)
        handler.post({
            retrofitInterface.getData().enqueue(object : Callback<List<PojoKt>> {
                override fun onFailure(call: Call<List<PojoKt>>?, t: Throwable?) {
                    Log.e("data is not come","data failed",t)
                }

                override fun onResponse(call: Call<List<PojoKt>>?, response: Response<List<PojoKt>>?) {
                    if (response != null && response.isSuccessful){
                        val list = response.body()
                        list?.forEach {
                            retDao.insert(it)
                        }
                    }
                }

            })
        })

        return liveData
    }
}