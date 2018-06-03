package com.iamsdt.androidplayground.paging

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.iamsdt.androidplayground.R
import com.iamsdt.androidplayground.paging.db.MyDatabase
import com.iamsdt.androidplayground.paging.db.RetDao
import com.iamsdt.androidplayground.paging.retrofit.PojoKt
import com.iamsdt.androidplayground.paging.retrofit.RetDemo
import com.iamsdt.androidplayground.paging.retrofit.RetrofitInterface
import kotlinx.android.synthetic.main.page_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KClass

class PageMainActivity:AppCompatActivity(){

    lateinit var restInt: RetrofitInterface
    lateinit var retDao: RetDao
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_main)

        restInt = RetDemo().restInt!!
        retDao = MyDatabase.getDatabase(this).retDao


        dialog = AlertDialog.Builder(this)
                .setTitle("Wait a while")
                .setMessage("Data is loading,\n Don't look on design follow the code")
                .setCancelable(false)
                .create()

        database.setOnClickListener {
            dialog.show()
            putData()
        }

        network.setOnClickListener {
            toNextActivity(PageNetwork::class)
        }

        network.setOnClickListener {
            toNextActivity(PageDBNet::class)
        }

    }


    /*
    This practice is pretty bad
    we must check dialog is showing or not
    we can also check lateinit is initialize or not
     */
    private fun putData() {

        restInt.getData().enqueue(object : Callback<List<PojoKt>> {

            override fun onResponse(call: Call<List<PojoKt>>?, response: Response<List<PojoKt>>?) {
                if (response != null && response.isSuccessful){
                    val threadLocal = HandlerThread("Data Insert")
                    threadLocal.start()

                    val handler = Handler(threadLocal.looper)
                    handler.post({
                        val data = response.body()
                        data?.forEach {
                            retDao.insert(it)
                        }

                        Handler(Looper.getMainLooper()).post {
                            dialog.dismiss()
                            toNextActivity(PageDatabase::class)
                        }

                        threadLocal.quitSafely()
                    })
                }
            }

            override fun onFailure(call: Call<List<PojoKt>>?, t: Throwable?) {
                Log.e("Page list","Response not found",t)
            }

        })
    }

    fun AppCompatActivity.toNextActivity(clazz: KClass<out AppCompatActivity>){
        startActivity(Intent(this,clazz.java))
    }

}