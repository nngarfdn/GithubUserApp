package com.iniudin.githubuserapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.iniudin.githubuserapp.R
import com.iniudin.githubuserapp.adapter.UserAdapter
import com.iniudin.githubuserapp.model.User
import com.iniudin.githubuserapp.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var daftarUser: MutableList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        daftarUser = Utils.getValueFromJson(this)
        showList()

    }

    private fun showList() {
        val adapter = UserAdapter(daftarUser, this)
        val rvUser = rvUser
        rvUser.adapter = adapter
        rvUser.layoutManager = LinearLayoutManager(this)
        rvUser.setHasFixedSize(true)
    }
}