package com.iamsdt.androidplayground.paging.retrofit

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetDemo{

    var instance: Retrofit?= null
    var restInt: RetrofitInterface ?= null

    init {
        if (instance != null) {
            instance = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create(Gson()))
                    .build()

            restInt = instance?.create(RetrofitInterface::class.java)
        }
    }


}