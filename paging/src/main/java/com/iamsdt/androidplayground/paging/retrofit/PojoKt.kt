package com.iamsdt.androidplayground.paging.retrofit

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class PojoKt(var albumId: Int = 0,
                  @PrimaryKey(autoGenerate = false)
                  var id: Int = 0,
                  var title: String = "",
                  var url: String = "",
                  var thumbnailUrl: String = "")