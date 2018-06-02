package com.iamsdt.androidplayground.retrofit

import androidx.room.Entity

@Entity
data class PojoKt(var albumId: Int = 0,
                  var id: Int = 0,
                  var title: String = "",
                  var url: String = "",
                  var thumbnailUrl: String = "")