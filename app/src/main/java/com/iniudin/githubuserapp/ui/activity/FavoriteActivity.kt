package com.iniudin.githubuserapp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.iniudin.githubuserapp.R
import com.iniudin.githubuserapp.adapter.FavoriteAdapter
import com.iniudin.githubuserapp.database.DatabaseContract.CONTENT_URI
import com.iniudin.githubuserapp.database.MappingHelper
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity() {
    private lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
//        initViewModels()

//        getData()
//
//        val handlerThread = HandlerThread("DataObserver")
//        handlerThread.start()
//        val handler = Handler(handlerThread.looper)
//        val myObserver = object : ContentObserver(handler) {
//            override fun onChange(self: Boolean) {
//                getData()
//            }
//        }
//        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)

        GlobalScope.launch(Dispatchers.Main) {
            val deferredGit = async(Dispatchers.IO) {
                val cursor = contentResolver?.query(CONTENT_URI, null, null, null, null)
                MappingHelper.mapCursorToArrayList(cursor)
            }

            val fav = deferredGit.await()
            if (fav.size > 0) {
                adapter = FavoriteAdapter(fav)
                rvFavorited.adapter = adapter
                initRecView()
                isDataShow(true)
                isLoading(false)
            } else {
                  isDataShow(false)
                isLoading(false)
                adapter = FavoriteAdapter(fav)
                rvFavorited.adapter = adapter
                initRecView()
            }
        }
    }



    private fun initRecView() {
        rvFavorited.layoutManager = LinearLayoutManager(this)
        rvFavorited.setHasFixedSize(true)
        adapter.notifyDataSetChanged()
    }

    private fun isDataShow(status: Boolean) {
        if (status) txtNoData.visibility = View.INVISIBLE else txtNoData.visibility =
            View.VISIBLE
    }

    private fun isLoading(status: Boolean) {
        if (status) progressBar.visibility = View.VISIBLE else progressBar.visibility =
            View.INVISIBLE
    }
}