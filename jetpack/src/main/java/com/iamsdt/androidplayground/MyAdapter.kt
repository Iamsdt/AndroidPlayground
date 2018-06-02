package com.iamsdt.androidplayground

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.iamsdt.androidplayground.retrofit.PojoKt
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class MyAdapter:PagedListAdapter<PojoKt,ItemView>(DIFF_CALLBACK){

    var pageList:PagedList<PojoKt> ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item,parent)
        return ItemView(view = view)
    }

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        if (pageList != null && pageList!!.isNotEmpty()){
            val model = pageList!![position]!!
            holder.bind(model)
        }
    }

    fun updateList(pageList:PagedList<PojoKt>){
        this.pageList = pageList
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
}

class ItemView(view:View):RecyclerView.ViewHolder(view){
    val id:TextView = view.textView
    val title:TextView = view.textView2
    val img:TextView = view.textView3

    fun bind(pojoKt: PojoKt){
        id.text = pojoKt.id.toString()
        title.text = pojoKt.title
        img.text = pojoKt.thumbnailUrl
    }
}