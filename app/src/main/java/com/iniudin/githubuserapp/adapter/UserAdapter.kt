package com.iniudin.githubuserapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iniudin.githubuserapp.R
import com.iniudin.githubuserapp.activity.DetailUserActivity
import com.iniudin.githubuserapp.model.User

class UserAdapter(private val cardList: MutableList<User>, val context: Context) :
    RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = cardList[position]

        holder.name.text = currentItem.name
        holder.location.text = currentItem.location

        Glide.with(context)
            .load(context.resources.getIdentifier(currentItem.avatar, "drawable", context.packageName))
            .into(holder.photo)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailUserActivity::class.java)
            intent.putExtra(DetailUserActivity.EXTRA_USER, currentItem)
            context.startActivity(intent)
        }

    }


}