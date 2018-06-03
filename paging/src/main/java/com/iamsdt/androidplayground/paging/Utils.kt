package com.iamsdt.androidplayground.paging

import android.content.Context
import android.content.SharedPreferences

fun isDataInsert(context: Context):Boolean =
        getSp(context).getBoolean("PageDatabase",false)

fun setDataInsert(context: Context){
    getSp(context).edit().putBoolean("PageDatabase",true).apply()
}

fun getSp(context: Context):SharedPreferences = context.getSharedPreferences("JetPack",Context.MODE_PRIVATE)