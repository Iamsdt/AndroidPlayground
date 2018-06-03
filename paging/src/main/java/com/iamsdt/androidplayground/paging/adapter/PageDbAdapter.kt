package com.iamsdt.androidplayground.paging.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.iamsdt.androidplayground.R

import com.iamsdt.androidplayground.paging.retrofit.PojoKt
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class PageDbAdapter: PagedListAdapter<PojoKt, PageDbAdapter.ItemView>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item,parent,false)
        return ItemView(view = view)
    }

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        val pojo:PojoKt ?= getItem(position)
        if (pojo != null) holder.bind(pojo)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PojoKt>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(oldConcert: PojoKt,
                                         newConcert: PojoKt): Boolean =
                    oldConcert.id == newConcert.id

            override fun areContentsTheSame(oldConcert: PojoKt,
                                            newConcert: PojoKt): Boolean =
                    oldConcert == newConcert
        }
    }

    inner class ItemView(view:View): RecyclerView.ViewHolder(view){
        private val id:TextView = view.textView
        private val title:TextView = view.textView2
        private val img:TextView = view.textView3

        fun bind(pojoKt: PojoKt){
            val pId = "ID: ${pojoKt.id}"
            val pTitle = "Title: ${pojoKt.title}"
            val pImg = "IMG: ${pojoKt.thumbnailUrl}"
            id.text = pId
            title.text = pTitle
            img.text = pImg
        }
    }
}

