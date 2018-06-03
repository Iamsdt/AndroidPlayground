package com.iamsdt.androidplayground


import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.iamsdt.androidplayground.paging.retrofit.PojoKt
import com.iamsdt.androidplayground.paging.db.RetDao

class DataAndNE(val retDao: RetDao) {

    //database and network
    //offline support
    //fast resume
    //minimize traffic


    /*
    When we are connected then we fetch from server
    when not fetch form data base

    The problem is that when we have catch then we are not using that
    */

    /* Another way is that
    when database tell us that I am out of data then fetch
     */

    //their is Boundry Call back
    inner class ItemBoundryCallBack: PagedList.BoundaryCallback<PojoKt>(){

        //take from constructor
//        val retrofitInterface: RetrofitInterface,
//        val db: MyDatabase

        override fun onItemAtEndLoaded(itemAtEnd: PojoKt) {
            super.onItemAtEndLoaded(itemAtEnd)

            //hey give me more data

            //if request successfully
            //insert data
            // Set is I am loading then return no need to load more
        }

    }

    val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setInitialLoadSizeHint(50)//by default page size * 3
            .setPrefetchDistance(10) // default page size
            .setEnablePlaceholders(true) //default true
            // that's means scroll bar is not jump and all data set show on the
            //recycler view first after 30 it will show empty view
            // when load it will update with animation
            .build()

    val factory = retDao.getData()
    val liveData = LivePagedListBuilder(factory,config)
            .setBoundaryCallback(ItemBoundryCallBack())
            .build()


}