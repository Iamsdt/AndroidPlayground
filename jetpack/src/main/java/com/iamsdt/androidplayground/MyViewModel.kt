package com.iamsdt.androidplayground

import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.iamsdt.androidplayground.retrofit.PojoKt
import com.iamsdt.androidplayground.retrofit.RetDao
import com.iamsdt.androidplayground.retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel:ViewModel(){


    fun getData(retDao: RetDao,retrofitInterface: RetrofitInterface):LiveData<PagedList<PojoKt>> {


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