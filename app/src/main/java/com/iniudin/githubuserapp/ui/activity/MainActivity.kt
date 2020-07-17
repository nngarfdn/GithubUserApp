package com.iniudin.githubuserapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.iniudin.githubuserapp.R
import com.iniudin.githubuserapp.adapter.UserAdapter
import com.iniudin.githubuserapp.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var adapter: UserAdapter
    private val edtInput: String = "Input Username"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            val value = savedInstanceState.getString(edtInput)
            tf_search.setText(value)
        }

        initViewModels()
        isDataShow(false)
        isLoading(false)
        btn_search.setOnClickListener {
            val username = tf_search.text.toString()
            actionSearch(username)
        }

    }

    private fun initViewModels() {
        searchViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(SearchViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lang, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_language -> {
                val mIntent = Intent(this, SettingActivity::class.java)
                startActivity(mIntent)
            }

            R.id.favorite -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun actionSearch(username: String?) {
        if (username?.isEmpty()!!) {
            Toast.makeText(this, "Input is empty", Toast.LENGTH_SHORT).show()
        } else {
            isDataShow(false)
            isLoading(true)
            searchViewModel.setSearch(username)
            getResultData()
        }
    }

    private fun getResultData() {
        searchViewModel.getSearchResutl().observe(this, Observer {
            if (it.total_count > 0) {
                adapter = UserAdapter(this)
                adapter.setData(it.items)
                rvFavorited.adapter = adapter
                initRecView()
                isDataShow(true)
                isLoading(false)
            } else {
                isDataShow(false)
                isLoading(false)
                adapter = UserAdapter(this)
                adapter.setData(it.items)
                rvFavorited.adapter = adapter
                initRecView()
            }
        })
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(edtInput, tf_search.text.toString())
    }

}