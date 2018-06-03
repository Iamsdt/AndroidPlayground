package com.iamsdt.androidplayground.paging.retrofit

import android.arch.persistence.room.Entity

@Entity
data class PojoKt(var albumId: Int = 0,
                  var id: Int = 0,
                  var title: String = "",
                  var url: String = "",
                  var thumbnailUrl: String = "")