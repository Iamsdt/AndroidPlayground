package com.iamsdt.androidplayground.paging

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.Gson
import com.iamsdt.androidplayground.R
import com.iamsdt.androidplayground.paging.db.MyDatabase
import com.iamsdt.androidplayground.paging.db.RetDao
import com.iamsdt.androidplayground.paging.retrofit.PojoKt
import com.iamsdt.androidplayground.paging.retrofit.RetrofitInterface
import kotlinx.android.synthetic.main.page_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

class PageMainActivity:AppCompatActivity(){

    lateinit var retDao: RetDao
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_main)

        retDao = MyDatabase.getDatabase(this).retDao

        dialog = AlertDialog.Builder(this)
                .setTitle("Wait a while")
                .setMessage("Data is loading,\n Don't look on design follow the code")
                .setCancelable(false)
                .create()

        database.setOnClickListener {
            dialog.show()
            if (!isDataInsert(this)) putData()
            else {
                dialog.dismiss()
                toNextActivity(PageDatabase::class)
            }

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

        val ret = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
        val api = ret.create(RetrofitInterface::class.java)

        api.getData().enqueue(object : Callback<List<PojoKt>> {

            override fun onResponse(call: Call<List<PojoKt>>?, response: Response<List<PojoKt>>?) {
                if (response != null && response.isSuccessful){
                    val threadLocal = HandlerThread("Data Insert")
                    threadLocal.start()

                    val handler = Handler(threadLocal.looper)
                    handler.post({
                        val data = response.body()
                        var long:Long = 0
                        data?.forEach {
                            long = retDao.insert(it)
                            Log.i("Data put",it.title)
                        }

                        if (long > 0){
                            setDataInsert(this@PageMainActivity)
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