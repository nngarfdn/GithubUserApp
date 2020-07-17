package com.iniudin.githubuserapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_item.view.*

class UserViewHolder(v : View) : RecyclerView.ViewHolder(v) {
    val name = v.txtName!!
    val location = v.txtLocation!!
    val photo = v.imgPhoto
}