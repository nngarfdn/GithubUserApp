package com.iniudin.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.iniudin.consumerapp.R
import com.iniudin.ui.adapter.FavoriteAdapter
import com.iniudin.ui.database.MappingHelper
import com.iniudin.ui.model.GithubProfile.Companion.CONTENT_URI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

    }

    private fun getData() {
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