package com.iamsdt.androidplayground.paging.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface{
    @GET("photos")
    fun getData(): Call<List<PojoKt>>
}